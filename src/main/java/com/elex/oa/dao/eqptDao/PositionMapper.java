package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper {
    List<Repository> PositionList();

    List<Repository> searchPosition(Repository repository);

    void insertPosition(Repository repository);

    void changePosition(Repository repository);

    Repository locationPosition(Repository repository);

    String showPosition(Repository repository);

    void deletePosition(Repository repository);

    List<Repository> reptlist();

    List<Repository> reptname(Repository repository);

    String checkPostId(Repository repository);

    void deletePostManage(Repository repository);

    String recordin(Repository repository);

    String recordout(Repository repository);

    String recordshift(Repository repository);
}
