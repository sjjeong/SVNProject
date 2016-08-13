package com.googry.android.cardwithnfc.data;

public class MyCardSingleton {
	private static MyCardSingleton instance;
	private MyCard myCard;
	private DBManager dbManager;

	private MyCardSingleton() {
	}

	public static MyCardSingleton getInstance() {
		if (instance == null) {
			instance = new MyCardSingleton();
		}
		return instance;

	}

	public MyCard getMyCard() {
		return myCard;
	}

	public void setMyCard(MyCard myCard) {
		this.myCard = myCard;
	}

	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}
}
