package com.elex.oa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OaApplicationTests {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Test
	public void contextLoads() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(weekDay);
	}

	@Test
	public void weekTest() {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int offset = 1 - dayOfWeek;
		calendar.add(Calendar.DATE,offset - 7);
		String lastBeginDate = simpleDateFormat.format(calendar.getTime());
		System.out.println(lastBeginDate);
	}

	@Test
	public void one() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String start = simpleDateFormat.format(calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		String end = simpleDateFormat.format(calendar1.getTime());
		System.out.println("start: "+start);
		System.out.println("end: "+end);
	}
}
