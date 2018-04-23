package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Partner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartnerMapper {


    List<Partner> PartnerList();

    List<Partner> searchPartner(Partner partner);

    void insertPartner(Partner partner);

    void changePartner(Partner partner);

    Partner findName(Partner partner);

    void deletePartner(Partner partner);
}
