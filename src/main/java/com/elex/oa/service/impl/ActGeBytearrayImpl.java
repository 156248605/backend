package com.elex.oa.service.impl;
import com.elex.oa.dao.IActGeByteArrayDao;
import com.elex.oa.entity.ActGeBytearray;
import com.elex.oa.service.IActGeBytearrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import tk.mybatis.mapper.entity.Example;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import java.sql.PreparedStatement;
import org.springframework.jdbc.support.lob.LobCreator;
/**
 *@author hugo.zhao
 *@since 2018/4/9 13:37
*/
@Service
public class ActGeBytearrayImpl extends BaseServiceImpl<ActGeBytearray> implements IActGeBytearrayService {

    @Autowired
    private IActGeByteArrayDao actGeByteArrayDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String getDefXmlByDeployId(String deployId) {
        Example example = new Example(ActGeBytearray.class);
        Example.Criteria criteria = example.createCriteria();
        //任务名称
        if(deployId !=null && deployId.length()>0){
            criteria.andEqualTo("DEPLOYMENTID",deployId);
        }
          criteria.andLike("NAME","%bpmn20.xml");
        List<ActGeBytearray> actGeBytearrays = actGeByteArrayDao.selectByExample(example);
        ActGeBytearray actGeBytearray = actGeBytearrays.get(0);
        final ByteArrayOutputStream contentOs = new ByteArrayOutputStream();
        String defXml = null;
        try {
            FileCopyUtils.copy(actGeBytearray.getBYTES(),contentOs);
            defXml = new String(contentOs.toByteArray(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return defXml;
    }
    public void writeDefXml(final String deployId, String defXml){
        try {
            final DefaultLobHandler ex = new DefaultLobHandler();
            final byte[] btyesXml = defXml.getBytes("UTF-8");
            String sql = "update ACT_GE_BYTEARRAY set BYTES_=? where NAME_ LIKE \'%bpmn20.xml\' and DEPLOYMENT_ID_= ? ";
            this.jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(ex) {
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                    lobCreator.setBlobAsBytes(ps, 1, btyesXml);
                    ps.setString(2, deployId);
                }
            });

        }catch (Exception var6){
            var6.printStackTrace();
        }


    }



}
