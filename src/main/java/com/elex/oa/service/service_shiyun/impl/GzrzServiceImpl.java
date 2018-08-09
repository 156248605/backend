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
                if(s1==""||s1==null){
                    s1 = "此员工无部门";
                }
                gzrzVO.setDepname(s1);
                //添加工作日志数量
                List<Gzrz> gzrzs = iGzrzDao.selectGzrzWriteByDateAndStateAndTruename(firstDate, lastDate, null, s);
                int[] arr = new int[31];
                /*gzrzVO.setWorkday(gzrzs.size());*/
                //添加休息日
                Integer daysByDate = IDcodeUtil.getDaysByDate(date);
                /*gzrzVO.setWeekday(daysByDate-gzrzs.size());*/
                //判断哪天填写了日志
                for (Gzrz gzrz:gzrzs
                     ) {
                    String starttime = gzrz.getStarttime();
                    Integer month = IDcodeUtil.getMonthByDate(starttime);//方法名写错，获得是哪一天
                    arr[month] = 1;
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
        } catch (ParseException e) {
            System.out.println("根据时间获得当月的头尾日期时，格式出错！");
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
                if(s1==""||s1==null){
                    s1 = "此员工无部门";
                }
                gzrzVO.setDepname(s1);
                //添加工作日志数量
                List<Gzrz> gzrzs = iGzrzDao.selectGzrzWriteByDateAndStateAndTruename(firstDate, lastDate, "SUCCESS_END", s);
                gzrzVO.setWorkday(gzrzs.size());
                //添加休息日
                Integer daysByDate = IDcodeUtil.getDaysByDate(date);
                gzrzVO.setWeekday(daysByDate-gzrzs.size());
                //判断哪天填写了日志
                for (Gzrz gzrz:gzrzs
                        ) {
                    String starttime = gzrz.getStarttime();
                    Integer month = IDcodeUtil.getMonthByDate(starttime);
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
        } catch (ParseException e) {
            System.out.println("根据时间获得当月的头尾日期时，格式出错！");
        }
        return gzrzVOList;
    }
}
