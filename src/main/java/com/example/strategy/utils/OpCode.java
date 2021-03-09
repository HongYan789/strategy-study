package com.example.strategy.utils;

public class OpCode {

    /***
     * 内部异常
     */
    public static final int Internal = 601;

    /***
     * 无效参数异常
     */
    public static final int InvalidArgument = 602;

    /***
     * 成功
     */
    public static final int Success = 200;

    /***
     * 无效对象
     */
    public static final int InvalidObject = 630;
    /**
     * 路径不存在，请检查路径是否正确
     */
    public static final int InvalidPath = 404;
    /**
     * 数据重复
     */
    public static final int DUPLICATE_KEY_CODE = 1001;
    /**
     * ConstraintViolationException
     */
    public static final int PARAM_FAIL_CODE = 1002;
    /**
     * ValidationException
     */
    public static final int VALIDATION_CODE = 1003;

}
