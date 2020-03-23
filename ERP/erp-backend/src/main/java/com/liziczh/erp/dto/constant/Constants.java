package com.liziczh.erp.dto.constant;

/**
 * @author Lizi
 */
public class Constants {
    public static final int SC_OK = 200;

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    // 进销存记录类型
    public static final String PSI_IN = "in";  // 入库
    public static final String PSI_OUT = "out"; // 出库
    public static final String PSI_REFUND = "refund"; // 退货
}
