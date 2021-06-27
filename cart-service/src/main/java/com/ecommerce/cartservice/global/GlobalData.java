package com.ecommerce.cartservice.global;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.cartservice.model.Product;

public class GlobalData {

	public static List<Product> cart;
	static {
		cart=new ArrayList<Product>();
	}

}
