package com.unicodi.android.unicodi;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewsfeedActivity extends Activity {
	private ImageView iv_addnewsfeed_closet1, iv_addnewsfeed_closet2,
			iv_addnewsfeed_closet3;
	private EditText et_profileModifyMemo;
	private Button btn_addnewsfeed_Complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnewsfeed);
		setLayoutId();
		iv_addnewsfeed_closet1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(AddNewsfeedActivity.this, "준비 중 입니다.",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		iv_addnewsfeed_closet2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(AddNewsfeedActivity.this, "준비 중 입니다.",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		iv_addnewsfeed_closet3.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(AddNewsfeedActivity.this, "준비 중 입니다.",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		btn_addnewsfeed_Complete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void setLayoutId() {
		iv_addnewsfeed_closet1 = (ImageView) findViewById(R.id.iv_addnewsfeed_closet1);
		iv_addnewsfeed_closet2 = (ImageView) findViewById(R.id.iv_addnewsfeed_closet2);
		iv_addnewsfeed_closet3 = (ImageView) findViewById(R.id.iv_addnewsfeed_closet3);
		et_profileModifyMemo = (EditText) findViewById(R.id.et_profileModifyMemo);
		btn_addnewsfeed_Complete = (Button) findViewById(R.id.btn_addnewsfeed_Complete);
	}

}