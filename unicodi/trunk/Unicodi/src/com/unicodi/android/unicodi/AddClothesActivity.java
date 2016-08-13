package com.unicodi.android.unicodi;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddClothesActivity extends Activity implements OnClickListener {
	private ImageView iv_addclothes_clothe;
	private EditText et_addclothes_Memo;
	private Button btn_addclothes_category, btn_addclothes_complete;
	private Animation aniShow, aniHide;
	private View categoryView;
	private ImageButton imgBtn_addclothes_category_cancel,
			imgBtn_addclothes_category_top, imgBtn_addclothes_category_bottom,
			imgBtn_addclothes_category_outwear, imgBtn_addclothes_category_etc;
	private boolean open = false;
	private TextView tv_addclothes_categoryText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addclothes);
		setLayoutId();

		categoryView.setVisibility(View.GONE);
		aniShow = AnimationUtils.loadAnimation(this, R.anim.bottom_in);
		aniHide = AnimationUtils.loadAnimation(this, R.anim.bottom_out);
		iv_addclothes_clothe.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(AddClothesActivity.this, "준비 중 입니다.",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		btn_addclothes_category.setOnClickListener(this);
		btn_addclothes_complete.setOnClickListener(this);
		imgBtn_addclothes_category_cancel.setOnClickListener(this);
		imgBtn_addclothes_category_top.setOnClickListener(this);
		imgBtn_addclothes_category_bottom.setOnClickListener(this);
		imgBtn_addclothes_category_outwear.setOnClickListener(this);
		imgBtn_addclothes_category_etc.setOnClickListener(this);
	}

	private void setLayoutId() {

		categoryView = findViewById(R.id.layout_categoryView);
		tv_addclothes_categoryText = (TextView) findViewById(R.id.tv_addclothes_categoryText);
		iv_addclothes_clothe = (ImageView) findViewById(R.id.iv_addclothes_clothe);
		et_addclothes_Memo = (EditText) findViewById(R.id.et_addclothes_Memo);
		btn_addclothes_category = (Button) findViewById(R.id.btn_addclothes_category);
		btn_addclothes_complete = (Button) findViewById(R.id.btn_addclothes_complete);
		imgBtn_addclothes_category_cancel = (ImageButton) findViewById(R.id.imgBtn_addclothes_category_cancel);
		imgBtn_addclothes_category_top = (ImageButton) findViewById(R.id.imgBtn_addclothes_category_top);
		imgBtn_addclothes_category_bottom = (ImageButton) findViewById(R.id.imgBtn_addclothes_category_bottom);
		imgBtn_addclothes_category_outwear = (ImageButton) findViewById(R.id.imgBtn_addclothes_category_outwear);
		imgBtn_addclothes_category_etc = (ImageButton) findViewById(R.id.imgBtn_addclothes_category_etc);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_addclothes_category:
			if (!open) {
				aniShow();
			}
			break;
		case R.id.btn_addclothes_complete:
			finish();
			break;
		case R.id.imgBtn_addclothes_category_cancel:
			aniHide();
			break;
		case R.id.imgBtn_addclothes_category_top:
			tv_addclothes_categoryText.setText("TOP");
			aniHide();
			break;
		case R.id.imgBtn_addclothes_category_bottom:
			tv_addclothes_categoryText.setText("BOTTOM");
			aniHide();
			break;
		case R.id.imgBtn_addclothes_category_outwear:
			tv_addclothes_categoryText.setText("OUTWEAR");
			aniHide();
			break;
		case R.id.imgBtn_addclothes_category_etc:
			tv_addclothes_categoryText.setText("ETC");
			aniHide();
			break;
		}

	}

	private void aniHide() {

		categoryView.startAnimation(aniHide);
		categoryView.setVisibility(View.GONE);
		open = false;
	}

	private void aniShow() {
		categoryView.setVisibility(View.VISIBLE);
		categoryView.startAnimation(aniShow);
		open = true;

	}

	@Override
	public void onBackPressed() {
		if (open) {
			aniHide();
		} else {
			super.onBackPressed();
		}
	}
}
