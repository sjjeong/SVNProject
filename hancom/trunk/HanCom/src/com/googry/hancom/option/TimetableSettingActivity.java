package com.googry.hancom.option;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.googry.hancom.R;
import com.googry.hancom.option.preference.PreferenceManager;

public class TimetableSettingActivity extends Activity {
	private CheckBox cb_dayofweek, cb_year;
	private RadioGroup rg_dayofweek, rg_year;
	private RadioButton rb_dayofweek_auto, rb_dayofweek_monday,
			rb_dayofweek_tuesday, rb_dayofweek_wednesday,
			rb_dayofweek_thursday, rb_dayofweek_friday, rb_year_first,
			rb_year_second, rb_year_third, rb_year_fourth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option_timetable);
		setId();
		setListener();
		setData();
	}

	private void setId() {
		cb_dayofweek = (CheckBox) findViewById(R.id.cb_dayofweek);
		cb_year = (CheckBox) findViewById(R.id.cb_year);
		rg_dayofweek = (RadioGroup) findViewById(R.id.rg_dayofweek);
		rg_year = (RadioGroup) findViewById(R.id.rg_year);

		rb_dayofweek_auto = (RadioButton) findViewById(R.id.rb_dayofweek_auto);
		rb_dayofweek_monday = (RadioButton) findViewById(R.id.rb_dayofweek_monday);
		rb_dayofweek_tuesday = (RadioButton) findViewById(R.id.rb_dayofweek_tuesday);
		rb_dayofweek_wednesday = (RadioButton) findViewById(R.id.rb_dayofweek_wednesday);
		rb_dayofweek_thursday = (RadioButton) findViewById(R.id.rb_dayofweek_thursday);
		rb_dayofweek_friday = (RadioButton) findViewById(R.id.rb_dayofweek_friday);
		rb_year_first = (RadioButton) findViewById(R.id.rb_year_first);
		rb_year_second = (RadioButton) findViewById(R.id.rb_year_second);
		rb_year_third = (RadioButton) findViewById(R.id.rb_year_third);
		rb_year_fourth = (RadioButton) findViewById(R.id.rb_year_fourth);
	}

	private void setListener() {
		cb_dayofweek.setOnCheckedChangeListener(cbListener);
		cb_year.setOnCheckedChangeListener(cbListener);

	}

	private void setData() {
		cb_dayofweek.setChecked(PreferenceManager
				.getBooleanData(PreferenceManager.KEY_DAYOFWEEKCHECKED));
		cb_year.setChecked(PreferenceManager
				.getBooleanData(PreferenceManager.KEY_YEARCHECKED));
		if (cb_dayofweek.isChecked()) {
			Log.i("googry","day "+PreferenceManager.getIntData(PreferenceManager.KEY_DAYOFWEEK));
			switch (PreferenceManager.getIntData(PreferenceManager.KEY_DAYOFWEEK)) {
			case 0:
				rb_dayofweek_auto.setChecked(true);
				break;
			case 1:
				rb_dayofweek_monday.setChecked(true);
				break;
			case 2:
				rb_dayofweek_tuesday.setChecked(true);
				break;
			case 3:
				rb_dayofweek_wednesday.setChecked(true);
				break;
			case 4:
				rb_dayofweek_thursday.setChecked(true);
				break;
			case 5:
				rb_dayofweek_friday.setChecked(true);
				break;
			}
		}

		if (cb_year.isChecked()) {
			Log.i("googry", "year " + PreferenceManager.getIntData(PreferenceManager.KEY_YEAR));
			switch (PreferenceManager.getIntData(PreferenceManager.KEY_YEAR)) {
			case 1:
				rb_year_first.setChecked(true);
				break;
			case 2:
				rb_year_second.setChecked(true);
				break;
			case 3:
				rb_year_third.setChecked(true);
				break;
			case 4:
				rb_year_fourth.setChecked(true);
				break;

			}
		}

	}

	private CompoundButton.OnCheckedChangeListener cbListener = new CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.cb_dayofweek:
				if (isChecked) {
					rg_dayofweek.setVisibility(View.VISIBLE);
				} else {
					rg_dayofweek.setVisibility(View.GONE);
				}
				break;
			case R.id.cb_year:
				if (isChecked) {
					rg_year.setVisibility(View.VISIBLE);
				} else {
					rg_year.setVisibility(View.GONE);
				}
				break;
			}

		}
	};

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEKCHECKED,
				cb_dayofweek.isChecked());
		PreferenceManager.setData(PreferenceManager.KEY_YEARCHECKED,
				cb_year.isChecked());
		switch (rg_dayofweek.getCheckedRadioButtonId()) {
		case R.id.rb_dayofweek_auto:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,0);
			break;
		case R.id.rb_dayofweek_monday:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,1);
			break;
		case R.id.rb_dayofweek_tuesday:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,2);
			break;
		case R.id.rb_dayofweek_wednesday:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,3);
			break;
		case R.id.rb_dayofweek_thursday:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,4);
			break;
		case R.id.rb_dayofweek_friday:
			PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,5);
			break;
		}
		switch (rg_year.getCheckedRadioButtonId()) {
		case R.id.rb_year_first:
			PreferenceManager.setData(PreferenceManager.KEY_YEAR,1);
			break;
		case R.id.rb_year_second:
			PreferenceManager.setData(PreferenceManager.KEY_YEAR,2);
			break;
		case R.id.rb_year_third:
			PreferenceManager.setData(PreferenceManager.KEY_YEAR,3);
			break;
		case R.id.rb_year_fourth:
			PreferenceManager.setData(PreferenceManager.KEY_YEAR,4);
			break;

		}
		Log.i("googry","aday "+PreferenceManager.getIntData(PreferenceManager.KEY_DAYOFWEEK));
		Log.i("googry", "ayear " + PreferenceManager.getIntData(PreferenceManager.KEY_YEAR));
		super.onPause();
	}

}
