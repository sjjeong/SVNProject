package com.googry.android.cardwithnfc;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;


public class YourCardDialog extends Dialog {
	private TextView tv_name, tv_phone, tv_comName, tv_comAddr, tv_department,
			tv_position, tv_number, tv_fax, tv_email;

	public YourCardDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_yourcard);
		tv_name = (TextView) findViewById(R.id.name);
		tv_phone = (TextView) findViewById(R.id.phone);
		tv_comName = (TextView) findViewById(R.id.comName);
		tv_comAddr = (TextView) findViewById(R.id.comAddr);
		tv_department = (TextView) findViewById(R.id.department);
		tv_position = (TextView) findViewById(R.id.position);
		tv_number = (TextView) findViewById(R.id.number);
		tv_fax = (TextView) findViewById(R.id.fax);
		tv_email = (TextView) findViewById(R.id.email);
	}

	public void setName(String str) {
		tv_name.setText(str);
	}

	public void setPhone(String str) {
		tv_phone.setText(str);
	}

	public void setComName(String str) {
		tv_comName.setText(str);
	}

	public void setComAddr(String str) {
		tv_comAddr.setText(str);
	}

	public void setDepartment(String str) {
		tv_department.setText(str);
	}

	public void setPosition(String str) {
		tv_position.setText(str);
	}

	public void setNumber(String str) {
		tv_number.setText(str);
	}

	public void setFax(String str) {
		tv_fax.setText(str);
	}

	public void setEmail(String str) {
		tv_email.setText(str);
	}

}
