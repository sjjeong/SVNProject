package com.sjjeong.UsefulLife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.sjjeong.EnterLotto.LottoActivity;
import com.sjjeong.LifeDutchPay.DutchPayActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.usefullife_main);

	}

	public void setOnClick(View v) {
		switch (v.getId()) {
		case R.id.btn_enterLotto:
			doStartActivity(LottoActivity.class);
			break;
		case R.id.btn_lifeDutchPay:
			doStartActivity(DutchPayActivity.class);
			break;
		}
	}

	void doStartActivity(Class<?> cls) {
		Intent intent = new Intent(MainActivity.this, cls);
		startActivity(intent);
	}

}
