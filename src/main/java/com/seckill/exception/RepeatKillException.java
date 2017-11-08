package com.seckill.exception;

/**
 * @describe 重复秒杀异常类
 * @author pyq
 * 2017年11月7日
 */
public class RepeatKillException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}
	
	

}
