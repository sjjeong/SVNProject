package com.sjjeong.EnterLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lotto {
	private ArrayList<Integer> lottoNumber;// 로토 번호 변순

	Lotto() {
		lottoNumber = new ArrayList<Integer>();// 로토 번호 배열 6개+보너스번호1개
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
		// //보너스 번호 생성
		// while (true) {
		// int number = ran.nextInt(45) + 1;
		// if (lottoNumber.contains(number)) {
		// continue;
		// }
		// lottoNumber.add(number);
		// break;
		// }
	}

	public int getLottoNumber(int index) {
		return lottoNumber.get(index);
	}

	public ArrayList<Integer> getList() {
		return lottoNumber;
	}
}
