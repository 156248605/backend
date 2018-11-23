package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.PostRelationship;

import java.util.List;

public interface IPostRelationshipService {
    Integer addPostrp(PostRelationship postRelationship);

    List<PostRelationship> queryAllPostrelationship();

    Object removeById(List<Integer> postrp_ids);
}
