package com.sjjeong.LifeDutchPay;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.sjjeong.UsefulLife.R;

public class DutchPayAdapter extends ArrayAdapter<DutchPay> {

	private ArrayList<DutchPay> dutch;
	private LayoutInflater mInflater;
	private View v;

	public DutchPayAdapter(Context context, int textViewResourceId,
			ArrayList<DutchPay> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		dutch = objects;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		v = convertView;
		if (v == null) {
			v = mInflater.inflate(R.layout.life_dutchpay_listview, null);
		}
		DutchPay mDutch = dutch.get(position);
		final int mPosition = position;
		if (mDutch != null) {
			// 메뉴 삭제
			Button btn_del = (Button) v.findViewById(R.id.btn_dutchpayDel);
			btn_del.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dutch.remove(mPosition);
					new DutchPayActivity().getDa().notifyDataSetChanged();
				}
			});

			EditText menu = (EditText) v.findViewById(R.id.et_DutchPay_Menu);
			EditText price = (EditText) v.findViewById(R.id.et_DutchPay_Price);
			EditText amount = (EditText) v
					.findViewById(R.id.et_DutchPay_Amount);

			if (!menu.getText().toString().replace(" ", "").equals("")) {
				mDutch.setMenu(menu.getText().toString() + "");
			}
			if (!price.getText().toString().equals("")) {
				mDutch.setPrice(Integer.parseInt(price.getText().toString()
						+ ""));
			}
			if (!amount.getText().toString().equals("")) {
				mDutch.setAmount(Integer.parseInt(amount.getText().toString()
						+ ""));
			}
		}

		return v;
	}

}
