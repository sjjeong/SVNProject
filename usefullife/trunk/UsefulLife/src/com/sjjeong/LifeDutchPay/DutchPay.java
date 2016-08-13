package com.sjjeong.LifeDutchPay;

import android.support.v4.os.ParcelableCompat;

public class DutchPay extends ParcelableCompat {
	private String menu;
	private int price;
	private int amount;

	DutchPay() {
		menu = "";
		price = 0;
		amount = 0;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
