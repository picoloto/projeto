package com.picoloto.projetoviasoft.exceptions;

public class ParseCustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParseCustomException(String msg) {
		super(msg);
	}

	public ParseCustomException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
