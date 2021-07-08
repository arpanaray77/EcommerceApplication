package com.ecommerce.cartservice.controller;

public class ApiResponse {
	boolean flag;
	String msg;

	public ApiResponse(boolean b, String string) {
		this.flag=b;
		this.msg=string;
	}

	
}
