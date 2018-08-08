package com.elex.oa.dao.eqptDao;


import com.elex.oa.entity.eqpt.Linkman;
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

    //导入excel
    // int importPartner(String pnCategory,String company,String pjName,String name);

    int importPartner(Partner partner);

    List<Linkman> authorize();

    List<Linkman> authorizeId(Linkman linkman);

    List<Linkman> authorizeInfo(Linkman linkman);

    List<Linkman> otherName(Linkman linkman);

    List<Partner> getPart();

}
