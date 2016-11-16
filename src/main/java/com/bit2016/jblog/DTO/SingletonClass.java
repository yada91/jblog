package com.bit2016.jblog.DTO;

public class SingletonClass {

	private static SingletonClass instance;

	public static SingletonClass getInstance() {
		if (instance == null) {
			instance = new SingletonClass();
		}

		return instance;
	}

	private SingletonClass() {
	}
}