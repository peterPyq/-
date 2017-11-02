package com.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Seckill;

/**
 * @describe 商品库存dao
 * @author pyq
 */
public interface SeckillDao {
	/**
	 * 减库存
	 * @param seckiiedId
	 * @param killTime
	 * @return 如果影响行数大于1，表示更新的行数
	 */
	int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return Seckill
	 **/
	Seckill queryById(long seckillId);

	/** 
	 * 根据偏移量查询秒杀商品列表
	 * @param offset 
	 * @param limit 
	 * @return List
	 * */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
