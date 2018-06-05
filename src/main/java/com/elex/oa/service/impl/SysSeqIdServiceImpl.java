package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysSeqIdDao;
import com.elex.oa.entity.core.SysSeqId;
import com.elex.oa.service.ISysSeqIdService;
import com.elex.oa.util.DateUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class SysSeqIdServiceImpl implements ISysSeqIdService {

    @Resource
    private ISysSeqIdDao sysSeqIdDao;
    @Override
    public String genSequenceNo(String alias, String tenantId) {
        SysSeqId identity = this.getByAlias(alias, tenantId);
        if(identity == null) {
            //this.logger.warn("流水号(" + alias + "--tenantId:" + tenantId + ")不存在================");
            return "";
        } else {
            return this.getSequenceId(identity);
        }
    }

    @Override
    public SysSeqId getByAlias(String alias, String tenantId) {
        SysSeqId sysSeqId = new SysSeqId();
        sysSeqId.setAlias(alias);
        sysSeqId.setTenantId(tenantId);
        return  sysSeqIdDao.selectOne(sysSeqId);
    }

    @Override
    public SysSeqId getById(String Id) {
        return sysSeqIdDao.selectByPrimaryKey(Id);
    }

    private synchronized String getSequenceId(SysSeqId identity) {
        String rule = identity.getRule();
        String genType = identity.getGenType();
        Integer curValue = identity.getCurVal();
        if(curValue == null) {
            curValue = identity.getInitVal();
        }

        Date oldDate = identity.getCurDate() == null?new Date():identity.getCurDate();
        int curTimeVal = 0;
        int oldTimeVal = 0;
        if("DAY".equals(genType)) {
            curTimeVal = DateUtil.getCurDay();
            oldTimeVal = DateUtil.getDay(oldDate);
        } else if("WEEK".equals(genType)) {
            curTimeVal = DateUtil.getCurWeekOfYear();
            oldTimeVal = DateUtil.getWeekOfYear(oldDate);
        } else if("MONTH".equals(genType)) {
            curTimeVal = DateUtil.getCurMonth();
            oldTimeVal = DateUtil.getMonth(oldDate);
        } else if("YEAR".equals(genType)) {
            curTimeVal = DateUtil.getCurYear();
            oldTimeVal = DateUtil.getYear(oldDate);
        }

        if(curTimeVal != oldTimeVal) {
            oldDate = new Date();
            curValue = identity.getInitVal();
        } else {
            curValue = Integer.valueOf(curValue.intValue() + identity.getStep().shortValue());
        }

        identity.setCurVal(curValue);
        identity.setCurDate(oldDate);
       // this.update(identity);
        sysSeqIdDao.updateByPrimaryKeySelective(identity);
        String rtn = this.getByRule(rule, identity.getLen().intValue(), curValue.intValue());
        return rtn;
    }

    private String getByRule(String rule, int length, int curValue) {
        Calendar now = Calendar.getInstance();
        DecimalFormat nf = new DecimalFormat("00");
        int year = now.get(1);
        int month = now.get(2) + 1;
        int day = now.get(5);
        StringBuffer sb = new StringBuffer();

        for(int fullDateFormat = 0; fullDateFormat < length; ++fullDateFormat) {
            sb.append("0");
        }

        SimpleDateFormat var15 = new SimpleDateFormat("yyyMMdd");
        SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyMM");
        DecimalFormat seqFt = new DecimalFormat(sb.toString());
        String seqNo = seqFt.format((long)curValue);
        String rtn = rule.replace("{yyyy}", year + "").replace("{yy}", nf.format((long)year)).replace("{MM}", nf.format((long)month)).replace("{mm}", month + "").replace("{DD}", nf.format((long)day)).replace("{dd}", day + "").replace("{NO}", seqNo).replace("{no}", curValue + "").replace("{yyyyMM}", shortDateFormat.format(now.getTime())).replace("{yyyyMMdd}", var15.format(now.getTime()));
        return rtn;
    }


}
