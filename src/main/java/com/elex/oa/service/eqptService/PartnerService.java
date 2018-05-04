package com.example.oa_file.service;

import com.example.oa_file.entity.Page;
import com.example.oa_file.entity.Partner;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface PartnerService {

    PageInfo<Partner> showPartner(Page page);

    PageInfo<Partner> searchPartner(Page page,HttpServletRequest request);

    void insertPartner(Partner partner,HttpServletRequest request);

    Partner name(HttpServletRequest request);

    void changePartner(Partner partner,HttpServletRequest request);

    void deletePartner(Partner partner,HttpServletRequest request);
}
