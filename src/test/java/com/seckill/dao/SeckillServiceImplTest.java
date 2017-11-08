package com.seckill.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-dao.xml", "classpath:application-service.xml" })
public class SeckillServiceImplTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("seckill={}", list);
	}

	@Test
	public void testGetById() {
		long id=1004L;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long id=1004L;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
//		exposed=true, 
//		md5=4d3db88e656122862702ea8201807338, 
//		seckillId=1004,
//		nowTime=0, 
//		startTime=0,
//		endTime=0
	}

	@Test
	public void testExecuteSeckill() {
		long seckillId=1004L;
		long userPhone=222L;
		String md5="4d3db88e656122862702ea8201807338";
		SeckillExecution result = seckillService.executeSeckill(seckillId, userPhone, md5);
		logger.info("result={}",result);
		/**
		 * result=SeckillExecution 
			 * [seckillId=1004,
			 *  state=1, 
			 *  stateInfo=秒杀成功,
			 *  successSeckilled=
			 *  	SuccessSeckilled [
			 *  		seckillId=1004, 
			 *  		userPhone=222,
			 *  		state=0,
			 *  		createTime=Wed Nov 08 21:45:21 CST 2017,
			 *  		seckill=Seckill 
			 *  			[seckillId=1004,
			 *  			 name=null,
			 *  			 number=98, 
			 *  			 endTime=Thu Nov 23 00:00:00 CST 2017,
			 *  			 createTime=Tue Nov 07 10:39:36 CST 2017]]]	 startTime=Wed Nov 08 21:45:21 CST 2017, 
			 *  		

		 */
	}
	
	@Test
	public void testExecuteKill() {
		long seckillId=1004L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		
		if (exposer.isExposed()) {
			try {
				long userPhone = 333L;
				String md5 = exposer.getMd5();
				SeckillExecution result = seckillService.executeSeckill(seckillId, userPhone, md5);
				logger.info("result={}", result);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			} catch (SeckillException e) {
				logger.error(e.getMessage());
			} 
		}else {
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
		
	}

}
