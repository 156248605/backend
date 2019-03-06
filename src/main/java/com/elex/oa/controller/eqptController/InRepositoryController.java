package com.elex.oa.controller.eqptController;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.InRepositoryImpl;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/in")
public class InRepositoryController {

    @Resource
    public InRepositoryImpl inRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request) {
        PageInfo<Repository> list = inRepositoryImpl.ShowRepository(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public String InsertRepository (HttpServletRequest request) throws ParseException {
        return inRepositoryImpl.NewRepository(request);
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        PageInfo<Repository> list = inRepositoryImpl.searchRepository(page,request);
        return list;
    }

    @RequestMapping("/material")
    @ResponseBody
    public void InsertMaterial (HttpServletRequest request) throws ParseException {
        inRepositoryImpl.InsertMaterial(request);
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void newRepository (HttpServletRequest request) {
        inRepositoryImpl.InsertRepository(request);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void DeleteInRept (HttpServletRequest request) { inRepositoryImpl.DeleteInRept(request); }

    @RequestMapping("/deleteDraft")
    @ResponseBody
    public void deleteDraft (HttpServletRequest request) { inRepositoryImpl.deleteDraft(request); }

    @RequestMapping("/showinid")
    @ResponseBody
    public List showInId (HttpServletRequest request) {
        List dateToId = inRepositoryImpl.showInId(request);
        return dateToId;
    }

    @RequestMapping("/check")
    @ResponseBody
    public String checkId (HttpServletRequest request) {
        String result = inRepositoryImpl.canIn(request);
        return result;
    }

    @RequestMapping("/postCap")
    @ResponseBody
    public String postCap (HttpServletRequest request){
        String result = inRepositoryImpl.postCap(request);
        return result;
    }

    @RequestMapping("/cgsh")
    @ResponseBody
    public List<Repository> cgsh () {
        return inRepositoryImpl.wdbh();
    }

    @RequestMapping("/showmat")
    @ResponseBody
    public List<Repository> showmat (HttpServletRequest request) {
        return inRepositoryImpl.showmat(request);
    }

    @RequestMapping("/showproj")
    @ResponseBody
    public List<Repository> showproj (HttpServletRequest request) {
        return inRepositoryImpl.showproj(request);
    }

    @RequestMapping("/showprojProduce")
    @ResponseBody
    public List<Repository> showprojProduce (HttpServletRequest request) {
        return inRepositoryImpl.showprojProduce(request);
    }


    @RequestMapping("/getInstId")
    @ResponseBody
    public String getInstId (String instid, HttpServletRequest request){
        return inRepositoryImpl.getInstId(instid, request);
    }

    @RequestMapping("/updateApprove")
    @ResponseBody
    public void updateApprove (String instId){
        inRepositoryImpl.updateApprove(instId);
    }

    @RequestMapping("/getApprove")
    @ResponseBody
    public void getApprove (HttpServletRequest request){
        inRepositoryImpl.getApprove(request);
    }

    @RequestMapping("/postInfo")
    @ResponseBody
    public List<Repository> postInfo (HttpServletRequest request){
        return inRepositoryImpl.postInfo(request);
    }

    @RequestMapping("/node")
    @ResponseBody
    public String node (HttpServletRequest request){
        return inRepositoryImpl.node(request);
    }

    @RequestMapping("/approveName")
    @ResponseBody
    public List<Repository> approveName (HttpServletRequest request){
        return inRepositoryImpl.approveName(request);
    }

    @RequestMapping("/findDraft")
    @ResponseBody
    public PageInfo<Repository> showDraft (Page page) {
        PageInfo<Repository> list = inRepositoryImpl.showDraft(page);
        return list;
    }

    @RequestMapping("/insertDraft")
    @ResponseBody
    public void insertDraft (HttpServletRequest request) throws ParseException{
        inRepositoryImpl.insertDraft(request);
    }

    @RequestMapping("/checkDraft")
    @ResponseBody
    public String checkDraft (HttpServletRequest request) {
        return inRepositoryImpl.checkDraft(request);
    }

    @RequestMapping("/postDraft")
    @ResponseBody
    public List<Repository> postDraft (HttpServletRequest request){
        return inRepositoryImpl.postDraft(request);
    }

    @RequestMapping("/noticeChild")
    @ResponseBody
    public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return inRepositoryImpl.noticeChild(request); }

    @RequestMapping("/findNotice")
    @ResponseBody
    public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {
        PageInfo<Repository> list = inRepositoryImpl.showNotice(page,request);
        return list;
    }
}
