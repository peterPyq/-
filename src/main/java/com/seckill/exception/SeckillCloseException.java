package com.seckill.exception;

/**
 * @describe 秒杀已关闭异常类
 * @author pyq
 * 2017年11月7日
 */
public class SeckillCloseException extends SeckillException {

	private static final long serialVersionUID = 1L;

	
	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}


	public SeckillCloseException(String message) {
		super(message);
	}

}
