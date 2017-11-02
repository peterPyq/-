package com.seckill.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.SuccessSeckilled;

/**
 * @describe SuccessKilledDaoTest单元测试类
 * @author pyq 
 * 2017年11月2日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-dao.xml" })
public class SuccessKilledDaoTest {

	@Autowired
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		long id = 1000L;
		long phone = 18627990000L;
		int insertCount = successKilledDao.insertSuccessKilled(id, phone);
		System.out.println(insertCount);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long id = 1000L;
		long phone = 18627990000L;
		SuccessSeckilled successSeckilled = successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println(successSeckilled);
		System.out.println(successSeckilled.getSeckill());
	}

}
