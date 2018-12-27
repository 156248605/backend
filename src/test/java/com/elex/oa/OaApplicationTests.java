package com.elex.oa;

import com.elex.oa.dao.hr.IDimissionInformationDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.entity.hr_entity.DimissionInformation;
import com.elex.oa.service.hr_service.IHRsetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {
	@Resource
	IDimissionInformationDao iDimissionInformationDao;


	@Test
	public void contextLoads() {
		List<DimissionInformation> dimissionInformations = iDimissionInformationDao.selectByCondition(null);
		System.out.println(dimissionInformations);
	}

}
