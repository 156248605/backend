package com.elex.oa.service.reportForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PersonDetailService {
    // 所在角色和权限
    List menuAndRole(HttpServletRequest request);
}
