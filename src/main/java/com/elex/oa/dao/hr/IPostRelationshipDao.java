package com.elex.oa.dao.hr;

import com.elex.oa.entity.hr_entity.hr_set.PostRelationship;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPostRelationshipDao {
    Integer insert(PostRelationship postRelationship);

    void deleteById(Integer id);

    void update(PostRelationship postRelationship);

    PostRelationship selectOneById(Integer id);

    List<PostRelationship> selectByCondition(PostRelationship postRelationship);

    List<PostRelationship> selectAll();

    List<Integer> getPostgradeidListByPostfamilyid(Integer familyid);
}
