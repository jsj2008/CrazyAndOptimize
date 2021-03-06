package com.designpattern.flyweight;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import java.sql.Connection;

public class ConnectionPool {

	private Vector<Connection> pool;
	
	private String url = "jdbc:mysql://localhost:3306/test";
	private String userName = "root";
	private String password = "root";
	private String driverClassName ="com.mysql.jdbc.Driver";
	
	private int poolSize = 100;
	private static ConnectionPool instance = null;
	Connection conn = null;
	
	private ConnectionPool() {
		pool = new Vector<Connection>(poolSize);
		for (int i = 0; i < poolSize; i ++) {
			try {
				Class.forName(driverClassName);
				conn = DriverManager.getConnection(url, userName, password);
				pool.add(conn); 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void release() {
		pool.add(conn);
	}
	
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
	
	public synchronized static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
}
