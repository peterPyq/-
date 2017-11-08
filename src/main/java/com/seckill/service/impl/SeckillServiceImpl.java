package com.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessSeckilled;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	private final String SLAT="1pengyuquantest2";//用于制造md5值

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 10);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);

		long startTime = seckill.getStartTime().getTime();
		long endTime = seckill.getEndTime().getTime();
		long nowTime = (new Date()).getTime();

		if (nowTime >= startTime && nowTime <= endTime) {
			String md5 = this.getMD5(seckillId);
			return new Exposer(true, md5, seckillId);
		}
		return new Exposer(false, nowTime, startTime, endTime);
	}

	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, Long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(null==md5 || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite!");
		}
		try {
			int updateCount = seckillDao.reduceNumber(seckillId, new Date());
			if(updateCount<1) {
				//减库存失败
				throw new SeckillCloseException("seckill is closed!");
			}else {
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);// seckillId + userPhone作为联合主键
				if(insertCount<1) {
					//重复秒杀
					throw new RepeatKillException("seckill repeated!");
				}else {
					//秒杀成功
					 SuccessSeckilled  successSeckilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					 return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS,successSeckilled);
				}
			}
		} catch (SeckillCloseException e) {
			throw e;
		} catch (RepeatKillException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill system error:"+e.getMessage());
		}
	}

	/**
	 * 生成MD5值，防止重复秒杀
	 * @param seckillId
	 */
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + SLAT;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}
