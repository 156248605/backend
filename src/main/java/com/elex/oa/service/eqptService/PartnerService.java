package com.elex.oa.service.eqptService;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import com.elex.oa.entity.eqpt.Partner;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PartnerService {

    PageInfo<Partner> showPartner(Page page);

    PageInfo<Partner> searchPartner(Page page,HttpServletRequest request);

    void insertPartner(Partner partner,HttpServletRequest request);

    Partner name(HttpServletRequest request);

    void changePartner(Partner partner,HttpServletRequest request);

    void deletePartner(Partner partner,HttpServletRequest request);

    //授权联系人
    List<Linkman> authorize();

    //联系人Id和其他联系人
    List<Linkman> authorizeId(HttpServletRequest request);
    List<Linkman> otherName(HttpServletRequest request);

    //联系人信息
    List<Linkman> authorizeInfo(HttpServletRequest request);
}
