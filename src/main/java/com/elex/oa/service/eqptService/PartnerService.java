package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Partner;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface PartnerService {

    PageInfo<Partner> showPartner(Page page);

    PageInfo<Partner> searchPartner(Page page, HttpServletRequest request);

    void insertPartner(Partner partner, HttpServletRequest request);

    Partner name(HttpServletRequest request);

    void changePartner(Partner partner, HttpServletRequest request);

    void deletePartner(Partner partner, HttpServletRequest request);
}
