package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Linkman;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
public interface LinkmanMapper {
    List<Linkman> LinkmanList();

    List<Linkman> searchFor(Linkman linkman);

    List<Linkman> search(Linkman linkman);

    void newLinkman(Linkman linkman);

    void deleteLinkman(Linkman linkman);

    int importLinkman(Linkman linkman);

    void changeLinkman(Linkman linkman);

    // 查询最大的联系人ID
    String maxLinkId();
}
