package com.elex.oa.common.hr;

/**
 * @Author:ShiYun;
 * @Description;
 * @Date:Create in 2018/2/28 17:46
 * @Modify By:
 */
public interface Commons {
    String realpath = "F:/static";
    int REST_SUCCESS = 200;
    int REST_SERVER_ERROR = 400;
    int REST_PARAM_ERROR = 500;
    String HRSET_CONTRACTTYPE = "contracttype";
    String HRSET_DIMISSION_TYPE = "dimissiontype";
    String HRSET_DIMISSION_DIRECTION = "dimissiondirection";
    String HRSET_DIMISSION_REASON = "dimissionreason";
    String POST_STATE_ON = "1";//岗位开启状态
    String POST_STATE_OFF = "0";//岗位关闭状态
    String CLUE_ON = "线索开启状态";
    String CLUE_OFF = "线索关闭状态";
    String CLUE_TRANSFOR_OPPORTUNITY = "线索已转商机状态";
    String OPPORTUNITY_ON = "商机开启状态";
    String OPPORTUNITY_OFF = "商机关闭状态";
    String OPPORTUNITY_TRANSFOR_PROJECT = "商机已转项目状态";
    String EMPLOYEE_ON = "1";//员工在职状态
    String EMPLOYEE_OFF = "0";//员工离职状态
}
