package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IGzrzDao;
import com.elex.oa.entity.hr_entity.Gzrz;
import com.elex.oa.entity.hr_entity.GzrzVO;
import com.elex.oa.entity.hr_entity.Lysqd;
import com.elex.oa.service.hr_service.IGzrzService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.hr_util.IDcodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
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
                    method.invoke(gzrzVO,new Object[]{"√"});//执行相应的方法
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
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
                Integer month = hrUtils.getDaycodeByDate(starttime);
                if(month==1){
                    gzrzVO.setD1("√");
                }
                if(month==2){
                    gzrzVO.setD2("√");
                }
                if(month==3){
                    gzrzVO.setD3("√");
                }
                if(month==4){
                    gzrzVO.setD4("√");
                }
                if(month==5){
                    gzrzVO.setD5("√");
                }
                if(month==6){
                    gzrzVO.setD6("√");
                }
                if(month==7){
                    gzrzVO.setD7("√");
                }
                if(month==8){
                    gzrzVO.setD8("√");
                }
                if(month==9){
                    gzrzVO.setD9("√");
                }
                if(month==10){
                    gzrzVO.setD10("√");
                }
                if(month==11){
                    gzrzVO.setD11("√");
                }
                if(month==12){
                    gzrzVO.setD12("√");
                }
                if(month==13){
                    gzrzVO.setD13("√");
                }
                if(month==14){
                    gzrzVO.setD14("√");
                }
                if(month==15){
                    gzrzVO.setD15("√");
                }
                if(month==16){
                    gzrzVO.setD16("√");
                }
                if(month==17){
                    gzrzVO.setD17("√");
                }
                if(month==18){
                    gzrzVO.setD18("√");
                }
                if(month==19){
                    gzrzVO.setD19("√");
                }
                if(month==20){
                    gzrzVO.setD20("√");
                }
                if(month==21){
                    gzrzVO.setD21("√");
                }
                if(month==22){
                    gzrzVO.setD22("√");
                }
                if(month==23){
                    gzrzVO.setD23("√");
                }
                if(month==24){
                    gzrzVO.setD24("√");
                }
                if(month==25){
                    gzrzVO.setD25("√");
                }
                if(month==26){
                    gzrzVO.setD26("√");
                }
                if(month==27){
                    gzrzVO.setD27("√");
                }
                if(month==28){
                    gzrzVO.setD28("√");
                }
                if(month==29){
                    gzrzVO.setD29("√");
                }
                if(month==30){
                    gzrzVO.setD30("√");
                }
                if(month==31){
                    gzrzVO.setD31("√");
                }
            }
            if (gzrzs.size()!=0) {
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
        List<Lysqd> lysqds = iGzrzDao.selectLysqd();
        return lysqds;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询一条录用申请单
     *@Date: 17:10 2018\9\28 0028
     */
    @Override
    public Lysqd queryLysqdById(String id) {
        Lysqd lysqd = iGzrzDao.selectLysqdById(id);
        return lysqd;
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
