package com.googry.hancom.professor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.googry.hancom.R;

public class ProfessorDetailActivity extends Activity {
	private TextView tv_name, tv_fromSchool, tv_major, tv_lab, tv_email;
	private ImageView iv_prof;
	private Button btn_callLab;

	private Professor pf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor);
		setLayoutId();
		setData();

		btn_callLab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ pf.getTel()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

	private void setLayoutId() {
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_fromSchool = (TextView) findViewById(R.id.tv_fromSchool);
		tv_major = (TextView) findViewById(R.id.tv_major);
		tv_lab = (TextView) findViewById(R.id.tv_lab);
		tv_email = (TextView) findViewById(R.id.tv_email);
		iv_prof = (ImageView) findViewById(R.id.iv_prof);
		btn_callLab = (Button) findViewById(R.id.btn_callLab);
	}

	private void setData() {
		Intent intent = getIntent();
		pf = (Professor) intent.getSerializableExtra("professor");
		tv_name.setText(pf.getName());
		tv_fromSchool.setText(pf.getUniv());
		tv_major.setText(pf.getMajor());
		tv_lab.setText(pf.getLab());
		tv_email.setText(pf.getEmail());
		iv_prof.setImageResource(pf.getPictureId());
	}

}
