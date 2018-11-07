package com.elex.oa;

import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.service.hr_service.IHRsetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {



	@Test
	public void contextLoads() {
		Scanner scanner = new Scanner(System.in);
		String expected = "\"id\":1,\"name\":\"MacBook\",\"price\":1000";
		String str =scanner.next();
		//String str = "2017/7/9";
		String[] strs = str.split("/");
		if(strs.length==3){
			String year = strs[0];
			String month = strs[1];
			month = month.length()==2?month:"0"+month;
			String day = strs[2];
			day = day.length()==2?day:"0"+day;
			str = year+"/"+month+"/"+day;
		}
		System.out.println(str);
	}

}
