package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:部门信息的持久层
 * @Date:Created in  11:07 2018\3\20 0020
 * @Modify By:
 */
@Mapper
public interface IPostDao  {
    List<Map<String,Object>> selectByPostlist(@Param("postList") List<String> postList);
    List<Map<String,Object>> selectAllPostOfIdPostcodePostnameStateON();

    //根据岗位ID查询岗位信息
    Post selectPostByPostid(Integer id);

    //根据岗位名称查询岗位信息
    Post selectPostByPostname(String postname);

    List<String> getAllPostnames();

    /**
     *@Author:ShiYun;
     *@Description:根据岗位编号查询岗位信息
     *@Date: 15:27 2018\8\11 0011
     */
    Post selectPostByPostcode(String postcode);
    Post selectPostByPostcodePostON(String postcode);
    Post selectPostByPostcodePostOFF(String postcode);

    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位信息
     *@Date: 11:15 2018\4\11 0011
     */
    public List<Post> selectAllPosts();

    /**
     *@Author:ShiYun;
     *@Description:根据parentpostid
     *@Date: 9:41 2018\4\23 0023
     */
    public List<Post> selectPostsByParentpostid(@Param("parentpostid") Integer parentpostid);

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息并返回主键
     *@Date: 11:57 2018\4\23 0023
     */
    public Integer insertOne(Post post);

    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:04 2018\5\2 0002
     */
    public void updateOne(Post post);

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID删除岗位信息
     *@Date: 14:49 2018\5\2 0002
     */
    void deleteOne(Integer id);

    void deleteByChangeStatePostOFF(Integer id);

    List<Map<String, Object>> getAllPostidAndPostnameByPostON();

    String selectPostlistToStringByUserid(Integer userid);
}
