package com.googry.hancom.timetable;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String prof;
	private String location;
	private String year;
	private ArrayList<Integer> alPeriod;
	private int dayOfTheWeek;

	// 시작이 0교시 8:00
	private final static int START_TIME = 480;
	private final static int RELAX_TIME = 10;
	private final static int CLASS_TIME = 50;

	public Lecture(String name, String prof, String location, String grade,
			int dayOfTheWeek, int... period) {
		this.name = name;
		this.prof = prof;
		this.location = location;
		this.year = grade;
		this.dayOfTheWeek = dayOfTheWeek;
		this.alPeriod = new ArrayList<Integer>();
		for (int element : period) {
			this.alPeriod.add(element);
		}

	}

	public String getName() {
		return name;
	}

	public String getProf() {
		return prof;
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<Integer> getAlPeriod() {
		return alPeriod;
	}

	public String getYear() {
		return year;
	}

	public int getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public String getTime() {
		String time = "";
		int startTime = START_TIME + (alPeriod.get(0) - alPeriod.get(0) / 10)
				* (CLASS_TIME + RELAX_TIME);
		int endTime = startTime;
		if (alPeriod.size() == 1) {
			endTime += CLASS_TIME;
		} else {
			int startPeriod = alPeriod.get(0);
			int endPeriod = alPeriod.get(alPeriod.size() - 1);
			int faultPeriod = 0;
			if (startPeriod < 10 && endPeriod > 10) {
				// 주간시작, 야간끝
				faultPeriod = 1;
			}
			endTime += (endPeriod - startPeriod - faultPeriod + 1) * CLASS_TIME
					+ (endPeriod - startPeriod - faultPeriod) * RELAX_TIME;
		}
		String strStartTime = String.format("%02d:%02d", startTime / 60,
				startTime % 60);
		String strEndTime = String.format("%02d:%02d", endTime / 60,
				endTime % 60);
		time = strStartTime + " ~ " + strEndTime;
		return time;
	}
}
