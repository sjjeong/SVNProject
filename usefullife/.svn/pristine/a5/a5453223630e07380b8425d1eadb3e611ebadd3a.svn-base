package com.sjjeong.LifeDutchPay;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.sjjeong.UsefulLife.R;

public class DutchPayActivity extends Activity {
	private ListView lv;
	private static ArrayList<DutchPay> al;
	private static DutchPayAdapter da;

	Button btn_add;
	Button btn_jump;
	Button btn_next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.life_dutchpay);
		lv = (ListView) findViewById(R.id.lv_dutchpayList1);
		al = new ArrayList<DutchPay>();
		da = new DutchPayAdapter(this, R.layout.life_dutchpay_listview, al);

		al.add(new DutchPay());

		lv.setAdapter(da);

		btn_add = (Button) findViewById(R.id.btn_dutchpayAdd);
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				al.add(new DutchPay());
				da.notifyDataSetChanged();
			}
		});
		btn_jump = (Button) findViewById(R.id.btn_dutchpayJump);
		btn_jump.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DutchPayActivity.this,
						DutchPayNext.class);
				intent.putExtra("bool", false);
				startActivity(intent);
			}
		});
		btn_next = (Button) findViewById(R.id.btn_dutchpayNext);
		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				da.notifyDataSetChanged();
				Intent intent = new Intent(DutchPayActivity.this,
						DutchPayNext.class);
				intent.putExtra("bool", true);
				startActivity(intent);

			}
		});

	}

	public static ArrayList<DutchPay> getAl() {
		return al;
	}

	public static DutchPayAdapter getDa() {
		return da;
	}

}
