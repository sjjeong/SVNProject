package com.sjjeong.EnterLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sjjeong.UsefulLife.R;

public class LottoActivity extends Activity {
	private ListView lv;
	private ArrayList<Lotto> al;
	private LottoAdapter la;
	TextView lottos[];
	Button btn_winLotto;

	String lottoStr = "!";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("UsefulLife", "LottoActivity onCreate()");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.enter_lotto);
		lv = (ListView) findViewById(R.id.lv_lotto);
		al = new ArrayList<Lotto>();
		la = new LottoAdapter(this, R.layout.enter_lotto_listview, al);

		al.add(new Lotto());
		al.add(new Lotto());
		al.add(new Lotto());
		al.add(new Lotto());
		al.add(new Lotto());

		// 로또 번호 보여줄 TextView 생성
		lottos = new TextView[7];
		lottos[0] = (TextView) findViewById(R.id.tv_lottos1);
		lottos[1] = (TextView) findViewById(R.id.tv_lottos2);
		lottos[2] = (TextView) findViewById(R.id.tv_lottos3);
		lottos[3] = (TextView) findViewById(R.id.tv_lottos4);
		lottos[4] = (TextView) findViewById(R.id.tv_lottos5);
		lottos[5] = (TextView) findViewById(R.id.tv_lottos6);
		lottos[6] = (TextView) findViewById(R.id.tv_lottos7);

		ArrayList<Integer> lottoNumber = new ArrayList<Integer>();// 로토 번호 배열
		// 6개+보너스번호1개
		Random ran = new Random();// 랜덤객체 생성
		lottoNumber.add(ran.nextInt(45) + 1);// 1~45번까지 랜덤 생성
		for (int i = 1; i < 6; i++) {
			int number = ran.nextInt(45) + 1;
			if (lottoNumber.contains(number)) {
				i--;
				continue;
			}
			lottoNumber.add(number);// 1~45번까지 랜덤 생성
		}
		Collections.sort(lottoNumber);
		for (int i = 0; i < 6; i++) {
			lottos[i].setText(lottoNumber.get(i) + "");
		}
		int bonusNumber = 0;
		// 보너스 번호 생성
		while (true) {
			bonusNumber = ran.nextInt(45) + 1;
			if (lottoNumber.contains(bonusNumber)) {
				continue;
			}
			break;
		}
		lottos[6].setText("bonus Number : " + bonusNumber);

		// 당첨 확인 알고리즘
		int winCount = 0;
		for (int i = 0; i < 5; i++) {
			// 같은 번호가 몇개 있는지 확인
			for (int j = 0; j < 6; j++) {
				if (al.get(i).getList().contains(lottoNumber.get(j))) {
					winCount++;
				}

			}
			// 당첨확인 문자열
			switch (winCount) {
			case 6:
				lottoStr += "1등 당첨!!!\n";
				break;
			case 5:
				if (al.get(i).getList().contains(bonusNumber)) {
					lottoStr += "2등 당첨!!\n";
				} else {
					lottoStr += "3등 당첨!\n";
				}
				break;
			case 4:
				lottoStr += "4등 당첨\n";
				break;
			case 3:
				lottoStr += "5등 당첨\n";
				break;

			}
			winCount = 0;
		}

		lv.setAdapter(la);
		Log.i("Bug", lottoStr);
		if (lottoStr.equals("!")) {
			lottoStr += "당첨되지 않았습니다.";
		}

		btn_winLotto = (Button) findViewById(R.id.btn_lotto_winCheck);
		btn_winLotto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(LottoActivity.this)
						.setTitle("Lotto")
						.setMessage(lottoStr)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
			}
		});
	}
}
