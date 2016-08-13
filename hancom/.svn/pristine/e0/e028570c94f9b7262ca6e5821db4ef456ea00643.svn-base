package com.googry.hancom.timetable;

import java.util.ArrayList;
import java.util.Calendar;

import com.googry.hancom.option.preference.PreferenceManager;

public class Timetable {
	private static Timetable _instance;
	private ArrayList<Lecture> alLecture;

	private Timetable() {
	}

	public static Timetable getInstance() {
		if (_instance == null) {
			_instance = new Timetable();
			_instance.alLecture = new ArrayList<Lecture>();
		}
		return _instance;
	}

	public void clearAlLecture() {
		alLecture.clear();
	}

	public void addLecture(Lecture l) {
		alLecture.add(l);
	}

	public ArrayList<Lecture> getLectureList() {
		ArrayList<Lecture> returnLectures = new ArrayList<Lecture>();
		ArrayList<Lecture> tmpLectures = new ArrayList<Lecture>();
		if (PreferenceManager
				.getBooleanData(PreferenceManager.KEY_DAYOFWEEKCHECKED)) {
			int day = PreferenceManager
					.getIntData(PreferenceManager.KEY_DAYOFWEEK);
			if (day == 0) {
				Calendar today = Calendar.getInstance();
				for (Lecture l : this.alLecture) {
					if (today.get(Calendar.DAY_OF_WEEK) == l.getDayOfTheWeek()) {
						tmpLectures.add(l);
					}
				}
			} else {
				for (Lecture l : this.alLecture) {
					if (day + 1 == l.getDayOfTheWeek()) {
						tmpLectures.add(l);
					}
				}
			}
		}
		if (PreferenceManager.getBooleanData(PreferenceManager.KEY_YEARCHECKED)) {
			int year = PreferenceManager.getIntData(PreferenceManager.KEY_YEAR);
			for (Lecture l : tmpLectures) {
				if (year == Integer.parseInt(l.getYear())) {
					returnLectures.add(l);
				}
			}
		} else {
			returnLectures = tmpLectures;
		}
		if (PreferenceManager
				.getBooleanData(PreferenceManager.KEY_DAYOFWEEKCHECKED) == false
				&& PreferenceManager
						.getBooleanData(PreferenceManager.KEY_YEARCHECKED) == false) {
			for (Lecture l : this.alLecture) {
				returnLectures.add(l);
			}
		}

		return returnLectures;
	}

}
