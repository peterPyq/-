package com.seckill.dto;

/**
 * @describe 暴露秒杀地址DTO
 * @author pyq
 * 2017年11月7日
 */
public class Exposer {

	private boolean exposed;// 是否开启秒杀
	private String md5;// 加密措施
	private long seckillId;
	private long nowTime;// 系统当前时间(毫秒)
	private long startTime;// 开启时间
	private long endTime;// 结束时间

	public Exposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean exposed, long nowTime, long startTime, long endTime) {
		this.exposed = exposed;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNowTime() {
		return nowTime;
	}

	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", seckillId=" + seckillId + ", nowTime=" + nowTime
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

}
