package com.elex.oa.service.reportForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MoneyService {
    // 获取收入支出报表
    List moneyForm(HttpServletRequest request);
}
