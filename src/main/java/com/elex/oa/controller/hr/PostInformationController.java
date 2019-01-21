package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.elex.oa.entity.hr_entity.*;
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


/**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 10:40 2018\3\20 0020
     */
    @RequestMapping("/queryOnePostByPostid")
    @ResponseBody
    public Post queryOnePostByPostid(@RequestParam("id") Integer id){
        Post post = iPostService.queryOnePostByPostid(id);
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位编号查询岗位信息
     *@Date: 19:48 2018\8\11 0011
     */
    @RequestMapping("/queryOnePostByPostcode")
    @ResponseBody
    public Post queryOnePostByPostcode(
            @RequestParam("code")String code
    ){
        Post post = iPostService.queryOnePostByPostcode(code);
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

    /**
     *@Author:ShiYun;
     *@Description:选择上级岗位
     *@Date: 13:39 2018\8\30 0030
     */
    @RequestMapping("/queryPostsRemoveChilren")
    @ResponseBody
    public List<Post> queryPostsRemoveChilren(
            @RequestParam("postid")Integer postid
    ){
        List<Post> posts = iPostService.queryPostsRemoveChilren(postid);
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
        if(null==posts || posts.size()==0)return deptTree;
        deptTree.setTitle(posts.get(0).getPostname());
        deptTree.setCode(posts.get(0).getPostcode());
        deptTree.setId(posts.get(0).getId());
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

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息
     *@Date: 11:55 2018\4\23 0023
     */
    @RequestMapping("/addOnePost")
    @ResponseBody
    public Object addOnePost(
            Post post,
            HttpServletRequest request
    ){
        post.setDutyfile(hrUtils.getSignalFileAddress(request,"df","/org/file/"));
        return iPostService.addOnePost(post);
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
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:03 2018\5\2 0002
     */
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

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID级联删除岗位信息
     *@Date: 14:44 2018\5\2 0002
     */
    @RequestMapping("/deletePostsById")
    @ResponseBody
    public Object deletePostsById(@RequestParam("id") Integer id){
        return iPostService.deletePostsById(id);
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

    /**
     *@Author:ShiYun;
     *@Description:岗位排序--》根据父节点查询所有的同级数据
     *@Date: 11:15 2018\6\15 0015
     */
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
            titleAndCode.setId(posts.get(i).getId());
            list.add(titleAndCode);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:对数据进行排序
     *@Date: 14:52 2018\6\15 0015
     */
    @RequestMapping("/submitSortdata2")
    @ResponseBody
    public DeptTree submitSortdata2(
            @RequestParam("sortdata") String sortdata,
            @RequestParam("title") String title,
            @RequestParam("deptTree") String deptTree
    ){
        List<TitleAndCode> list = JSONObject.parseArray(sortdata, TitleAndCode.class);
        //先将树形结构数据查出来
        List<Post> posts = iPostService.queryByParentpostid(null);
        DeptTree deptTree1 = JSONObject.parseObject(deptTree,new TypeReference<DeptTree>(){});
        //先排序
        if(title!=null && !"".equals(title) && list.size()>=2){
            list.sort(new Comparator<TitleAndCode>() {
                @Override
                public int compare(TitleAndCode o1, TitleAndCode o2) {
                    return o1.getCode().compareTo(o2.getCode());
                }
            });
        }
        for (TitleAndCode t:list
             ) {
            Post post = iPostService.queryOneByPostname(t.getTitle());
            Post post1 = new Post();
            post1.setId(post.getId());
            post1.setOrdercode(t.getCode());
            iPostService.modifyOne(post1);
        }
        //再换节点
        //首先将父节点是定点的情况去除
        if(deptTree1.getTitle().equals(title)){
            List<DeptTree> old = deptTree1.getChildren();
            List<DeptTree> renew = new ArrayList<>();
            for (TitleAndCode titleAndCode:list
                    ) {
                for (DeptTree dtt:old
                        ) {
                    if(dtt.getTitle().equals(titleAndCode.getTitle())){
                        renew.add(dtt);
                    }
                }
            }
            deptTree1.setChildren(renew);
            return deptTree1;
        }
        DeptTree newDepttree = getNewDepttree(deptTree1, title, list);
        return newDepttree;
    }

    @RequestMapping("/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            @RequestParam("postcode")String postcode
    ){
        Boolean aBoolean = iPostService.validateByPostcode(postcode);
        return aBoolean? RespUtil.successResp("200","岗位编号已存在！",null):RespUtil.successResp("500","岗位编号不存在",null);
    }

    @RequestMapping("/getRecommendedPostcode")
    @ResponseBody
    public String getRecommendedPostcode(){
        return iPostService.getRecommendedPostcode();
    }

    /**
     *@Author:ShiYun;
     *@Description:同级排序
     *@Date: 18:41 2018\6\15 0015
     */
    public DeptTree getNewDepttree(DeptTree deptTree,String title,List<TitleAndCode> list){
        for(int i = 0;i<deptTree.getChildren().size();i++){
            if(deptTree.getChildren().get(i).getTitle().equals(title)){
                List<DeptTree> old = deptTree.getChildren().get(i).getChildren();
                List<DeptTree> renew = new ArrayList<>();
                for (TitleAndCode titleAndCode:list
                        ) {
                    for (DeptTree dtt:old
                            ) {
                        if(dtt.getTitle().equals(titleAndCode.getTitle())){
                            renew.add(dtt);
                        }
                    }
                }
                deptTree.getChildren().get(i).setChildren(renew);
                return deptTree;
            }else {
                if(deptTree.getChildren().get(i).getChildren().size()>0){
                    DeptTree parentDepttree = getNewDepttree(deptTree.getChildren().get(i), title,list);
                    if(parentDepttree!=null){
                        deptTree.getChildren().get(i).setChildren(parentDepttree.getChildren());
                        return deptTree;
                    }
                }
            }
        }
        return null;
    }
}
