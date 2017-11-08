package com.seckill.service;

import java.util.List;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

public interface SeckillService {
	/**
	 * 查询所有的秒杀记录
	 * @return List
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查询单个的秒杀记录
	 * @param seckillId
	 * @return seckill
	 */
	Seckill getById(long seckillId);

	/**
	 * 仅当秒杀开启时输出秒杀接口
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5 
	 */
	SeckillExecution executeSeckill(long seckillId, Long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
}
