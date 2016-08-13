package com.googry.hancom.timetable;

import java.util.Calendar;

public class LectureData {
	public static Lecture lecture[] = new Lecture[] {
		new Lecture("컴퓨터프로그래밍1 A", "장재영", "공학관 305호","1", Calendar.MONDAY, 2,3,4),
		new Lecture("컴퓨터프로그래밍1 N", "장재영", "공학관 305호","1", Calendar.MONDAY, 8,9,11),
		new Lecture("컴퓨터프로그래밍1 P", "정인상", "공학관 102호","1", Calendar.MONDAY, 8,9,11),
		new Lecture("컴퓨터기술체험론 N", "황기태", "미래관 B104호","1", Calendar.MONDAY,13,14,15),
		new Lecture("컴퓨터기술체험론 A", "김성동", "공학관 405호", "1", Calendar.TUESDAY,1,2),
		new Lecture("컴퓨터프로그래밍1 B", "정인상", "공학관 102호", "1", Calendar.TUESDAY,7,8,9),
		new Lecture("컴퓨터기술체험론 A", "김성동", "공학관 404호", "1", Calendar.WEDNESDAY,5),
		new Lecture("이산수학 A", "김민중", "공학관 404호", "1", Calendar.FRIDAY,7,8,9),
		new Lecture("이산수학 N", "김민중", "공학관 404호", "1", Calendar.FRIDAY,13,14,15),
		new Lecture("프로그래밍언어론 A","김성동","공학관 404호","2",Calendar.MONDAY,3), 
		new Lecture("프로그래밍랩 A","정인환","공학관 305호","2",Calendar.MONDAY,5,6,7), 
		new Lecture("프로그래밍언어론 N","김성동","공학관 404호","2",Calendar.MONDAY,11), 
		new Lecture("프로그래밍랩 N","정인환","공학관 305호","2",Calendar.MONDAY,12,13,14), 
		new Lecture("프로그래밍랩 B","정인환","공학관 305호","2",Calendar.TUESDAY,1,2,3), 
		new Lecture("프로그래밍언어론 N","김성동","공학관 402호","2",Calendar.TUESDAY,11,12), 
		new Lecture("논리회로설계 N","허준영","공학관 404호","2",Calendar.TUESDAY,13,14), 
		new Lecture("자료구조 A","정인환","공학관 404호","2",Calendar.WEDNESDAY,2,3,4), 
		new Lecture("프로그래밍언어론 A","김성동","공학관 404호","2",Calendar.WEDNESDAY,6,7), 
		new Lecture("논리회로설계 N","허준영","공학관 305호","2",Calendar.WEDNESDAY,11,12), 
		new Lecture("논리회로설계 A","허준영","공학관 405호","2",Calendar.THURSDAY,2,3), 
		new Lecture("자료구조 N","김영웅","공학관 102호","2",Calendar.THURSDAY,11,12,13), 
		new Lecture("논리회로설계 A","허준영","공학관 305호","2",Calendar.FRIDAY,2,3), 
		new Lecture("확률및통계 A","송종수","우촌관 201호","2",Calendar.FRIDAY,6,7,8), 
		new Lecture("확률및통계 N","송종수","우촌관 201호","2",Calendar.FRIDAY,11,12,13), 
		new Lecture("객체지향언어2 A","황기태","공학관 102호","3",Calendar.MONDAY,2,3),
		new Lecture("소프트웨어공학 A","정인상","공학관 402호","3",Calendar.MONDAY,5),
		new Lecture("객체지향언어2 B","황기태","공학관 102호","3",Calendar.MONDAY,6,7),
		new Lecture("객체지향언어2 N","이찬수","공학관 102호","3",Calendar.MONDAY,12),
		new Lecture("객체지향언어2 A","황기태","공학관 102호","3",Calendar.TUESDAY,2,3),
		new Lecture("객체지향언어2 B","황기태","공학관 102호","3",Calendar.TUESDAY,5,6),
		new Lecture("운영체제 N","허준영","공학관 404호","3",Calendar.TUESDAY,11),
		new Lecture("소프트웨어공학 N","정인상","공학관 404호","3",Calendar.TUESDAY,12),
		new Lecture("객체지향언어2 N","이찬수","공학관 305호","3",Calendar.TUESDAY,13,14,15),
		new Lecture("소프트웨어공학 A","정인상","공학관 305호","3",Calendar.WEDNESDAY,2,3),
		new Lecture("운영체제 A","김일민","공학관 405호","3",Calendar.WEDNESDAY,6,7,8),
		new Lecture("소프트웨어공학 N","정인상","공학관 305호","3",Calendar.WEDNESDAY,8,9),
		new Lecture("데이터베이스 N","김영웅","공학관 404호","3",Calendar.WEDNESDAY,12,13,14),
		new Lecture("데이터베이스 A","김영웅","공학관 404호","3",Calendar.THURSDAY,2,3),
		new Lecture("데이터베이스 B","김영웅","공학관 404호","3",Calendar.THURSDAY,5,6),
		new Lecture("운영체제 N","허준영","공학관 404호","3",Calendar.THURSDAY,11,12),
		new Lecture("웹프로그래밍 N","송종수","공학관 305호","3",Calendar.THURSDAY,13,14,15),
		new Lecture("웹프로그래밍 A","변정현","공학관 102호","3",Calendar.FRIDAY,1,2,3),
		new Lecture("데이터베이스 A","김영웅","공학관 404호","3",Calendar.FRIDAY,5),
		new Lecture("데이터베이스 B","김영웅","공학관 404호","3",Calendar.FRIDAY,6),
		new Lecture("데이터마이닝 A","현윤진","공학관 305호","3",Calendar.FRIDAY,7,8,9),
		new Lecture("데이터마이닝 N","현윤진","공학관 305호","3",Calendar.FRIDAY,12,13,14),
		new Lecture("컴파일러이론및설계 A","김성동","공학관 404호","4",Calendar.MONDAY,5,6,7),
		new Lecture("컴퓨터보안 N","이찬수","공학관 102호","4",Calendar.MONDAY,13,14,15),
		new Lecture("컴퓨터보안 A","이찬수","공학관 305호","4",Calendar.TUESDAY,7,8,9),
		new Lecture("빅데이터프로그래밍 A","장재영","공학관 102호","4",Calendar.WEDNESDAY,2,3,4),
		new Lecture("빅데이터프로그래밍 N","장재영","공학관 102호","4",Calendar.WEDNESDAY,11,12,13),
		new Lecture("설계프로젝트","공동강좌","공학관 405호","4",Calendar.FRIDAY,11,12,13,14,15),
	};
}