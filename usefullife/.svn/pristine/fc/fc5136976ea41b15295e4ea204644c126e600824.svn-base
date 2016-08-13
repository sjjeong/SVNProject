package com.sjjeong.UsefulLife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends Activity {

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
		setContentView(R.layout.usefullife_start);
		h = new Handler();
		h.postDelayed(irun, 500);

	}

	Runnable irun = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(StartActivity.this, IntroActivity.class);
			startActivity(intent);
			finish();
			
			overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}
	};

}
