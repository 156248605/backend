package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.PartnerMapper;
import com.elex.oa.entity.eqpt.Page;
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
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String country = request.getParameter("country");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        String detail = request.getParameter("detail");
        if (pnCategory.equals("") && company.equals("") && name.equals("") && tel.equals("") && country.equals("") && province.equals("") && city.equals("") && area.equals("") && detail.equals("")){
            List<Partner> listP = partnerMapper.PartnerList();
            return new PageInfo<>(listP);
        } else {
            Partner partner = new Partner();
            partner.setPnCategory(pnCategory);
            partner.setCompany(company);
            partner.setName(name);
            partner.setTel(tel);
            partner.setCountry(country);
            partner.setProvince(province);
            partner.setCity(city);
            partner.setArea(area);
            partner.setDetail(detail);
            List<Partner> listP = partnerMapper.searchPartner(partner);
            return new PageInfo<>(listP);
        }
    }

    @Override
    public void insertPartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setName(request.getParameter("name"));
        partner.setTel(request.getParameter("tel"));
        partner.setCountry(request.getParameter("country"));
        partner.setProvince(request.getParameter("province"));
        partner.setCity(request.getParameter("city"));
        partner.setArea(request.getParameter("area"));
        partner.setDetail(request.getParameter("detail"));
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
        partner.setName(request.getParameter("name"));
        partner.setTel(request.getParameter("tel"));
        partner.setCountry(request.getParameter("country"));
        partner.setProvince(request.getParameter("province"));
        partner.setCity(request.getParameter("city"));
        partner.setArea(request.getParameter("area"));
        partner.setDetail(request.getParameter("detail"));
        partnerMapper.changePartner(partner);
    }

    @Override
    public void deletePartner(Partner partner, HttpServletRequest request) {
        partner.setName(request.getParameter("name"));
        partnerMapper.deletePartner(partner);
    }

}
