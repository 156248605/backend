package com.elex.oa.service.reportForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PersonDetailService {
    // 用户工号及其他信息
    List userForm(HttpServletRequest request);

    // 所在角色和权限
    List menuAndRole(HttpServletRequest request);

    // 用户所有线索,商机,项目
    Map allInfoByCOP(HttpServletRequest request);
}
