package com.seckill.dto;

import com.seckill.entity.SuccessSeckilled;
import com.seckill.enums.SeckillStateEnum;

/**
 * @describe 封装秒杀执行后结果
 * @author pyq
 * 2017年11月7日
 */
public class SeckillExecution {
	private long seckillId;
	private int state;//秒杀执行结果状态
	private String stateInfo;//状态标识
	private SuccessSeckilled successSeckilled;//秒杀成功对象
	
	//秒杀成功时构造函数
	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessSeckilled successSeckilled) {
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successSeckilled = successSeckilled;
	}
	//秒杀失败时构造函数
	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessSeckilled getSuccessSeckilled() {
		return successSeckilled;
	}
	public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
		this.successSeckilled = successSeckilled;
	}
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successSeckilled=" + successSeckilled + "]";
	}
	
	
}
