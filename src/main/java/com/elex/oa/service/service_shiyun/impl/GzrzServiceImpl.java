package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.dao.dao_shiyun.IGzrzDao;
import com.elex.oa.dao.dao_shiyun.IPersonalInformationDao;
import com.elex.oa.entity.entity_shiyun.Gzrz;
import com.elex.oa.entity.entity_shiyun.GzrzVO;
import com.elex.oa.service.service_shiyun.IGzrzService;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import static com.elex.oa.util.util_shiyun.IDcodeUtil.*;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  15:46 2018\8\2 0002
 * @Modify By:
 */
@Service
public class GzrzServiceImpl implements IGzrzService {
    @Autowired
    private IGzrzDao iGzrzDao;

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志填写的统计信息
     *@Date: 15:47 2018\8\2 0002
     */
    @Override
    public Object queryGzrzByTime(Date date) {
        List<GzrzVO> gzrzVOList = new ArrayList<>();
        try {
            Map map = getFirstAndLastDate(date);
            Date firstDate = (Date) map.get("firstDate");
            Date lastDate = (Date) map.get("lastDate");
            String[] strings = iGzrzDao.selectNamesByDateAndState(firstDate, lastDate);
            List<String> list = Arrays.asList(strings);
            List<String> truenames = new ArrayList<String>(list);
            for (String s:truenames
                 ) {
                GzrzVO gzrzVO = new GzrzVO();
                //添加姓名
                gzrzVO.setTruename(s);
                //添加部门
                String s1 = iGzrzDao.selectDepnameByTruename(s);
                if(s1==""){
                    s1 = "此员工无部门";
                }
                gzrzVO.setDepname(s1);
                //添加工作日志数量
                List<Gzrz> gzrzs = iGzrzDao.selectGzrzWriteByDateAndStateAndTruename(firstDate, lastDate, null, s);
                gzrzVO.setWorkday(gzrzs.size());
                //添加休息日


            }
        } catch (ParseException e) {
            System.out.println("根据时间获得当月的头尾日期时，格式出错！");
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志审查的统计信息
     *@Date: 15:48 2018\8\2 0002
     */
    @Override
    public Object queryGzrzByTime2(Date date) {
        try {
            Map map = getFirstAndLastDate(date);
            Date firstDate = (Date) map.get("firstDate");
            Date lastDate = (Date) map.get("lastDate");
            String[] strings = iGzrzDao.selectNamesByDateAndState(firstDate, lastDate);
            List<String> list = Arrays.asList(strings);
            List<String> truenames = new ArrayList<String>(list);
        } catch (ParseException e) {
            System.out.println("根据时间获得当月的头尾日期时，格式出错！");
        }
        return null;
    }
}
