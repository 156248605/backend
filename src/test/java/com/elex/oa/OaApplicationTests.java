package com.elex.oa;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.service.hr_service.IHRsetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {



	@Test
	public void contextLoads() {
		Properties prop = new Properties();
		InputStream in = OaApplication.class.getClassLoader().getResourceAsStream("config/HR_API_Conf.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("swagger.api.title")+"==============================================");
		System.out.println(prop.getProperty("swagger.api.version")+"==============================================");
		System.out.println(prop.getProperty("swagger.base-package")+"==============================================");
	}

}
