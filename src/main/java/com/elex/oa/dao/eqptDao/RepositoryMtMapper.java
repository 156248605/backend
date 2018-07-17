package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepositoryMtMapper {
    List<Repository> RepositoryMtList();

    List<Repository> SearchRepositoryMt(Repository repository);

    void saveRepositoryMt(Repository repository);

    void newRepositoryMt(Repository repository);

    void deleteRepositoryMt(Repository repository);

    String searchPostCap (Repository repository);

    List<Material> matlist();

    void updMatFix(Material material);

    void updOtherFix(Material material);
}
