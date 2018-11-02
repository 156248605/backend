package com.elex.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {


	@Test
	public void contextLoads() {
		Scanner scanner = new Scanner(System.in);
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
