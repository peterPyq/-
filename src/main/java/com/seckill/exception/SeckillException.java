package com.seckill.exception;

/**
 * @describe 秒杀相关业务异常类
 * @author pyq
 * 2017年11月7日
 */
public class SeckillException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
