package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IPerandpostrsDao;
import com.elex.oa.dao.dao_shiyun.IPostDao;
import com.elex.oa.entity.entity_shiyun.Post;
import com.elex.oa.service.service_shiyun.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:部门信息服务层的实现类
 * @Date:Created in  11:04 2018\3\20 0020
 * @Modify By:
 */
@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostDao iPostDao;
    @Autowired
    IPerandpostrsDao iPerandpostrsDao;

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID查询岗位信息
     *@Date: 11:18 2018\4\11 0011
     */
    @Override
    public Post queryOneByPostid(Integer id) {
        Post post = iPostDao.selectPostByPostid(id);
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 11:19 2018\4\11 0011
     */
    @Override
    public Post queryOneByPostname(String postname) {
        Post post = iPostDao.selectPostByPostname(postname);
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位编号查询岗位信息
     *@Date: 15:32 2018\8\11 0011
     */
    @Override
    public Post queryOneByPostcode(String postcode) {
        Post post = iPostDao.selectPostByPostcode(postcode);
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位信息
     *@Date: 11:19 2018\4\11 0011
     */
    @Override
    public List<Post> queryAllPosts() {
        List<Post> posts = iPostDao.selectAllPosts();
        return posts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据parentpostid查询岗位信息
     *@Date: 9:46 2018\4\23 0023
     */
    @Override
    public List<Post> queryByParentpostid(Integer parentpostid) {
        List<Post> posts = iPostDao.selectPostsByParentpostid(parentpostid);
        return posts;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息并返回主键
     *@Date: 13:08 2018\4\23 0023
     */
    @Override
    public Integer addOne(Post post) {
        Integer integer = iPostDao.insertOne(post);
        return post.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:11 2018\5\2 0002
     */
    @Override
    public void modifyOne(Post post) {
        iPostDao.updateOne(post);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID删除岗位信息
     *@Date: 14:53 2018\5\2 0002
     */
    @Override
    public void remove(Integer id) {
        /*先删除关系表中的相关数据*/
        iPerandpostrsDao.deleteByPostid(id);
        /*再删除相应的岗位信息*/
        iPostDao.deleteOne(id);
    }
}
