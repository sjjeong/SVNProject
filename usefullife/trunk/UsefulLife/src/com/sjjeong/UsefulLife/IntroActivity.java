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

	static ImageView imgIntro;// �ΰ� �̹��� ����
	static Handler handler;// �ڵ鷯

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// Ÿ��Ʋ ���ֱ�
		setContentView(R.layout.usefullife_intro);

		imgIntro = (ImageView) findViewById(R.id.imaIntro);

		Animation animation = null;// �ִϸ��̼� ����
		animation = AnimationUtils.loadAnimation(IntroActivity.this,
				R.anim.alpha);// �ִϸ��̼� �Ӽ� �ޱ�
		imgIntro.startAnimation(animation);// �ִϸ��̼� �Ӽ� ����

		handler = new Handler();// �ڵ鷯 ��ü ����
		handler.postDelayed(runnable, 1500);// �������ֱ�

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
