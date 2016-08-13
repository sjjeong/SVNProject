package com.unicodi.android.unicodi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unicodi.android.unicodi.utilgoogry.AppDataManagerUtil;

public class IntroActivity extends Activity {

	private Handler h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);

		h = new Handler();
		h.postDelayed(irun, 1000);
	}

	Runnable irun = new Runnable() {

		@Override
		public void run() {
			startActivity(new Intent(IntroActivity.this, LoginActivity.class));
			finish();

			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		h.removeCallbacks(irun);
	}

}
