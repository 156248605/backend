package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.PartnerMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Partner;
import com.elex.oa.service.eqptService.PartnerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PartnerImpl implements PartnerService {

    @Resource
    private PartnerMapper partnerMapper;

    @Override
    public PageInfo<Partner> showPartner(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Partner> listP = partnerMapper.PartnerList();
        return new PageInfo<>(listP);
    }

    @Override
    public PageInfo<Partner> searchPartner(Page page, HttpServletRequest request) {
        String pnCategory = request.getParameter("pnCategory");
        String company = request.getParameter("company");
        String pjName = request.getParameter("pjName");
        String name = request.getParameter("name");
        if (pnCategory.equals("") && company.equals("") && pjName.equals("") && name.equals("") ){
            List<Partner> listP = partnerMapper.PartnerList();
            return new PageInfo<>(listP);
        } else {
            Partner partner = new Partner();
            partner.setPnCategory(pnCategory);
            partner.setCompany(company);
            partner.setPjName(pjName);
            partner.setName(name);
            List<Partner> listP = partnerMapper.searchPartner(partner);
            return new PageInfo<>(listP);
        }
    }

    @Override
    public void insertPartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setPjName(request.getParameter("pjName"));
        partner.setName(request.getParameter("name"));
        partnerMapper.insertPartner(partner);
    }

    @Override
    public Partner name(HttpServletRequest request) {
        Partner partner = new Partner();
        partner.setName(request.getParameter("name"));
        Partner partner1 = partnerMapper.findName(partner);
        return partner1;
    }

    @Override
    public void changePartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setPjName(request.getParameter("pjName"));
        partner.setName(request.getParameter("name"));
        partnerMapper.changePartner(partner);
    }

    @Override
    public void deletePartner(Partner partner, HttpServletRequest request) {
        partner.setName(request.getParameter("name"));
        partnerMapper.deletePartner(partner);
    }

}
