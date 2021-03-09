# 策略模式demo学习

### Reference Documentation
主要是为了解决ifelse的繁琐代码，采用策略+抽象工厂模式来消除ifelse，并且满足对修改关闭，对扩展开放原则（开闭原则）


####demo
实现一个商城购物车加购功能：
1、不通会员等级享受不同优惠折扣
2、根据商品属性计算满折满减满赠优惠金额


####策略模式使用场景
1、针对同一问题的多种处理方式，仅仅是具体行为有所差异时；
2、需要安全的封装多种同一类型操作时；
3、同一抽象类有多个子类，而客户端需要使用if-else或者switch-case来选择具体子类时；

####策略模式涉及到的角色
环境角色（Context）：持有一个Strategy的引用
抽象策略角色（Strategy）：这是一个抽象角色，通常是一个接口或者抽象类实现。此角色给出所有具体策略类所需的接口
具体策略角色（ConcreteStrategy）：包装了相关的具体算法或者行为


1.测试满减
```$xslt
curl -X POST \
  http://127.0.0.1:8080/coupon/getCouponAmt \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: caeb901b-0e33-c1bd-88a1-ac4aac87611d' \
  -d '{
	"type":1,
	"paidAmt":30,
	"sillAmt":20,
	"fullReductionAmt":50
}'
```
返回结果
```$xslt
1
```


2.测试满折
```$xslt
curl -X POST \
  http://127.0.0.1:8080/coupon/getCouponAmt \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 717a97db-fb40-9365-314f-e44a8eac8a64' \
  -d '{
	"type":2,
	"paidAmt":30,
	"discount":0.2
}'
```

返回结果
```$xslt
6
```


3.测试N元
```$xslt
curl -X POST \
  http://127.0.0.1:8080/coupon/getCouponAmt \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 9b11a43e-6cf0-20fa-763a-73421b0d1849' \
  -d '{
	"type":3,
	"paidAmt":30,
	"nyAmt":16
}'
```

返回结果
```$xslt
16
```

4.测试直降/直减
```$xslt
curl -X POST \
  http://127.0.0.1:8080/coupon/getCouponAmt \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: acde74c1-d417-81ee-6100-7a730b775fc9' \
  -d '{
	"type":0,
	"paidAmt":30,
	"fallAmt":2
}'
```

返回结果
```$xslt
28
```


####采用反射实现动态数据使用场景


核心反射方法
```
/***
     * 根据方法名进行动态调用方法
     * @param methodName
     * @param args
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object invokeMethod(String methodName ,Object[] args) throws Exception {
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = this.getClass().getMethod(methodName,argsClass);
        return method.invoke(this, args);
    }

```

核心枚举类
```
public enum EHRMarkEnum {
    /**
     * 门店人员标识、门店员工方法调用
     */
    STORE("X","门店员工标识","StoreEmployeeMethod"),
    /**
     * 职能人员标识、职能员工方法调用
     */
    FUNCTION("Y","职能员工标识","FunctionEmployeeMethod"),
    /**
     * 全部人员标识、全部员工方法调用
     */
    ALL("ALL","全部用户","AllEmployeeMethod"),

            ;
    private String code;
    private String value;
    private String method;

    EHRMarkEnum(String code, String value, String method){
        this.code = code;
        this.value = value;
        this.method = method;
    }

    public static String getValueByCode(String code){
        EHRMarkEnum[] array = values();
        for(EHRMarkEnum arr: array){
            if(arr.code.equals(code)){
                return arr.value;
            }
        }
        return null;
    }

    public static String getMethodByCode(String code){
        EHRMarkEnum[] array = values();
        for(EHRMarkEnum arr: array){
            if(arr.code.equals(code)){
                return arr.method;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getMethod() {
        return method;
    }

}

```

方法入口
```
@GetMapping("/getData")
    public R getData(String mark, String jobNo) throws Exception {
        return R.sucess(reflectionService.queryTargetUsers(mark,jobNo));
    }
    
    
    
public List<Employee> queryTargetUsers(String mark, String jobNo) throws Exception {
        if(StringUtils.isEmpty(jobNo)){
            jobNo = "";
        }
        String method = EHRMarkEnum.getMethodByCode(mark);
        if(StringUtils.isEmpty(method)){
            log.error("通过人员标识：[{}]查询出目标人群失败",mark);
            return new ArrayList<>();
        }
        Object[] args=new Object[]{jobNo};
        R result = (R)invokeMethod(method,args);

        if(OpCode.Success == result.getCode()){
            List<Employee> list = (List<Employee>)result.getData();
            log.info("查询出目标人群返回结果：[{}] count",list.size());
            return list;
        }
        return new ArrayList<>();
    }    
```


执行结果
```
request:

curl -X GET \
  'http://127.0.0.1:8080/reflection/getData?mark=ALL&jobNo=20015441%2C20011296' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 07a7f6ba-2898-72ba-3c74-9540d72d90fb'
  
  
response: 


{
    "code": 200,
    "msg": null,
    "cost": 0,
    "data": [
        {
            "pkId": 115461,
            "name": "张雁",
            "bankName": null,
            "bankNo": null,
            "birthday": null,
            "sex": null,
            "jobNo": "20015441",
            "level": null,
            "sapState": 3,
            "sapDepartmentId": 50016218,
            "leaveTime": null,
            "joinTime": null,
            "joinRealTime": null,
            "mobile": "13971687221",
            "tel": null,
            "workPlace": null,
            "email": "ZHANGYAN@LPPZ.COM",
            "hide": null,
            "fkDingId": "0269343067793505",
            "updateTime": null,
            "messageId": null,
            "scanState": null,
            "stationId": 50017439,
            "virtualGroup": null,
            "subDepId": "50014693",
            "parentId": "50016207",
            "persk": null,
            "district": null,
            "idCard": null,
            "companyCode": null,
            "objectName": null,
            "store": "技术研发组",
            "bankAccount": null,
            "positionLevel": null,
            "stationLevel": null,
            "errorCount": null,
            "errorCode": null,
            "response": null,
            "ruleType": null,
            "desc": null,
            "wechatStatus": null,
            "wechatUserId": "20015441"
        },
        {
            "pkId": 112040,
            "name": "马露",
            "bankName": null,
            "bankNo": null,
            "birthday": null,
            "sex": null,
            "jobNo": "20011296",
            "level": null,
            "sapState": 3,
            "sapDepartmentId": 10000179,
            "leaveTime": null,
            "joinTime": null,
            "joinRealTime": null,
            "mobile": "15927456261",
            "tel": null,
            "workPlace": null,
            "email": "MALU@LPPZ.COM",
            "hide": null,
            "fkDingId": "04691459001264198",
            "updateTime": null,
            "messageId": null,
            "scanState": null,
            "stationId": 50017439,
            "virtualGroup": null,
            "subDepId": "50014693",
            "parentId": "50008262",
            "persk": null,
            "district": null,
            "idCard": null,
            "companyCode": null,
            "objectName": null,
            "store": "3312",
            "bankAccount": null,
            "positionLevel": null,
            "stationLevel": null,
            "errorCount": null,
            "errorCode": null,
            "response": null,
            "ruleType": null,
            "desc": null,
            "wechatStatus": null,
            "wechatUserId": "20011296"
        }
    ]
}  

```



https://blog.csdn.net/honger_hua/article/details/100572810
https://www.lmlphp.com/user/10038/article/item/412786/
https://bugstack.cn/itstack-demo-design/2020/07/05/%E9%87%8D%E5%AD%A6-Java-%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F-%E5%AE%9E%E6%88%98%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F.html