package com.optimize.chapter2;

public class DBQueryMain {

	public static void main(String[] args) {
		IDBQuery query = new DBQueryProxy();
		System.out.println(query.request());
	}
}
