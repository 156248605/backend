package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.DeptTree;
import com.elex.oa.entity.entity_shiyun.Post;
import com.elex.oa.service.service_shiyun.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 10:40 2018\3\20 0020
     */
    @RequestMapping("/queryOnePostByPostname")
    @ResponseBody
    public Post queryOnePostByPostname(@RequestParam("name") String name){
        Post post = iPostService.queryOneByPostname(name);
        if(post.getParentpost() == null){
            Post parentpost = new Post();
            parentpost.setPostname("无上级岗位");
            post.setParentpost(parentpost);
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
    public String addOnePost(Post post){
        Integer postid = iPostService.addOne(post);

        if(postid == post.getId()) {
            /*
            * id,name
            * post.getPostname() 即为 name
            * post.getId() 即为 id
            * */
        }
        /*Map<String,Object> result = new HashMap<>();
        if(postid == post.getId()){
            result.put("result","success");
            result.put("message","添加成功");
        } else {
            result.put("result","failure");
            result.put("message","添加失败，稍后再试");
        }
        return  result;*/
        return "提交成功!";
    }
}
