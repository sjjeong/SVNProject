package com.googry.android.cardwithnfc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.googry.android.cardwithnfc.data.DBManager;
import com.googry.android.cardwithnfc.data.MyCard;
import com.googry.android.cardwithnfc.data.MyCardSingleton;

public class IntroActivity extends Activity {

	Handler h;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		h.removeCallbacks(irun);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		MyCard myCard = new MyCard();
		SharedPreferences pref = getSharedPreferences("Pref", 0);
		myCard.setName(pref.getString("name", ""));
		myCard.setPhone(pref.getString("phone", ""));
		myCard.setComName(pref.getString("comName", ""));
		myCard.setComAddr(pref.getString("comAddr", ""));
		myCard.setDepartment(pref.getString("department", ""));
		myCard.setPosition(pref.getString("position", ""));
		myCard.setNumber(pref.getString("number", ""));
		myCard.setFax(pref.getString("fax", ""));
		myCard.setEmail(pref.getString("email", ""));

		DBManager dbManager = new DBManager(this, "mycard.db", null, 1);

		MyCardSingleton singleton = MyCardSingleton.getInstance();
		singleton.setMyCard(myCard);
		singleton.setDbManager(dbManager);

		h = new Handler();
		h.postDelayed(irun, 2000);
	}

	Runnable irun = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(IntroActivity.this,
					CardListActivity.class);
			startActivity(intent);
			finish();

			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
		}
	};

}
