package com.ecommerce.productcatalogservice.global;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.productcatalogservice.Model.Product;


public class GlobalData {
	public static List<Product> cart;
	static {
		cart=new ArrayList<Product>();
	}

}
