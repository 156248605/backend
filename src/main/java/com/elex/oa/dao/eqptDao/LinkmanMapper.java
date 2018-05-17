package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LinkmanMapper {
    List<Linkman> LinkmanList();

    List<Linkman> searchFor(Linkman linkman);

    void newLinkman(Linkman linkman);

    void deleteLinkman(Linkman linkman);

    int importLinkman(Linkman linkman);

}
