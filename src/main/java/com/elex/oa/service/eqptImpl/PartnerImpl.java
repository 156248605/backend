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

import static java.lang.Integer.parseInt;

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
        String pnId = request.getParameter("pnId");
        String corp = request.getParameter("corp");
        String comAddr = request.getParameter("comAddr");
        String comTel = request.getParameter("comTel");
        String capital = request.getParameter("capital");
        String staffNum = request.getParameter("staffNum");
        String sales = request.getParameter("sales");
        String brief = request.getParameter("brief");
        String industry = request.getParameter("industry");
        String area = request.getParameter("area");
        if (pnCategory.equals("") && company.equals("") && name.equals("") && area.equals("") && pnId.equals("") && corp.equals("") && comAddr.equals("") && comTel.equals("") && capital.equals("") && staffNum.equals("") && sales.equals("") && brief.equals("") && industry.equals("") ){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Partner> listP = partnerMapper.PartnerList();
            return new PageInfo<>(listP);
        } else {
            Partner partner = new Partner();
            partner.setPnCategory(pnCategory);
            partner.setCompany(company);
            partner.setName(name);
            partner.setPnId(pnId);
            partner.setCorp(corp);
            partner.setComAddr(comAddr);
            partner.setComTel(comTel);
            partner.setCapital(capital);
            partner.setStaffNum(staffNum);
            partner.setSales(sales);
            partner.setBrief(brief);
            partner.setIndustry(industry);
            partner.setArea(area);
            List<Partner> listP = partnerMapper.searchPartner(partner);
            return new PageInfo<>(listP);
        }
    }

    @Override
    public void insertPartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setName(request.getParameter("name"));
        partner.setPnId(request.getParameter("pnId"));
        partner.setCorp(request.getParameter("corp"));
        partner.setComAddr(request.getParameter("comAddr"));
        partner.setComTel(request.getParameter("comTel"));
        partner.setCapital(request.getParameter("capital"));
        partner.setStaffNum(request.getParameter("staffNum"));
        partner.setSales(request.getParameter("sales"));
        partner.setBrief(request.getParameter("brief"));
        partner.setIndustry(request.getParameter("industry"));
        partner.setArea(request.getParameter("area"));
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
        partner.setPnId(request.getParameter("pnId"));
        partner.setCorp(request.getParameter("corp"));
        partner.setComAddr(request.getParameter("comAddr"));
        partner.setComTel(request.getParameter("comTel"));
        partner.setCapital(request.getParameter("capital"));
        partner.setStaffNum(request.getParameter("staffNum"));
        partner.setSales(request.getParameter("sales"));
        partner.setBrief(request.getParameter("brief"));
        partner.setIndustry(request.getParameter("industry"));
        partner.setArea(request.getParameter("area"));
        partner.setOnlyIdP(parseInt(request.getParameter("onlyIdP")));
        partnerMapper.changePartner(partner);
    }

    @Override
    public void deletePartner(Partner partner, HttpServletRequest request) {
        partner.setOnlyIdP(parseInt(request.getParameter("onlyIdP")));
        partnerMapper.deletePartner(partner);
    }

}
