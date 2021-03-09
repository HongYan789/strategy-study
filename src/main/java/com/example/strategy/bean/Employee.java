package com.example.strategy.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Employee implements Serializable{
    private Integer pkId;
    private String name;
    private String bankName;
    private String bankNo;
    private String birthday;
    private Integer sex;
    private String jobNo;
    private String level;
    private Integer sapState;
    private Integer sapDepartmentId;
    private Date leaveTime;
    private Date joinTime;
    private Date joinRealTime;
    private String mobile;
    private String tel;
    private String workPlace;
    private String email;
    private Integer hide;
    private String fkDingId;
    private Date updateTime;
    private String messageId;
    private Integer scanState;
    private Integer stationId;
    private String virtualGroup;
    private String subDepId;
    private String parentId;
    private String persk;
    private String district;
    private String idCard;
    private String companyCode;
    private String objectName;
    private String store;
    private String bankAccount;
    private String positionLevel;
    private String stationLevel;
    private Integer errorCount;
    private String errorCode;
    private String response;
    private String ruleType;
    private String desc;
    private Integer wechatStatus;
    private String wechatUserId;

}
