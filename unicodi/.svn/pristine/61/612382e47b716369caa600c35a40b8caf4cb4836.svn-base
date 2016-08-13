package com.unicodi.android.unicodi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordChangeActivity extends Activity implements OnClickListener {

	private Button btn_pwc_complete;
	private EditText et_pwc_now, et_pwc_new, et_pwc_new_con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passwordchange);
		setLayoutId();
		btn_pwc_complete.setOnClickListener(this);
	}

	private void setLayoutId() {
		btn_pwc_complete = (Button) findViewById(R.id.btn_pwc_complete);
		et_pwc_now = (EditText) findViewById(R.id.et_pwc_now);
		et_pwc_new = (EditText) findViewById(R.id.et_pwc_new);
		et_pwc_new_con = (EditText) findViewById(R.id.et_pwc_new_con);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_pwc_complete:
			// 비밀번호 변경 구현;
			finish();
			break;
		}
	}
}
