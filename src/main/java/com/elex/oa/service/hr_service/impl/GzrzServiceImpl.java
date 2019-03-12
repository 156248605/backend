package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IGzrzDao;
import com.elex.oa.entity.hr_entity.Gzrz;
import com.elex.oa.entity.hr_entity.GzrzVO;
import com.elex.oa.entity.hr_entity.Lysqd;
import com.elex.oa.service.hr_service.IGzrzService;
import com.elex.oa.util.hr_util.HrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  15:46 2018\8\2 0002
 * @Modify By:
 */
@Service
public class GzrzServiceImpl implements IGzrzService {
    @Resource
    IGzrzDao iGzrzDao;
    @Resource
    HrUtils hrUtils;

    private static Logger logger = LoggerFactory.getLogger(GzrzServiceImpl.class);

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志填写的统计信息
     *@Date: 15:47 2018\8\2 0002
     */
    @Override
    public Object queryGzrzByTime(Date date) {
        List<GzrzVO> gzrzVOList = new ArrayList<>();
        Map map = hrUtils.getFirstAndLastDate(date);
        Date firstDate = (Date) map.get("firstDate");
        Date lastDate = (Date) map.get("lastDate");
        String[] strings = iGzrzDao.selectNamesByDateAndState(firstDate, lastDate);//包括正在运行和运行结束的日志填报人（去重之后的）
        for (String tbrName:strings
             ) {
            Class<?> c = GzrzVO.class;//反射用于拼接方法名
            GzrzVO gzrzVO = new GzrzVO();
            //添加姓名
            gzrzVO.setTruename(tbrName);
            //添加部门
            gzrzVO.setDepname(hrUtils.getDepnameByTruename(tbrName));
            //添加工作日志数量
            List<Gzrz> gzrzs = iGzrzDao.selectGzrzWriteByDateAndStateAndTruename(firstDate, lastDate, null, tbrName);
            int[] arr = new int[31];
            //添加休息日
            Integer daysByDate = hrUtils.getDaysByDate(date);
            //判断哪天填写了日志
            for (Gzrz gzrz:gzrzs
                 ) {
                String starttime = gzrz.getStarttime();
                Integer datOfMoth = hrUtils.getDaycodeByDate(starttime);
                try {
                    Method method=c.getMethod("setD"+datOfMoth, String.class);//输入拼接方法名和参数类型
                    method.invoke(gzrzVO,"√");//执行相应的方法
                } catch (Exception e) {
                    logger.info(String.valueOf(e.getCause()));
                }
                arr[datOfMoth-1] = 1;
            }
            int count = 0;
            for (int i:arr
                 ) {
                count = count + i;
            }
            gzrzVO.setWorkday(count);
            gzrzVO.setWeekday(daysByDate-count);
            if (count!=0) {
                gzrzVOList.add(gzrzVO);
            }
        }
        return gzrzVOList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询工作日志审查的统计信息
     *@Date: 15:48 2018\8\2 0002
     */
    @Override
    public Object queryGzrzByTime2(Date date) {
        List<GzrzVO> gzrzVOList = new ArrayList<>();
        Map map = hrUtils.getFirstAndLastDate(date);
        Date firstDate = (Date) map.get("firstDate");
        Date lastDate = (Date) map.get("lastDate");
        String[] strings = iGzrzDao.selectNamesByDateAndState(firstDate, lastDate);
        for (String tbrName:strings
                ) {
            Class<?> c = GzrzVO.class;
            GzrzVO gzrzVO = new GzrzVO();
            //添加姓名
            gzrzVO.setTruename(tbrName);
            //添加部门
            String s1 = iGzrzDao.selectDepnameByTruename(tbrName);
            if(s1==""||s1==null){
                s1 = "此员工无部门";
            }
            gzrzVO.setDepname(s1);
            //添加工作日志数量
            List<Gzrz> gzrzs = iGzrzDao.selectGzrzWriteByDateAndStateAndTruename(firstDate, lastDate, "SUCCESS_END", tbrName);
            gzrzVO.setWorkday(gzrzs.size());
            //添加休息日
            Integer daysByDate = hrUtils.getDaysByDate(date);
            gzrzVO.setWeekday(daysByDate-gzrzs.size());
            //判断哪天填写了日志
            for (Gzrz gzrz:gzrzs
                    ) {
                String starttime = gzrz.getStarttime();
                Integer datOfMoth = hrUtils.getDaycodeByDate(starttime);
                try {
                    Method method=c.getMethod("setD"+datOfMoth, String.class);//输入拼接方法名和参数类型
                    method.invoke(gzrzVO,"√");//执行相应的方法
                } catch (Exception e) {
                    logger.info(String.valueOf(e.getCause()));
                }
            }
            if (!gzrzs.isEmpty()) {
                gzrzVOList.add(gzrzVO);
            }
        }
        return gzrzVOList;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询录用审批单通过的信息
     *@Date: 14:58 2018\9\28 0028
     */
    @Override
    public List<Lysqd> queryLyspd() {
        return iGzrzDao.selectLysqd();
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询一条录用申请单
     *@Date: 17:10 2018\9\28 0028
     */
    @Override
    public Lysqd queryLysqdById(String id) {
        return iGzrzDao.selectLysqdById(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:修改录用申请单
     *@Date: 17:31 2018\9\28 0028
     */
    @Override
    public void modifyLysqdById(String id) {
        iGzrzDao.updateLysqd(id);
    }
}
