package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.PartnerMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
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
    public List<Partner> getPart() {
        List<Partner> listP = partnerMapper.getPart();
        return listP;
    }

    @Override
    public PageInfo<Partner> searchPartner(Page page, HttpServletRequest request) {
        String pnCategory = request.getParameter("pnCategory");
        String pnCategoryC = request.getParameter("pnCateC");
        String company = request.getParameter("company");
        String companyC = request.getParameter("companyC");
        String authorize = request.getParameter("authorize");
        String authorizeC = request.getParameter("authorizeC");
        String auId = request.getParameter("auId");
        String auIdC = request.getParameter("auIdC");
        String auTel = request.getParameter("auTel");
        String auTelC = request.getParameter("auTelC");
        String auJob = request.getParameter("auJob");
        String auJobC = request.getParameter("auJobC");
        String auMail = request.getParameter("auMail");
        String auMailC = request.getParameter("auMailC");
        String auQq = request.getParameter("auQq");
        String auQqC = request.getParameter("auQqC");
        String auWechat = request.getParameter("auWechat");
        String auWechatC = request.getParameter("auWechatC");
        String auAddr = request.getParameter("auAddr");
        String auAddrC = request.getParameter("auAddrC");
        String otherLink = request.getParameter("otherLink");
        String otherLinkC = request.getParameter("otherLinkC");
        String pnId = request.getParameter("pnId");
        String pnIdC = request.getParameter("pnIdC");
        String corp = request.getParameter("corp");
        String corpC = request.getParameter("corpC");
        String comAddr = request.getParameter("comAddr");
        String comAddrC = request.getParameter("comAddrC");
        String comTel = request.getParameter("comTel");
        String comTelC = request.getParameter("comTelC");
        String capital = request.getParameter("capital");
        String capitalC = request.getParameter("capitalC");
        String staffNum = request.getParameter("staffNum");
        String staffNumC = request.getParameter("staffNumC");
        String sales = request.getParameter("sales");
        String salesC = request.getParameter("salesC");
        String brief = request.getParameter("brief");
        String briefC = request.getParameter("briefC");
        String industry = request.getParameter("industry");
        String industryC = request.getParameter("industryC");
        String area = request.getParameter("area");
        String areaC = request.getParameter("areaC");
        if (pnCategory.equals("") && company.equals("") && authorize.equals("") && area.equals("") && pnId.equals("") && corp.equals("") && comAddr.equals("") && comTel.equals("") && capital.equals("") && staffNum.equals("") && sales.equals("") && brief.equals("") && industry.equals("") && authorize.equals("") && auId.equals("") && auJob.equals("") && auTel.equals("") && auMail.equals("") && auQq.equals("'") && auWechat.equals("") && auAddr.equals("") && otherLink.equals("")){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Partner> listP = partnerMapper.PartnerList();
            return new PageInfo<>(listP);
        } else {
            Partner partner = new Partner();
            partner.setPnCategory(pnCategory);
            partner.setPnCateC(pnCategoryC);
            partner.setCompany(company);
            partner.setCompanyC(companyC);
            partner.setAuthorize(authorize);
            partner.setAuthorizeC(authorizeC);
            partner.setAuId(auId);
            partner.setAuIdC(auIdC);
            partner.setAuJob(auJob);
            partner.setAuJobC(auJobC);
            partner.setAuTel(auTel);
            partner.setAuTelC(auTelC);
            partner.setAuMail(auMail);
            partner.setAuMailC(auMailC);
            partner.setAuQq(auQq);
            partner.setAuQqC(auQqC);
            partner.setAuWechat(auWechat);
            partner.setAuWechatC(auWechatC);
            partner.setAuAddr(auAddr);
            partner.setAuAddrC(auAddrC);
            partner.setOtherLink(otherLink);
            partner.setOtherLinkC(otherLinkC);
            partner.setPnId(pnId);
            partner.setPnIdC(pnIdC);
            partner.setCorp(corp);
            partner.setCorpC(corpC);
            partner.setComAddr(comAddr);
            partner.setComAddrC(comAddrC);
            partner.setComTel(comTel);
            partner.setComTelC(comTelC);
            partner.setCapital(capital);
            partner.setCapitalC(capitalC);
            partner.setStaffNum(staffNum);
            partner.setStaffNumC(staffNumC);
            partner.setSales(sales);
            partner.setSalesC(salesC);
            partner.setBrief(brief);
            partner.setBriefC(briefC);
            partner.setIndustry(industry);
            partner.setIndustryC(industryC);
            partner.setArea(area);
            partner.setAreaC(areaC);
            List<Partner> listP = partnerMapper.searchPartner(partner);
            return new PageInfo<>(listP);
        }
    }

    @Override
    public void insertPartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setAuthorize(request.getParameter("authorize"));
        partner.setAuId(request.getParameter("auId"));
        partner.setAuJob(request.getParameter("auJob"));
        partner.setAuTel(request.getParameter("auTel"));
        partner.setAuMail(request.getParameter("auMail"));
        partner.setAuQq(request.getParameter("auQq"));
        partner.setAuWechat(request.getParameter("auWechat"));
        partner.setAuAddr(request.getParameter("auAddr"));
        partner.setOtherLink(request.getParameter("otherLink"));
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
        partner.setAuthorize(request.getParameter("name"));
        Partner partner1 = partnerMapper.findName(partner);
        return partner1;
    }

    @Override
    public void changePartner(Partner partner, HttpServletRequest request) {
        partner.setPnCategory(request.getParameter("pnCategory"));
        partner.setCompany(request.getParameter("company"));
        partner.setAuthorize(request.getParameter("authorize"));
        partner.setAuId(request.getParameter("auId"));
        partner.setAuJob(request.getParameter("auJob"));
        partner.setAuTel(request.getParameter("auTel"));
        partner.setAuMail(request.getParameter("auMail"));
        partner.setAuQq(request.getParameter("auQq"));
        partner.setAuWechat(request.getParameter("auWechat"));
        partner.setAuAddr(request.getParameter("auAddr"));
        partner.setOtherLink(request.getParameter("otherLink"));
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

    @Override
    public List<Linkman> authorize() {
        List<Linkman> list = partnerMapper.authorize();
        return list;
    }

    @Override
    public List<Linkman> authorizeId(HttpServletRequest request) {
        Linkman linkman = new Linkman();
        linkman.setName(request.getParameter("authorize"));
        List<Linkman> list = partnerMapper.authorizeId(linkman);
        return list;
    }

    @Override
    public List<Linkman> otherName(HttpServletRequest request) {
        Linkman linkman = new Linkman();
        linkman.setName(request.getParameter("authorize"));
        List<Linkman> list = partnerMapper.otherName(linkman);
        return list;
    }

    @Override
    public List<Linkman> authorizeInfo(HttpServletRequest request){
        Linkman linkman = new Linkman();
        linkman.setName(request.getParameter("authorize"));
        linkman.setLinkId(request.getParameter("auId"));
        List<Linkman> list = partnerMapper.authorizeInfo(linkman);
        return list;
    }
}
