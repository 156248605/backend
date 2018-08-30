package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.Post;

import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:岗位信息的服务层接口
 * @Date:Created in  11:00 2018\3\20 0020
 * @Modify By:
 */
public interface IPostService  {
    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID查询岗位信息
     *@Date: 11:00 2018\3\20 0020
     */
    Post queryOneByPostid(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 15:12 2018\4\8 0008
     */
    Post queryOneByPostname(String postname);

    /**
     *@Author:ShiYun;
     *@Description:根据岗位编号查询岗位信息
     *@Date: 15:30 2018\8\11 0011
     */
    Post queryOneByPostcode(String postcode);

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位信息
     *@Date: 11:17 2018\4\11 0011
     */
    List<Post> queryAllPosts();

    /**
     *@Author:ShiYun;
     *@Description:根据parentpostid查询岗位信息
     *@Date: 9:45 2018\4\23 0023
     */
    List<Post> queryByParentpostid(Integer parentpostid);

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息并返回主键
     *@Date: 13:07 2018\4\23 0023
     */
    Integer addOne(Post post);

    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:10 2018\5\2 0002
     */
    void modifyOne(Post post);

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID删除岗位信息
     *@Date: 14:51 2018\5\2 0002
     */
    void remove(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位（去除下级岗位和本生）
     *@Date: 13:42 2018\8\30 0030
     */
    List<Post> queryPostsRemoveChilren(Integer postid);

    /**
     *@Author:ShiYun;
     *@Description:
     *@param parentpostid:高级节点
     *@param childpostid:低级节点
     *@return 如果childpostid是parentpostid的子节点则返回true,否则返回false
     *@Date: 13:49 2018\8\30 0030
     */
    Boolean isChildPoint(Integer parentpostid,Integer childpostid);
}
