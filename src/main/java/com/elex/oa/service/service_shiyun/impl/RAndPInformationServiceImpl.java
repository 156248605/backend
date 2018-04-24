package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IRAndPInformationDao;
import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.entity.entity_shiyun.RAndPInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IRAndPInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:奖惩信息（业务层）
 * @Date:Created in  9:11 2018\4\18 0018
 * @Modify By:
 */
@Service
public class RAndPInformationServiceImpl implements IRAndPInformationService {
    @Autowired
    IRAndPInformationDao irAndPInformationDao;
    @Autowired
    IUserDao iUserDao;

    /**
     *@Author:ShiYun;
     *@Description:添加奖惩信息并返回主键
     *@Date: 9:12 2018\4\18 0018
     */
    @Override
    public Integer addOne(RAndPInformation rAndPInformation) {
        Integer integer = irAndPInformationDao.insertOne(rAndPInformation);
        Integer rAndPInformationId = rAndPInformation.getId();
        return rAndPInformationId;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的奖惩信息
     *@Date: 20:54 2018\4\18 0018
     */
    @Override
    public List<RAndPInformation> queryAll() {
        List<RAndPInformation> rAndPInformations = irAndPInformationDao.selectAll();
        return rAndPInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的奖惩名目
     *@Date: 9:31 2018\4\19 0019
     */
    @Override
    public List<RAndPInformation> queryAllRAndPProject() {
        List<RAndPInformation> rAndPInformations = irAndPInformationDao.selectAll();
        List<RAndPInformation> list = new ArrayList<RAndPInformation>();
        for(Integer i = 0;i<rAndPInformations.size();i++){
            Boolean b = true;
            for(Integer j = 0;j<list.size();j++){
                if(rAndPInformations.get(i).getRandpproject().equals(list.get(j).getRandpproject())){
                    b = false;
                }
            }
            if (b) {
                list.add(rAndPInformations.get(i));
            }
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询奖惩信息（分页）
     *@Date: 11:28 2018\4\19 0019
     */
    @Override
    public com.elex.oa.util.util_shiyun.PageHelper<RAndPInformation> queryByCondition(HashMap<String, Object> paramMap) {
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());

        RAndPInformation rAndPInformation = (RAndPInformation) paramMap.get("entity");
        List<RAndPInformation> list = irAndPInformationDao.selectByCondition(rAndPInformation);
        for(Integer i = 0;i<list.size();i++){
            User user = iUserDao.selectById(list.get(i).getUserid());
            list.get(i).setTruename(user.getTruename());
            User user1 = iUserDao.selectById(list.get(i).getTransactoruserid());
            list.get(i).setTransactortruename(user1.getTruename());
        }
        List<RAndPInformation> rAndPInformations = new ArrayList<RAndPInformation>();

        String changedate = paramMap.get("changedate").toString();

        if(changedate!=null && !changedate.equals("")){
            String[] split = changedate.split(",");
            Long minDate = Long.valueOf(split[0].replace("/",""));
            Long maxDate = Long.valueOf(split[1].replace("/",""));
            for(Integer i = 0;i<list.size();i++){
                Long curDate = Long.valueOf(list.get(i).getRandpdate().replace("/",""));
                if(curDate>=minDate && curDate<=maxDate){
                    rAndPInformations.add(list.get(i));
                }
            }
        }else {
            rAndPInformations = list;
        }

        com.elex.oa.util.util_shiyun.PageHelper<RAndPInformation> rAndPInformationPageHelper = new com.elex.oa.util.util_shiyun.PageHelper<>(pageNum, pageSize, rAndPInformations);
        return rAndPInformationPageHelper;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询奖惩信息（不分页）
     *@Date: 9:51 2018\4\20 0020
     */
    @Override
    public List<RAndPInformation> queryByCondition(RAndPInformation rAndPInformation) {
        List<RAndPInformation> list = irAndPInformationDao.selectByCondition(rAndPInformation);
        for (int i = 0;i<list.size();i++){
            User user = iUserDao.selectById(list.get(i).getTransactoruserid());
            list.get(i).setTransactortruename(user.getTruename());
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询奖惩信息
     *@Date: 16:26 2018\4\19 0019
     */
    @Override
    public RAndPInformation queryOneById(Integer id) {
        RAndPInformation rAndPInformation = irAndPInformationDao.selectOneById(id);
        User user = iUserDao.selectById(rAndPInformation.getUserid());
        rAndPInformation.setTruename(user.getTruename());
        return rAndPInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改奖惩信息
     *@Date: 19:07 2018\4\19 0019
     */
    @Override
    public void modifyOne(RAndPInformation rAndPInformation) {
        irAndPInformationDao.updateOne(rAndPInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除奖惩信息
     *@Date: 19:47 2018\4\19 0019
     */
    @Override
    public void removeOne(Integer id) {
        irAndPInformationDao.deleteOne(id);
    }
}
