package com.library.aspect;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {
		super("存在しない項目です");
	}

}
