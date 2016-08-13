package com.googry.android.cardwithnfc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.googry.android.cardwithnfc.data.MyCard;
import com.googry.android.cardwithnfc.data.MyCardSingleton;

public class MyCardActivity extends Activity implements OnClickListener {
	private EditText et_name, et_phone, et_comName, et_comAddr, et_department,
			et_position, et_number, et_fax, et_email;

	private MyCard myCard;

	private ImageButton btn_adjust, btn_save;
	private boolean editOnToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stubm
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycard);
		myCard = MyCardSingleton.getInstance().getMyCard();
		init();
		SharedPreferences pref = getSharedPreferences("Pref", 0);

		// if editOnToglle is false, disable
		editOnToggle = false;

		et_name.setText(myCard.getName());
		et_phone.setText(myCard.getPhone());
		et_comName.setText(myCard.getComName());
		et_comAddr.setText(myCard.getComAddr());
		et_department.setText(myCard.getDepartment());
		et_position.setText(myCard.getPosition());
		et_number.setText(myCard.getNumber());
		et_fax.setText(myCard.getFax());
		et_email.setText(myCard.getEmail());

		if (savedInstanceState != null) {
			Log.i("DEBUG", "savedInstanceState != null");
			et_name.setText(savedInstanceState.getString("name"));
			et_phone.setText(savedInstanceState.getString("phone"));
			et_comName.setText(savedInstanceState.getString("comName"));
			et_comAddr.setText(savedInstanceState.getString("comAddr"));
			et_department.setText(savedInstanceState.getString("department"));
			et_position.setText(savedInstanceState.getString("position"));
			et_number.setText(savedInstanceState.getString("number"));
			et_fax.setText(savedInstanceState.getString("fax"));

		}

	}

	public void init() {
		btn_adjust = (ImageButton) findViewById(R.id.btn_adjust);
		btn_save = (ImageButton) findViewById(R.id.btn_save);
		btn_adjust.setOnClickListener(this);
		btn_save.setOnClickListener(this);
		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_comName = (EditText) findViewById(R.id.et_comName);
		et_comAddr = (EditText) findViewById(R.id.et_comAddr);
		et_department = (EditText) findViewById(R.id.et_department);
		et_position = (EditText) findViewById(R.id.et_position);
		et_number = (EditText) findViewById(R.id.et_number);
		et_fax = (EditText) findViewById(R.id.et_fax);
		et_email = (EditText) findViewById(R.id.et_email);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
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
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString("name", et_name.getText().toString());
		outState.putString("phone", et_phone.getText().toString());
		outState.putString("comName", et_comName.getText().toString());
		outState.putString("comAddr", et_comAddr.getText().toString());
		outState.putString("department", et_department.getText().toString());
		outState.putString("position", et_position.getText().toString());
		outState.putString("number", et_number.getText().toString());
		outState.putString("fax", et_fax.getText().toString());
		outState.putString("email", et_email.getText().toString());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_adjust:
			et_name.setEnabled(true);
			et_phone.setEnabled(true);
			et_comName.setEnabled(true);
			et_comAddr.setEnabled(true);
			et_department.setEnabled(true);
			et_position.setEnabled(true);
			et_number.setEnabled(true);
			et_fax.setEnabled(true);
			et_email.setEnabled(true);
			btn_adjust.setVisibility(View.GONE);
			btn_save.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_save:
			et_name.setEnabled(false);
			et_phone.setEnabled(false);
			et_comName.setEnabled(false);
			et_comAddr.setEnabled(false);
			et_department.setEnabled(false);
			et_position.setEnabled(false);
			et_number.setEnabled(false);
			et_fax.setEnabled(false);
			et_email.setEnabled(false);
			SharedPreferences pref = getSharedPreferences("Pref", 0);
			SharedPreferences.Editor editor = pref.edit();
			editor.putString("name", et_name.getText().toString());
			editor.putString("phone", et_phone.getText().toString());
			editor.putString("comName", et_comName.getText().toString());
			editor.putString("comAddr", et_comAddr.getText().toString());
			editor.putString("department", et_department.getText().toString());
			editor.putString("position", et_position.getText().toString());
			editor.putString("number", et_number.getText().toString());
			editor.putString("fax", et_fax.getText().toString());
			editor.putString("email", et_email.getText().toString());
			editor.commit();
			btn_adjust.setVisibility(View.VISIBLE);
			btn_save.setVisibility(View.GONE);
			break;
		}

	}
}
