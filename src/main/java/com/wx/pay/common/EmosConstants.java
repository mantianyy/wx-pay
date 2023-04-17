package com.wx.pay.common;

/**
 * 常量工具类
 */
public class EmosConstants {

    /**已禁用**/
    public static final Integer  USER_DISABLED = 0;
    /**已激活**/
    public static final Integer  USER_ACTIVITED = 1;
    /**激活码**/
    public static final String  ROOT_ACTIVE_CODE = "0000";

    /**权限名**/
    public static final String EMOS_PERMISSION_NAME = "permission_name";
    /**角色名**/
    public static final String EMOS_ROLE_NAME = "role_name";

    /**休息日**/
    public final static Integer  EMOS_OFFDAY = -1;
    /**特殊节假日**/
    public final static Integer EMOS_HOLIDAY = 0;
    /**特殊工作日**/
    public final static Integer EMOS_WORKDAY = 1;

    /**疫情高风险等级**/
    public final static String EMOS_EPIDEMIC_RISK_HIGH_LEVEL = "高风险";
    /**疫情中风险等级**/
    public final static String EMOS_EPIDEMIC_RISK_MEDIUM_LEVEL = "中风险";
    /**疫情高低险等级**/
    public final static String EMOS_EPIDEMIC_RISK_LOW_LEVEL = "低风险";

    /**人脸识别-识别错误**/
    public final static String EMOS_UNABLE_RECOGNIZE_FACES = "无法识别出人脸";
    /**人脸识别-存在多张人脸**/
    public final static String EMOS_MULTIPLE_FACES_IN_PHOTO = "照片中存在多张人脸";
    /**人脸识别-执行错误**/
    public final static String EMOS_IDENTIFY_ERROR = "执行错误";
    /**人脸识别-识别失败**/
    public final static String EMOS_RECOGNIZE_FACES_ERROR = "False";
    /**人脸识别-识别成功**/
    public final static String EMOS_RECOGNIZE_FACES_TRUE = "True";

    /**考勤结果-正常打卡**/
    public final static String EMOS_WORK_NORMAL_ATTEND = "打卡";
    /**考勤结果-迟到**/
    public final static String EMOS_WORK_LATE = "迟到";
    /**考勤结果-缺勤**/
    public final static String EMOS_WORK_ABSENT = "缺勤";

    /**MongoDB-message表**/
    public final static String EMOS_MONGODB_MESSAGE_NAME = "message";
    /**MongoDB-message_ref表**/
    public final static String EMOS_MONGODB_MESSAGE_REF_NAME = "message_ref";
    /**消息队列-默认Sender**/
    public final static int EMOS_MONGODB_DEFAULT_SENDER = 0;
    /**消息队列-默认Msg**/
    public final static String EMOS_MONGODB_DEFAULT_RESULT = "消息发送成功";

    /*******************会议状态****************/
    /**审批中**/
    public final static String EMOS_MEETING_NOT_STARTED = "1";
    /**已拒绝**/
    public final static String EMOS_MEETING_PROCESSING = "2";
    /**已审批**/
    public final static String EMOS_MEETING_APPROVED = "3";
    /**已归档**/
    public final static String EMOS_MEETING_ARCHIVED = "4";
    /**已关闭**/
    public final static String EMOS_MEETING_END = "5";


    /*******************角色****************/
    public final static String EMOS_USER = "普通用户";
    public final static String EMOS_EMPLOYEE = "普通员工";
    public final static String EMOS_DEPT_MANAGER = "部门经理";
    public final static String EMOS_GENERAL_MANAGER = "总经理";
    public final static String EMOS_ROOT = "超级管理员";

    /*******************工作流接口****************/
    /**开启工作流**/
    public final static String START_WORKFLOW_URL = "/workflow/startMeetingProcess";
    /**删除工作流**/
    public final static String DELETE_WORKFLOW_URL = "/workflow/deleteProcessById";
    /**查询用户审批列表**/
    public final static String SELECT_USER_TASK_WORKFLOW_URL = "/workflow/searchUserTaskListByPage";
    /**审批会议**/
    public final static String APPROVAL_MEETING = "/workflow/approvalMeeting";


    /*******************会议类型****************/
    /**线上会议**/
    public final static int MEETING_ONLINE = 1;
    /**线下会议**/
    public final static int MEETING_OFFLINE = 2;
    /**审批通过**/
    public final static String MEETING_APPROVAL = "同意";
    /**审批不通过**/
    public final static String MEETING_APPROVAL_FAILED = "不同意";

    /*******************短信****************/
    /**发送成功**/
    public final static String SMS_OK = "Ok";
}
