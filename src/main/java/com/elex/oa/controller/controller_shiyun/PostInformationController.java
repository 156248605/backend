package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.common.common_shiyun.Commons;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.permission.JobService;
import com.elex.oa.service.service_shiyun.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    JobService jobService;//权限相关部分,岗位信息添加修改
    @Autowired
    IHRsetFunctionalTypeService ihRsetFunctionalTypeService;
    @Autowired
    IHRsetPostlevelService ihRsetPostlevelService;


    /**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 10:40 2018\3\20 0020
     */
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
        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(post.getFunctionaltypeid());
        if (hRsetFunctionalType!=null) {
            post.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
        }
        HRsetPostlevel hRsetPostlevel = ihRsetPostlevelService.queryById(post.getPostlevelid());
        if (hRsetPostlevel!=null) {
            post.setPostlevel(hRsetPostlevel.getPostlevel());
        }
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位
     *@Date: 11:14 2018\4\11 0011
     */
    @RequestMapping("/queryPosts")
    @ResponseBody
    public List<Post> queryPosts(){
        List<Post> posts = iPostService.queryAllPosts();
        for(Post post:posts){
            HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(post.getFunctionaltypeid());
            if (hRsetFunctionalType!=null) {
                post.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
            }
            HRsetPostlevel hRsetPostlevel = ihRsetPostlevelService.queryById(post.getPostlevelid());
            if (hRsetPostlevel!=null) {
                post.setPostlevel(hRsetPostlevel.getPostlevel());
            }
        }
        return posts;
    }

    /**
     *@Author:ShiYun;
     *@Description:将岗位数列出来
     *@Date: 9:37 2018\4\23 0023
     */
    @RequestMapping("/listPosts")
    @ResponseBody
    public DeptTree listPosts(){
        List<Post> posts = iPostService.queryByParentpostid(null);
        DeptTree deptTree = new DeptTree();
        deptTree.setTitle(posts.get(0).getPostname());
        DeptTree deptTree1 = getDeptTree(deptTree, posts.get(0).getId());
        return deptTree1;
    }

    public DeptTree getDeptTree(DeptTree deptTree,Integer parentid){
        List<Post> posts = iPostService.queryByParentpostid(parentid);;
        if (posts.size()!=0) {
            List<DeptTree> children = new ArrayList<DeptTree>();
            for(int i=0;i<posts.size();i++){
                DeptTree deptTree1 = new DeptTree();
                String depname = posts.get(i).getPostname();
                deptTree1.setTitle(depname);
                DeptTree deptTree2 = getDeptTree(deptTree1, posts.get(i).getId());
                children.add(deptTree2);
            }
            deptTree.setChildren(children);
        }else {
            return deptTree;
        }
        return deptTree;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息
     *@Date: 11:55 2018\4\23 0023
     */
    @RequestMapping("/addOnePost")
    @ResponseBody
    public String addOnePost(
            Post post,
            HttpServletRequest request
    ){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> dfs= multipartRequest.getFiles("df");
        if(dfs.size()!=0){
            try {
                String realPath = Commons.realpath + "/org/file";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                String dutyfile = "/org/file/" + l+ "/" + dfs.get(0).getOriginalFilename();
                dfs.get(0).transferTo(new File(realPath + "/" + l,dfs.get(0).getOriginalFilename()));
                post.setDutyfile(dutyfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryByFuctionaltype(post.getFunctionaltype());
        post.setFunctionaltypeid(hRsetFunctionalType.getId());
        HRsetPostlevel hRsetPostlevel = ihRsetPostlevelService.queryByPostlevel(post.getPostlevel());
        post.setPostlevelid(hRsetPostlevel.getId());
        Integer postid = iPostService.addOne(post);
        if (postid == post.getId()) {
            /*
             *id,name
             * post.getPostname()即为name
             * post.getId()即为id
             */
            int judgment = jobService.addJob(post.getId(), post.getPostname());
        }
        /*
         *Map<String,Object> result = new HashMap<>();
         * if(postid == post.getId()){
         *      result.put("result","success");
         *      result.put("message","添加成功");
         * }else{
         *       result.put("result","failure");
         *      result.put("message","添加失败，稍后再试");
         * }
         */
        return "提交成功!";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID查询岗位信息
     *@Date: 10:44 2018\5\2 0002
     */
    @RequestMapping("/queryByPostid")
    @ResponseBody
    public Post queryByPostid(@RequestParam("id") Integer id){
        Post post = iPostService.queryOneByPostid(id);
        Post parentpost = iPostService.queryOneByPostid(post.getParentpostid());
        post.setParentpost(parentpost);
        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(post.getFunctionaltypeid());
        if (hRsetFunctionalType!=null) {
            post.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
        }
        HRsetPostlevel hRsetPostlevel = ihRsetPostlevelService.queryById(post.getPostlevelid());
        if (hRsetPostlevel!=null) {
            post.setPostlevel(hRsetPostlevel.getPostlevel());
        }
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:03 2018\5\2 0002
     */
    @RequestMapping("/updateOnePost")
    @ResponseBody
    public String updateOnePost(
            @Valid Post post,
            HttpServletRequest request,
            @RequestParam("transactorusername") String transactorusername
    ){
        try {
            Boolean b = false;
            List<MultipartFile> dfs= null;
            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                dfs = multipartRequest.getFiles("df");
            } catch (Exception e) {
                return "提交失败！";
            }
            // 添加岗位日志
            PostLog postLog = new PostLog();
            postLog.setPostid(post.getId());
            postLog.setChangereason("公司业务需要");// 修改的原因业务暂未开发，默认为固定值
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String changedate = simpleDateFormat.format(new Date());
            postLog.setChangedate(changedate);
            User user = new User();
            user.setUsername(transactorusername);
            postLog.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());//默认为管理员，实际从session中拿

            Post post2 = iPostService.queryOneByPostid(post.getId());
            HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(post2.getFunctionaltypeid());
            if (hRsetFunctionalType!=null) {
                post2.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
            }
            HRsetPostlevel hRsetPostlevel = ihRsetPostlevelService.queryById(post2.getPostlevelid());
            if (hRsetPostlevel!=null) {
                post2.setPostlevel(hRsetPostlevel.getPostlevel());
            }

            if (!post.getPostname().equals(post2.getPostname())){
                b = true;
                postLog.setChangeinformation("岗位名称");
                postLog.setBeforeinformation(post2.getPostname());
                postLog.setAfterinformation(post.getPostname());
                iPostLogService.addOne(postLog);
            }if (!post.getFunctionaltype().equals(post2.getFunctionaltype())){
                b = true;
                postLog.setChangeinformation("职能类型");
                postLog.setBeforeinformation(post2.getFunctionaltype());
                postLog.setAfterinformation(post.getFunctionaltype());
                iPostLogService.addOne(postLog);
            }
            if ( post.getParentpostid()!=null && !post.getParentpostid().toString().equals(post2.getParentpostid().toString())){
                b = true;
                postLog.setChangeinformation("上级岗位");
                postLog.setBeforeinformation(iPostService.queryOneByPostid(post2.getParentpostid()).getPostname());
                postLog.setAfterinformation(iPostService.queryOneByPostid(post.getParentpostid()).getPostname());
                iPostLogService.addOne(postLog);
            }
            if (!post.getPostlevel().equals(post2.getPostlevel())){
                b = true;
                postLog.setChangeinformation("岗位级别");
                postLog.setBeforeinformation(post2.getPostlevel());
                postLog.setAfterinformation(post.getPostlevel());
                iPostLogService.addOne(postLog);
            }if (!post.getPostcode().equals(post2.getPostcode())){
                b = true;
                postLog.setChangeinformation("岗位编号");
                postLog.setBeforeinformation(post2.getPostcode());
                postLog.setAfterinformation(post.getPostcode());
                iPostLogService.addOne(postLog);
            }if (!post.getOrganization().equals(post2.getOrganization())){
                b = true;
                postLog.setChangeinformation("编制");
                postLog.setBeforeinformation(post2.getOrganization());
                postLog.setAfterinformation(post.getOrganization());
                iPostLogService.addOne(postLog);
            }if (!post.getDuty().equals(post2.getDuty())){
                b = true;
                postLog.setChangeinformation("职责");
                postLog.setBeforeinformation(post2.getDuty());
                postLog.setAfterinformation(post.getDuty());
                iPostLogService.addOne(postLog);
            }if (!post.getEntryrequirements().equals(post2.getEntryrequirements())){
                b = true;
                postLog.setChangeinformation("入职要求");
                postLog.setBeforeinformation(post2.getEntryrequirements());
                postLog.setAfterinformation(post.getEntryrequirements());
                iPostLogService.addOne(postLog);
            }if (!post.getJobdescription().equals(post2.getJobdescription())){
                b = true;
                postLog.setChangeinformation("岗位工作描述");
                postLog.setBeforeinformation(post2.getJobdescription());
                postLog.setAfterinformation(post.getJobdescription());
                iPostLogService.addOne(postLog);
            }
            if(dfs.size()!=0){
                try {
                    String realPath = Commons.realpath + "/org/file/";
                    Long l = Calendar.getInstance().getTimeInMillis();
                    File file = new File(realPath + "/" + l);
                    file.mkdirs();
                    String dutyfile = "/org/file/" + l+ "/" + dfs.get(0).getOriginalFilename();
                    dfs.get(0).transferTo(new File(realPath + "/" + l,dfs.get(0).getOriginalFilename()));
                    post.setDutyfile(dutyfile);
                    postLog.setChangeinformation("岗位说明书");
                    postLog.setBeforeinformation(post2.getDutyfile());
                    postLog.setAfterinformation(dutyfile);
                    iPostLogService.addOne(postLog);
                    b = true;
                } catch (IOException e) {
                    return "提交失败！";
                }
            }

            if (b) {
                HRsetFunctionalType hRsetFunctionalType1 = ihRsetFunctionalTypeService.queryByFuctionaltype(post.getFunctionaltype());
                if (hRsetFunctionalType1!=null) {
                    post.setFunctionaltypeid(hRsetFunctionalType1.getId());
                }
                HRsetPostlevel hRsetPostlevel1 = ihRsetPostlevelService.queryByPostlevel(post.getPostlevel());
                if (hRsetPostlevel1!=null) {
                    post.setPostlevelid(hRsetPostlevel1.getId());
                }
                iPostService.modifyOne(post);
            } else {
                return "没有需要修改的岗位信息！";
            }
        } catch (Exception e) {
            return "提交失败!";
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID级联删除岗位信息
     *@Date: 14:44 2018\5\2 0002
     */
    @RequestMapping("/deletePostsById")
    @ResponseBody
    public String deletePostsById(@RequestParam("id") Integer id){
        try {
            List<Integer> postids = new ArrayList<Integer>();
            postids.add(id);
            postids = getPostids(id,postids);
            if (postids.size()!=0) {
                for (int i=0;i<postids.size();i++){
                    iPostService.remove(postids.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        return "删除成功！";
    }

    // 通过递归获得需要删除的所有岗位ID
    public List<Integer> getPostids(Integer parentpostid,List<Integer> list){
        List<Post> posts = iPostService.queryByParentpostid(parentpostid);
        if(posts.size()!=0){
            for (Integer i = 0;i< posts.size();i++){
                list.add(posts.get(i).getId());
                list = getPostids(posts.get(i).getId(), list);
            }
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询岗位信息日志
     *@Date: 10:02 2018\5\3 0003
     */
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
        if(list.size()!=0){
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

    /**
     *@Author:ShiYun;
     *@Description:查询岗位日志（不分页）
     *@Date: 9:18 2018\5\24 0024
     */
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

    /**
     *@Author:ShiYun;
     *@Description:删除部门日志信息
     *@Date: 10:50 2018\5\24 0024
     */
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

    /**
     *@Author:ShiYun;
     *@Description:数据的导入
     *@Date: 15:09 2018\5\7 0007
     */
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
                if (users.size()!=0) {
                    postLog.setTransactoruserid(users.get(0).getId());
                }
                iPostLogService.addOne(postLog);
            }
        } catch (Exception e) {
            return "数据导入失败！";
        }
        return "数据导入成功！";
    }
}