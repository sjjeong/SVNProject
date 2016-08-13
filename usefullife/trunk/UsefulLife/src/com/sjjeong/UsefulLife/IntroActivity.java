package com.sjjeong.UsefulLife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroActivity extends Activity {

	static ImageView imgIntro;// 로고 이미지 변수
	static Handler handler;// 핸들러

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 타이틀 없애기
		setContentView(R.layout.usefullife_intro);

		imgIntro = (ImageView) findViewById(R.id.imaIntro);

		Animation animation = null;// 애니메이션 변수
		animation = AnimationUtils.loadAnimation(IntroActivity.this,
				R.anim.alpha);// 애니메이션 속성 받기
		imgIntro.startAnimation(animation);// 애니메이션 속성 적용

		handler = new Handler();// 핸들러 객체 생성
		handler.postDelayed(runnable, 1500);// 딜레이주기

	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(IntroActivity.this, MainActivity.class);
			startActivity(intent);
			finish();

			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		handler.removeCallbacks(runnable);
	}

}
