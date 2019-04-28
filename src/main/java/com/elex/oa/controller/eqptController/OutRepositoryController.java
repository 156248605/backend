package com.elex.oa.controller.eqptController;


import afu.org.checkerframework.checker.oigj.qual.O;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.OutRepositoryImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/out")
public class OutRepositoryController {

    @Resource
    private OutRepositoryImpl outRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> ShowRepository (Page page, HttpServletRequest request){
        PageInfo<Repository> list = outRepositoryImpl.ShowRepository(page,request);
        return list;
    }

    @RequestMapping("/new")
    @ResponseBody
    public Object InsertNew (HttpServletRequest request){
        return outRepositoryImpl.InsertRepository(request);
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchRepository(Page page, HttpServletRequest request){
        PageInfo<Repository> list = outRepositoryImpl.searchRepository(page,request);
        return list;
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void OutRepository (HttpServletRequest request) {
        outRepositoryImpl.OutRepository(request);
    }

    @RequestMapping("/material")
    @ResponseBody
    public void OutMaterial (HttpServletRequest request) throws ParseException  {
        outRepositoryImpl.OutMaterial(request);
    }


    @RequestMapping("/showoutid")
    @ResponseBody
    public List showOutId (HttpServletRequest request) {
        List dateToId = outRepositoryImpl.showOutId(request);
        return dateToId;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void DeleteInRept (HttpServletRequest request) { outRepositoryImpl.DeleteOutRept(request); }

    @RequestMapping("/deleteDraft")
    @ResponseBody
    public void deleteDraft (HttpServletRequest request) { outRepositoryImpl.deleteDraft(request); }

    @RequestMapping("/check")
    @ResponseBody
    public String checkId (HttpServletRequest request) {
        String result = outRepositoryImpl.canOut(request);
        return result;
    }

    @RequestMapping("/negative")
    @ResponseBody
    public String negative (HttpServletRequest request) {
        String result = outRepositoryImpl.negative(request);
        return result;
    }

    @RequestMapping("/rcly")
    @ResponseBody
    public List<Repository> rcly () {
        return outRepositoryImpl.wdbhR();
    }

    @RequestMapping("/xsfh")
    @ResponseBody
    public List<Repository> xsfh () {
        return outRepositoryImpl.wdbhX();
    }

    @RequestMapping("/cgth")
    @ResponseBody
    public List<Repository> cgth () {
        return outRepositoryImpl.wdbhC();
    }

    @RequestMapping("/showmatR")
    @ResponseBody
    public List<Repository> showmatR (HttpServletRequest request) {
        return outRepositoryImpl.showmatR(request);
    }

    @RequestMapping("/showmatX")
    @ResponseBody
    public List<Repository> showmatX (HttpServletRequest request) {
        return outRepositoryImpl.showmatX(request);
    }

    @RequestMapping("/showmatC")
    @ResponseBody
    public List<Repository> showmatC (HttpServletRequest request) {
        return outRepositoryImpl.showmatC(request);
    }

    @RequestMapping("/showprojX")
    @ResponseBody
    public List<Repository> showprojX (HttpServletRequest request) {
        return outRepositoryImpl.showprojX(request);
    }

    @RequestMapping("/showprojR")
    @ResponseBody
    public List<Repository> showprojR (HttpServletRequest request) {
        return outRepositoryImpl.showprojR(request);
    }

    @RequestMapping("/getInstId")
    @ResponseBody
    public String getInstId (String instid){
        return outRepositoryImpl.getInstId(instid);
    }

    @RequestMapping("/updateApprove")
    @ResponseBody
    public void updateApprove (String instId){
        outRepositoryImpl.updateApprove(instId);
    }

    @RequestMapping("/getApprove")
    @ResponseBody
    public void getApprove (HttpServletRequest request){
        outRepositoryImpl.getApprove(request);
    }

    @RequestMapping("/node")
    @ResponseBody
    public String node (HttpServletRequest request){
        return outRepositoryImpl.node(request);
    }

    @RequestMapping("/postInfo")
    @ResponseBody
    public List<Repository> postInfo (HttpServletRequest request){
        return outRepositoryImpl.postInfo(request);
    }

    @RequestMapping("/approveName")
    @ResponseBody
    public List<Repository> approveName (HttpServletRequest request){
        return outRepositoryImpl.approveName(request);
    }

    @RequestMapping("/findDraft")
    @ResponseBody
    public PageInfo<Repository> showDraft (Page page) {
        PageInfo<Repository> list = outRepositoryImpl.showDraft(page);
        return list;
    }

    @RequestMapping("/insertDraft")
    @ResponseBody
    public void insertDraft (HttpServletRequest request) throws ParseException{
        outRepositoryImpl.insertDraft(request);
    }

    @RequestMapping("/checkDraft")
    @ResponseBody
    public String checkDraft (HttpServletRequest request) {
        return outRepositoryImpl.checkDraft(request);
    }

    @RequestMapping("/postDraft")
    @ResponseBody
    public List<Repository> postDraft (HttpServletRequest request){
        return outRepositoryImpl.postDraft(request);
    }

    @RequestMapping("/getNotice")
    @ResponseBody
    public List<HashMap<String, Object>> getNotice(HttpServletRequest request) { return outRepositoryImpl.notice(request); }

    @RequestMapping("/noticeChild")
    @ResponseBody
    public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return outRepositoryImpl.noticeChild(request); }

    @RequestMapping("/findNotice")
    @ResponseBody
    public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {
        PageInfo<Repository> list = outRepositoryImpl.showNotice(page,request);
        return list;
    }
}
