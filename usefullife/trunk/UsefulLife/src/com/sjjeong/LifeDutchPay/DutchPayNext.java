package com.sjjeong.LifeDutchPay;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.sjjeong.UsefulLife.R;

public class DutchPayNext extends Activity {

	private int sumPrice;
	private int divisionNumber;
	private ArrayList<DutchPay> al;
	EditText et_allPrice;
	EditText et_totalNumber;
	EditText et_AmountPaid;
	Button btn_Cal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.life_dutchpay_next);

		Intent intent = getIntent();
		if (intent.getExtras().getBoolean("bool")) {
			al = new DutchPayActivity().getAl();
			int price = 0;
			int amount = 1;
			for (int i = 0; i < al.size(); i++) {
				price = al.get(i).getPrice();
				amount = al.get(i).getAmount();
				sumPrice += price * amount;
				Log.i("Bug2", "price = " + price + " amount = " + amount);
			}
		} else
			sumPrice = 0;
		divisionNumber = 1000;

		et_allPrice = (EditText) findViewById(R.id.et_DutchPay_AllPrice);
		et_allPrice.setText(sumPrice + "");

		et_totalNumber = (EditText) findViewById(R.id.et_DutchPay_TotalNumber);
		et_AmountPaid = (EditText) findViewById(R.id.et_DutchPay_AmountPaid);

		btn_Cal = (Button) findViewById(R.id.btn_DutchPay_Calculation);
		btn_Cal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!et_totalNumber.getText().toString().replace(" ", "")
						.equals("")
						&& Integer
								.parseInt(et_totalNumber.getText().toString()) != 0) {
					sumPrice = Integer.parseInt(et_allPrice.getText()
							.toString());
					String str = "";
					int totalNumber = 1;
					totalNumber = Integer.parseInt(et_totalNumber.getText()
							.toString());
					// 최종 값
					int finalPrice;
					// 동일한 값
					int baseMoney = sumPrice / (totalNumber * divisionNumber)
							* divisionNumber;
					// 다른 값
					int restMoney = sumPrice % (totalNumber * divisionNumber);
					// 차이금액 구하기
					int restPeople = 1;
					if (restMoney == 0) {
						finalPrice = baseMoney
								+ (restMoney / (totalNumber * divisionNumber) * divisionNumber);
						str += finalPrice + "원 " + totalNumber + "명";

					} else {
						restPeople = restMoney / divisionNumber;
						finalPrice = baseMoney + divisionNumber;
						if (restMoney % divisionNumber == 0) {
							str += finalPrice + "원 " + restPeople + "명\n";
							str += (baseMoney + (restMoney % divisionNumber))
									+ "원 " + (totalNumber - restPeople) + "명";
						} else {
							if (restPeople != 0)
								str += finalPrice + "원 " + restPeople + "명\n";
							if (totalNumber - restPeople - 1 != 0)
								str += baseMoney + "원 "
										+ (totalNumber - restPeople - 1)
										+ "명\n";
							str += (baseMoney + (restMoney % divisionNumber))
									+ "원 1명";
							// }

						}
					}

					et_AmountPaid.setText(str);
				}

			}
		});

	}
}