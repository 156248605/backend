package com.elex.oa.controller.eqptController;


import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptImpl.ShiftRepositoryImpl;
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
@RequestMapping("/shift")
public class ShiftRepositoryController {

    @Resource
    private ShiftRepositoryImpl shiftRepositoryImpl;

    @RequestMapping("/find")
    @ResponseBody
    public PageInfo<Repository> showrepository(Page page, HttpServletRequest request){
        PageInfo<Repository> listR = shiftRepositoryImpl.ShowRepository(page,request);
        return listR;
    }

    @RequestMapping("/search")
    @ResponseBody
    public PageInfo<Repository> searchShift(Page page, HttpServletRequest request){
        PageInfo<Repository> listR = shiftRepositoryImpl.searchRepository(page,request);
        return listR;
    }

    @RequestMapping("/new")
    @ResponseBody
    public String newRepository(HttpServletRequest request) throws ParseException{
        return shiftRepositoryImpl.NewRepository(request);
    }

    @RequestMapping("/repository")
    @ResponseBody
    public void changeRepository(HttpServletRequest request){
        shiftRepositoryImpl.changeRepository(request);
    }

    @RequestMapping("/showshiftid")
    @ResponseBody
    public List showShiftId (HttpServletRequest request) {
        List dateToId = shiftRepositoryImpl.showShiftId(request);
        return dateToId;
    }

    @RequestMapping("/postCap")
    @ResponseBody
    public String postCap(HttpServletRequest request){
        String result = shiftRepositoryImpl.postCap(request);
        return result;
    }

    @RequestMapping("/canIn")
    @ResponseBody
    public String canIn(HttpServletRequest request){
        String result = shiftRepositoryImpl.canIn(request);
        return result;
    }

    @RequestMapping("/canOut")
    @ResponseBody
    public String canOut(HttpServletRequest request){
        String result = shiftRepositoryImpl.canOut(request);
        return result;
    }

    @RequestMapping("/jy")
    @ResponseBody
    public List<Repository> jy () {
        return shiftRepositoryImpl.wdbhJ();
    }

    @RequestMapping("/scll")
    @ResponseBody
    public List<Repository> scll () {
        return shiftRepositoryImpl.wdbhL();
    }

    @RequestMapping("/gh")
    @ResponseBody
    public List<Repository> gh () {
        return shiftRepositoryImpl.wdbhG();
    }

    @RequestMapping("/sctl")
    @ResponseBody
    public List<Repository> sctl () {
        return shiftRepositoryImpl.wdbhT();
    }

    @RequestMapping("/showmatJ")
    @ResponseBody
    public List<Repository> showmatJ (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatJ(request);
    }

    @RequestMapping("/showmatL")
    @ResponseBody
    public List<Repository> showmatL (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatL(request);
    }

    @RequestMapping("/showmatG")
    @ResponseBody
    public List<Repository> showmatG (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatG(request);
    }

    @RequestMapping("/showmatT")
    @ResponseBody
    public List<Repository> showmatT (HttpServletRequest request) {
        return shiftRepositoryImpl.showmatT(request);
    }

    @RequestMapping("/showprojJ")
    @ResponseBody
    public List<Repository> showprojJ (HttpServletRequest request) {
        return shiftRepositoryImpl.showprojJ(request);
    }

    @RequestMapping("/showprojL")
    @ResponseBody
    public List<Repository> showprojL (HttpServletRequest request) {
        return shiftRepositoryImpl.showprojL(request);
    }

    @RequestMapping("/getInstId")
    @ResponseBody
    public String getInstId (String instid){
        return shiftRepositoryImpl.getInstId(instid);
    }

    @RequestMapping("/updateApprove")
    @ResponseBody
    public void updateApprove (String instId){
        shiftRepositoryImpl.updateApprove(instId);
    }

    @RequestMapping("/getApprove")
    @ResponseBody
    public void getApprove (HttpServletRequest request){
        shiftRepositoryImpl.getApprove(request);
    }

    @RequestMapping("/postInfo")
    @ResponseBody
    public List<Repository> postInfo (HttpServletRequest request){
        return shiftRepositoryImpl.postInfo(request);
    }

    @RequestMapping("/node")
    @ResponseBody
    public String node (HttpServletRequest request){
        return shiftRepositoryImpl.node(request);
    }

    @RequestMapping("/approveName")
    @ResponseBody
    public List<Repository> approveName (HttpServletRequest request){
        return shiftRepositoryImpl.approveName(request);
    }

    @RequestMapping("/findDraft")
    @ResponseBody
    public PageInfo<Repository> showDraft (Page page) {
        PageInfo<Repository> list = shiftRepositoryImpl.showDraft(page);
        return list;
    }

    @RequestMapping("/insertDraft")
    @ResponseBody
    public void insertDraft (HttpServletRequest request) throws ParseException{
        shiftRepositoryImpl.insertDraft(request);
    }

    @RequestMapping("/checkDraft")
    @ResponseBody
    public String checkDraft (HttpServletRequest request) {
        return shiftRepositoryImpl.checkDraft(request);
    }

    @RequestMapping("/postDraft")
    @ResponseBody
    public List<Repository> postDraft (HttpServletRequest request){
        return shiftRepositoryImpl.postDraft(request);
    }

    @RequestMapping("/getNotice")
    @ResponseBody
    public List<HashMap<String, Object>> getNotice(HttpServletRequest request) { return shiftRepositoryImpl.notice(request); }

    @RequestMapping("/noticeChild")
    @ResponseBody
    public List<HashMap<String, Object>> noticeChild(HttpServletRequest request) { return shiftRepositoryImpl.noticeChild(request); }

    @RequestMapping("/findNotice")
    @ResponseBody
    public PageInfo<Repository> showNotice (Page page, HttpServletRequest request) {
        PageInfo<Repository> list = shiftRepositoryImpl.showNotice(page,request);
        return list;
    }

    @RequestMapping("/deleteDraft")
    @ResponseBody
    public void deleteDraft (HttpServletRequest request) { shiftRepositoryImpl.deleteDraft(request); }
}
