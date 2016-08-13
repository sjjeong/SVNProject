package com.googry.hancom;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.googry.hancom.option.OptionPager;
import com.googry.hancom.option.preference.PreferenceManager;
import com.googry.hancom.professor.ProfessorListPager;
import com.googry.hancom.timetable.Lecture;
import com.googry.hancom.timetable.LectureData;
import com.googry.hancom.timetable.LectureListPager;
import com.googry.hancom.timetable.Timetable;
import com.viewpagerindicator.UnderlinePageIndicator;

public class MainActivity extends Activity implements OnClickListener {
	private ViewPager mPager;
	private UnderlinePageIndicator mIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PreferenceManager.setContext(getApplicationContext());
		if (PreferenceManager.getBooleanData(PreferenceManager.KEY_DAYOFWEEKCHECKED) == false) {
			if (PreferenceManager.getIntData(PreferenceManager.KEY_DAYOFWEEK) == -1) {
				
				PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEKCHECKED,
						true);
				PreferenceManager.setData(PreferenceManager.KEY_DAYOFWEEK,0);
			}
		}

		Timetable tt = Timetable.getInstance();
		for (Lecture l : LectureData.lecture) {
			tt.addLecture(l);
		}


		setLayout();

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new PagerAdapterClass());

		mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
		mIndicator.setFades(false);
		mIndicator.setViewPager(mPager);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_class:
			setCurrentInflateItem(0);
			break;
		case R.id.btn_prof:
			setCurrentInflateItem(1);
			break;
		case R.id.btn_option:
			setCurrentInflateItem(2);
			break;
		}
	}

	private void setCurrentInflateItem(int type) {
		switch (type) {
		case 0:
			mPager.setCurrentItem(0);
			break;
		case 1:
			mPager.setCurrentItem(1);
			break;
		case 2:
			mPager.setCurrentItem(2);
			break;
		}
	}

	/*
	 * Layout
	 */
	private void setLayout() {
		Button btn_class = (Button) findViewById(R.id.btn_class);
		Button btn_prof = (Button) findViewById(R.id.btn_prof);
		Button btn_option = (Button) findViewById(R.id.btn_option);

		btn_class.setOnClickListener(this);
		btn_prof.setOnClickListener(this);
		btn_option.setOnClickListener(this);
	}

	/**
	 * PagerAdapter
	 */
	private class PagerAdapterClass extends PagerAdapter {

		// private LayoutInflater mInflater;
		//
		// public PagerAdapterClass(Context c) {
		// super();
		// mInflater = LayoutInflater.from(c);
		// }

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			switch (position) {
			case 0:
				// lecture
				LectureListPager lectureListPager = new LectureListPager(
						getApplicationContext());
				v = lectureListPager.getView();

				// remove
				// v = mInflater.inflate(R.layout.viewpager_timetable, null);
				// ImageView iv_timetable = (ImageView) v
				// .findViewById(R.id.iv_timetable);
				// iv_timetable.setOnClickListener(new OnClickListener() {
				//
				// @Override
				// public void onClick(View v) {
				// // TODO Auto-generated method stub
				// Intent intent = new Intent(getApplicationContext(),
				// TimetableActivity.class);
				// startActivity(intent);
				// }
				// });
				break;
			case 1:
				ProfessorListPager professorActivity = new ProfessorListPager(
						getApplicationContext());
				v = professorActivity.getView();
				// office
				break;
			case 2:
				OptionPager optionPager = new OptionPager(
						getApplicationContext());
				v = optionPager.getView();
			default:
				break;
			}

			((ViewPager) pager).addView(v, 0);

			return v;
		}

		@Override
		public void destroyItem(View pager, int position, Object view) {
			((ViewPager) pager).removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View pager, Object obj) {
			return pager == obj;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		@Override
		public void finishUpdate(View arg0) {
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Timetable tt = Timetable.getInstance();
		tt.clearAlLecture();
		super.onDestroy();
	}

}
