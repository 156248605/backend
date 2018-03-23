package com.elex.oa.util;

import com.elex.oa.dao.IMiDbIdDao;
import com.elex.oa.entity.MiDbId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/22.
 */
@Component
public class DefaultIdGenerator implements IdGenerator {
    @Autowired
    public IMiDbIdDao miDbIdDao;
    public JdbcTemplate jdbcTemplate;
    private boolean isUseGuid = false;
    private final Long increaseBound = Long.valueOf(10000L);
    private Long dbid = Long.valueOf(1L);
    private Long maxDbid = Long.valueOf(-1L);
    private Long machineDbid = Long.valueOf(1L);
    private String machineName;

    public DefaultIdGenerator() {
    }

    public boolean isUseGuid() {
        return this.isUseGuid;
    }

    public void setUseGuid(boolean isUseGuid) {
        this.isUseGuid = isUseGuid;
    }

    public synchronized Long getLID() {
        if(this.dbid.longValue() >= this.maxDbid.longValue()) {
            this.genNextDbIds();
        }

        Long var2 = this.dbid;
        Long var3 = this.dbid = Long.valueOf(this.dbid.longValue() + 1L);
        return Long.valueOf(var2.longValue() + this.machineDbid.longValue() * 10000000000000L);
    }

    public synchronized String getSID() {
        return this.isUseGuid? UUID.randomUUID().toString():this.getLID().toString();
    }

    public void genNextDbIds() {
//        String sql = "UPDATE MI_DB_ID SET START_=?,MAX_=? WHERE ID_=?";
        this.dbid = this.maxDbid;
        this.maxDbid = Long.valueOf(this.maxDbid.longValue() + this.increaseBound.longValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",this.machineDbid);
        paramMap.put("start",this.dbid);
        paramMap.put("max",this.maxDbid);
        miDbIdDao.updateMiDbIdById(paramMap);
//        this.jdbcTemplate.update(sql, new Object[]{this.dbid, this.maxDbid, this.machineDbid});
    }

    public void afterPropertiesSet() throws Exception {
        String machineName = "1";
//        String sql = "select * from MI_DB_ID where MAC_NAME_=?";

        try {
//            this.jdbcTemplate.queryForObject(sql, new RowMapper() {
//                public Object mapRow(ResultSet rs, int i) throws SQLException {
//                    DefaultIdGenerator.this.dbid = Long.valueOf(rs.getLong("START_"));
//                    DefaultIdGenerator.this.maxDbid = Long.valueOf(rs.getLong("MAX_"));
//                    DefaultIdGenerator.this.machineDbid = Long.valueOf(rs.getLong("ID_"));
//                    return DefaultIdGenerator.this.machineDbid;
//                }
//            }, new Object[]{machineName});
            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("macName","1");
            MiDbId miDbId = miDbIdDao.getMiDbIdByMacName(paramMap);
            this.dbid = Long.valueOf(miDbId.getStart().toString());
            this.maxDbid = Long.valueOf(miDbId.getMax().toString());
            this.machineDbid = Long.valueOf(miDbId.getId().toString());

            this.genNextDbIds();
        } catch (Exception var9) {
//            String maxSql = "select max(ID_) from MI_DB_ID";
//            Number maxNum = (Number)this.jdbcTemplate.queryForObject(maxSql, Integer.class);
            Map<String,Object> returnMap = miDbIdDao.getMaxId();
            Integer maxNum = Integer.parseInt(returnMap.get("maxId").toString());
            Integer maxResult = null;
            if(maxNum == null) {
                maxResult = Integer.valueOf(1);
            } else {
                maxResult = Integer.valueOf(maxNum.intValue());
            }

            if(maxResult.intValue() == 0) {
                maxResult = Integer.valueOf(1);
            } else {
                maxResult = Integer.valueOf(maxResult.intValue() + 1);
            }

            this.machineDbid = new Long((long)maxResult.intValue());
            this.maxDbid = Long.valueOf(this.dbid.longValue() + this.increaseBound.longValue());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("id",this.machineDbid);
            paramMap.put("start",this.dbid);
            paramMap.put("max",this.maxDbid);
            paramMap.put("macName",machineName);
            miDbIdDao.insertMiDbId(paramMap);
//            sql = "INSERT INTO MI_DB_ID(ID_,START_,MAX_,MAC_NAME_)VALUES(?,?,?,?)";
//            this.jdbcTemplate.update(sql, new Object[]{this.machineDbid, this.dbid, this.maxDbid, machineName});
        }

    }

    public Long getDbid() {
        return this.dbid;
    }

    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }

    public Long getMaxDbid() {
        return this.maxDbid;
    }

    public void setMaxDbid(Long maxDbid) {
        this.maxDbid = maxDbid;
    }

    public Long getMachineDbid() {
        return this.machineDbid;
    }

    public void setMachineDbid(Long machineDbid) {
        this.machineDbid = machineDbid;
    }

    public String getMachineName() {
        return this.machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
