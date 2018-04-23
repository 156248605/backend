package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepositoryMapper {
    List<Repository> RepositoryList();

    List<Repository> searchRepository(Repository repository);

    void insertRepository(Repository repository);

    void changeRepository(Repository repository);

    Repository findPosition(Repository repository);

    void deleteRepository(Repository repository);

    void newRepository(Repository repository);

    Repository Position(Repository repository);

    void updRepository(Repository repository);

    Repository Num(Repository repository);

    void outRepository(Repository repository);

    List<Repository> searchIn(Repository repository);
    List<Repository> searchOut(Repository repository);
}
