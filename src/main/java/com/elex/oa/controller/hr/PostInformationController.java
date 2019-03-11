package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.department.DeptTree;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.hr_entity.post.Post;
import com.elex.oa.entity.hr_entity.post.PostLog;
import com.elex.oa.entity.hr_entity.post.TitleAndCode;
import com.elex.oa.entity.hr_entity.readexcel.ReadPostlogExcel;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:岗位信息的表现层
 * @Date:Created in  10:38 2018\3\20 0020
 * @Modify By:
 */
@Controller
@CrossOrigin
public class PostInformationController {

    @Autowired
    private IPostService iPostService;
    @Autowired
    private IPostLogService iPostLogService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    IHRsetService ihRsetService;
    @Autowired
    HrUtils hrUtils;

    @RequestMapping("/queryOnePostByPostname")
    @ResponseBody
    public Post queryOnePostByPostname(@RequestParam("name") String name){
        Post post = iPostService.queryOneByPostname(name);
        Post parentpost = iPostService.queryOneByPostid(post.getParentpostid());
        post.setParentpost(parentpost);
        if(parentpost == null){
            parentpost = new Post();
            parentpost.setPostname("无上级岗位");
        }
        post.setParentpost(parentpost);
        HRset hRsetFunctionalType = ihRsetService.queryById(post.getFunctionaltypeid());
        if (hRsetFunctionalType!=null) {
            post.setFunctionaltype(hRsetFunctionalType.getDatavalue());
        }
        HRset hRsetPostlevel = ihRsetService.queryById(post.getPostlevelid());
        if (hRsetPostlevel!=null) {
            post.setPostlevel(hRsetPostlevel.getDatavalue());
        }
        return post;
    }

    @RequestMapping("/queryOnePostByPostid")
    @ResponseBody
    public Post queryOnePostByPostid(@RequestParam("id") Integer id){
        return iPostService.queryOnePostByPostid(id);
    }

    @RequestMapping("/queryOnePostByPostcode")
    @ResponseBody
    public Post queryOnePostByPostcode(
            @RequestParam("code")String code
    ){
        return iPostService.queryOnePostByPostcode(code);
    }

    @RequestMapping("/queryPosts")
    @ResponseBody
    public List<Post> queryPosts(){
        List<Post> posts = iPostService.queryAllPosts();
        for(Post post:posts){
            HRset hRsetFunctionalType = ihRsetService.queryById(post.getFunctionaltypeid());
            if (hRsetFunctionalType!=null) {
                post.setFunctionaltype(hRsetFunctionalType.getDatavalue());
            }
            HRset hRsetPostlevel = ihRsetService.queryById(post.getPostlevelid());
            if (hRsetPostlevel!=null) {
                post.setPostlevel(hRsetPostlevel.getDatavalue());
            }
        }
        return posts;
    }

    @RequestMapping("/queryPostsRemoveChilren")
    @ResponseBody
    public List<Post> queryPostsRemoveChilren(
            @RequestParam("postid")Integer postid
    ){
        return iPostService.queryPostsRemoveChilren(postid);
    }

    @RequestMapping("/listPosts")
    @ResponseBody
    public DeptTree listPosts(){
        List<Post> posts = iPostService.queryByParentpostid(null);
        DeptTree deptTree = new DeptTree();
        if(null==posts || posts.isEmpty())return deptTree;
        deptTree.setTitle(posts.get(0).getPostname());
        deptTree.setCode(posts.get(0).getPostcode());
        deptTree.setId(posts.get(0).getId());
        return getDeptTree(deptTree, posts.get(0).getId());
    }

    public DeptTree getDeptTree(DeptTree deptTree,Integer parentid){
        List<Post> posts = iPostService.queryByParentpostid(parentid);
        if (!posts.isEmpty()) {
            List<DeptTree> children = new ArrayList<>();
            for(int i=0;i<posts.size();i++){
                DeptTree deptTree1 = new DeptTree();
                String depname = posts.get(i).getPostname();
                deptTree1.setTitle(depname);
                deptTree1.setCode(posts.get(i).getPostcode());
                deptTree1.setId(posts.get(i).getId());
                DeptTree deptTree2 = getDeptTree(deptTree1, posts.get(i).getId());
                children.add(deptTree2);
            }
            deptTree.setChildren(children);
        }else {
            return deptTree;
        }
        return deptTree;
    }

    @RequestMapping("/addOnePost")
    @ResponseBody
    public Object addOnePost(
            Post post,
            HttpServletRequest request
    ){
        post.setDutyfile(hrUtils.getSignalFileAddress(request,"df","/org/file/"));
        return iPostService.addOnePost(post);
    }

    @RequestMapping("/queryByPostid")
    @ResponseBody
    public Post queryByPostid(@RequestParam("id") Integer id){
        Post post = iPostService.queryOneByPostid(id);
        Post parentpost = iPostService.queryOneByPostid(post.getParentpostid());
        post.setParentpost(parentpost);
        return post;
    }

    @RequestMapping("/updateOnePost")
    @ResponseBody
    public Object updateOnePost(
            Post post,
            HttpServletRequest request,
            @RequestParam("transactorusername") String transactorusername
    ){
        //获得附件的地址
        String dutyfile = hrUtils.getSignalFileAddress(request, "df", "/org/file/");
        post.setDutyfile(dutyfile);
        return iPostService.updateOnePost(post, transactorusername);
    }

    @RequestMapping("/deletePostsById")
    @ResponseBody
    public Object deletePostsById(@RequestParam("id") Integer id){
        return iPostService.deletePostsById(id);
    }

    @RequestMapping("/queryPostLogInformations")
    @ResponseBody
    public PageInfo<PostLog> queryPostLogInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            PostLog postLog
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",postLog);
        PageInfo<PostLog> postLogPageInfo = iPostLogService.queryByConditions(paramMap);
        List<PostLog> list = postLogPageInfo.getList();
        if(!list.isEmpty()){
            for (int i = 0;i< list.size();i++) {
                if (iPostService.queryOneByPostid (list.get(i).getPostid())!=null) {
                    list.get(i).setPostname(iPostService.queryOneByPostid (list.get(i).getPostid()).getPostname());
                }
                list.get(i).setTransactortruename(iUserService.getById(list.get(i).getTransactoruserid()).getTruename());
            }
            postLogPageInfo.setList(list);
        }
        return postLogPageInfo;
    }

    @RequestMapping("/queryAllPostLogInformations")
    @ResponseBody
    public List<PostLog> queryAllPostLogInformations(){
        List<PostLog> postLogs = iPostLogService.queryAllPostLogs();
        for(PostLog postLog:postLogs){
            if (iPostService.queryOneByPostid(postLog.getPostid())!=null) {
                postLog.setPostname(iPostService.queryOneByPostid(postLog.getPostid()).getPostname());
            }
            if (iUserService.getById(postLog.getTransactoruserid())!=null) {
                postLog.setTransactortruename(iUserService.getById(postLog.getTransactoruserid()).getTruename());
            }
        }
        return postLogs;
    }

    @RequestMapping("/deletePostlogByIds")
    @ResponseBody
    public String deletePostlogByIds(
            @RequestParam("postlogids") List<Integer> postlogids
    ){
        for(Integer postlogid:postlogids){
            try {
                iPostLogService.removeOne(postlogid);
            } catch (Exception e) {
                return "删除失败！";
            }
        }
        return "删除成功！";
    }

    @RequestMapping("/importPostloginformations")
    @ResponseBody
    public String importPostloginformations(
            @RequestParam("file") MultipartFile multipartFile
    ){
        try {
            ReadPostlogExcel readPostlogExcel = new ReadPostlogExcel();
            List<PostLog> excelInfo = readPostlogExcel.getExcelInfo(multipartFile);
            for(PostLog postLog:excelInfo){
                if (iPostService.queryOneByPostname(postLog.getPostname())!=null) {
                    postLog.setPostid(iPostService.queryOneByPostname(postLog.getPostname()).getId());
                }
                User user = new User();
                user.setTruename(postLog.getTransactortruename());
                List<User> users = iUserService.selectByCondition(user);
                if (!users.isEmpty()) {
                    postLog.setTransactoruserid(users.get(0).getId());
                }
                iPostLogService.addOne(postLog);
            }
        } catch (Exception e) {
            return "数据导入失败！";
        }
        return "数据导入成功！";
    }

    @RequestMapping("/sortPostinformation")
    @ResponseBody
    public List<TitleAndCode> sortPostinformation(
            @RequestParam("id") Integer id
    ){
        List<TitleAndCode> list = new ArrayList<>();
        List<Post> posts = iPostService.queryByParentpostid(iPostService.queryOneByPostid(id).getParentpostid());
        for(int i = 0;i<posts.size();i++){
            TitleAndCode titleAndCode = new TitleAndCode();
            titleAndCode.setTitle(posts.get(i).getPostname());
            titleAndCode.setCode(posts.get(i).getOrdercode());
            titleAndCode.setPostcode(posts.get(i).getPostcode());
            titleAndCode.setId(posts.get(i).getId());
            list.add(titleAndCode);
        }
        return list;
    }

    @RequestMapping("/submitSortdata2")
    @ResponseBody
    public DeptTree submitSortdata2(
            @RequestParam("sortdata") String sortdata,
            @RequestParam("code") String code
    ){
        return iPostService.submitSortdata2(sortdata,code);
    }

    @RequestMapping("/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            @RequestParam("postcode")String postcode
    ){
        Boolean aBoolean = iPostService.validateByPostcode(postcode);
        return aBoolean? RespUtil.response("200","岗位编号已存在！",null):RespUtil.response("500","岗位编号不存在",null);
    }

    @RequestMapping("/getRecommendedPostcode")
    @ResponseBody
    public String getRecommendedPostcode(){
        return iPostService.getRecommendedPostcode();
    }
}
