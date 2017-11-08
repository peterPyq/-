package com.seckill.enums;

/**
 * @describe 使用枚举表述业务状态
 * @author pyq 2017年11月8日
 */
public enum SeckillStateEnum {
	SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");
	private int state;
	private String stateInfo;

	private SeckillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
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

	// 根据state去获取相应枚举类型
	public static SeckillStateEnum stateOf(int state) {
		for (SeckillStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state)
				return stateEnum;
		}
		return null;
	}

}
