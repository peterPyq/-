package com.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.SuccessSeckilled;

/**
 * @describe 成功秒杀明细Dao
 * @author pyq 2017年11月2日
 */
public interface SuccessKilledDao {
	/**
	 * 新增购买明细，可过滤重复
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return 插入的行数
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体
	 * 
	 * @param seckillId
	 * @return
	 */
	SuccessSeckilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
