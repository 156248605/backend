package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.LinkmanMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import com.elex.oa.service.eqptService.LinkmanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class LinkmanImpl implements LinkmanService{

    @Resource
    private LinkmanMapper linkmanMapper;

    // 联系人信息
    @Override
    public PageInfo<Linkman> showLinkman (Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Linkman> listL = linkmanMapper.LinkmanList();
        return new PageInfo<>(listL);
    }

    // 查找联系人
    @Override
    public PageInfo<Linkman> searchLinkman(Page page,HttpServletRequest request) {
        String linkId = request.getParameter("linkId");
        String linkIdC = request.getParameter("linkIdC");
        String name = request.getParameter("name");
        String nameC = request.getParameter("nameC");
        String tel = request.getParameter("tel");
        String telC = request.getParameter("telC");
        String job = request.getParameter("job");
        String jobC = request.getParameter("jobC");
        String address = request.getParameter("address");
        String addressC = request.getParameter("addressC");
        String email = request.getParameter("email");
        String emailC = request.getParameter("emailC");
        String qqNum = request.getParameter("qqNum");
        String qqNumC = request.getParameter("qqNumC");
        String wechatNum = request.getParameter("wechatNum");
        String wechatNumC = request.getParameter("wechatNumC");
        Linkman linkman = new Linkman();
        linkman.setLinkId(linkId);
        linkman.setLinkIdC(linkIdC);
        linkman.setName(name);
        linkman.setNameC(nameC);
        linkman.setTel(tel);
        linkman.setTelC(telC);
        linkman.setJob(job);
        linkman.setJobC(jobC);
        linkman.setAddress(address);
        linkman.setAddressC(addressC);
        linkman.setEmail(email);
        linkman.setEmailC(emailC);
        linkman.setQqNum(qqNum);
        linkman.setQqNumC(qqNumC);
        linkman.setWechatNum(wechatNum);
        linkman.setWechatNumC(wechatNumC);
        if (linkId.equals("") && name.equals("") && tel.equals("") && job.equals("") && email.equals("") && qqNum.equals("") && wechatNum.equals("") && address.equals("")) {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Linkman> listL = linkmanMapper.LinkmanList();
            return new PageInfo<>(listL);
        }else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Linkman> listL = linkmanMapper.searchFor(linkman);
            return new PageInfo<>(listL);
        }
    }

    // 添加联系人
    @Override
    public String newLinkman(HttpServletRequest request){
        String linkId = request.getParameter("linkId");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String job = request.getParameter("job");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String qqNum = request.getParameter("qqNum");
        String wechatNum = request.getParameter("wechatNum");
        Linkman linkman = new Linkman();
        linkman.setLinkId(linkId);
        linkman.setName(name);
        linkman.setTel(tel);
        linkman.setJob(job);
        linkman.setAddress(address);
        linkman.setEmail(email);
        linkman.setQqNum(qqNum);
        linkman.setWechatNum(wechatNum);
        // 判断是否重复
        List<Linkman> listL = linkmanMapper.searchFor(linkman);
        if (listL.isEmpty() ){
            linkmanMapper.newLinkman(linkman);
            return "1";
        }else {
            return "0";
        }
    }

    // 删除联系人
    @Override
    public void deleteLinkman(HttpServletRequest request) {
        String onlyIdL = request.getParameter("onlyIdL");
        Linkman linkman = new Linkman();
        linkman.setOnlyIdL(parseInt(onlyIdL));
        linkmanMapper.deleteLinkman(linkman);
    }

    @Override
    public void changeLinkman(HttpServletRequest request) {
        String linkId = request.getParameter("linkId");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String job = request.getParameter("job");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String qqNum = request.getParameter("qqNum");
        String wechatNum = request.getParameter("wechatNum");
        String onlyIdL = request.getParameter("onlyIdL");
        Linkman linkman = new Linkman();
        linkman.setLinkId(linkId);
        linkman.setName(name);
        linkman.setTel(tel);
        linkman.setJob(job);
        linkman.setAddress(address);
        linkman.setEmail(email);
        linkman.setQqNum(qqNum);
        linkman.setWechatNum(wechatNum);
        linkman.setOnlyIdL(parseInt(onlyIdL));
        linkmanMapper.changeLinkman(linkman);
    }
}
