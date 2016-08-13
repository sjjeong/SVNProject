package com.sjjeong.EnterLotto;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sjjeong.UsefulLife.R;

public class LottoAdapter extends ArrayAdapter<Lotto> {
	private ArrayList<Lotto> lotto;
	private LayoutInflater mInflater;

	public LottoAdapter(Context context, int textViewResourceId,
			ArrayList<Lotto> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		// Log.i("UsefulLife","LottoAdapter");
		lotto = objects;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// Log.i("UsefulLife", "LottoAdapter getView()");
		View v = convertView;
		if (v == null) {
			v = mInflater.inflate(R.layout.enter_lotto_listview, null);
		}
		Lotto mLotto = lotto.get(position);
		if (mLotto != null) {
			TextView lotto1 = (TextView) v.findViewById(R.id.tv_lotto1);
			if (lotto1 != null) {
				lotto1.setText("" + mLotto.getLottoNumber(0));
			}
			TextView lotto2 = (TextView) v.findViewById(R.id.tv_lotto2);
			if (lotto2 != null) {
				lotto2.setText("" + mLotto.getLottoNumber(1));
			}
			TextView lotto3 = (TextView) v.findViewById(R.id.tv_lotto3);
			if (lotto3 != null) {
				lotto3.setText("" + mLotto.getLottoNumber(2));
			}
			TextView lotto4 = (TextView) v.findViewById(R.id.tv_lotto4);
			if (lotto4 != null) {
				lotto4.setText("" + mLotto.getLottoNumber(3));
			}
			TextView lotto5 = (TextView) v.findViewById(R.id.tv_lotto5);
			if (lotto5 != null) {
				lotto5.setText("" + mLotto.getLottoNumber(4));
			}
			TextView lotto6 = (TextView) v.findViewById(R.id.tv_lotto6);
			if (lotto6 != null) {
				lotto6.setText("" + mLotto.getLottoNumber(5));
			}
		}
		// Log.i("UsefulLife", "LottoAdapter getView() finish");
		return v;
	}

}
