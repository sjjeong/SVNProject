package com.googry.hancom.option;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.googry.hancom.R;

public class OptionPager implements OnClickListener {
	private Context _Context;
	private View _View;

	private Button btn_curriculum, btn_alltimetable, btn_office, btn_setting;

	public OptionPager(Context context) {
		this._Context = context;
		_View = LayoutInflater.from(context).inflate(R.layout.viewpager_option,
				null);
		onCreate();
	}

	public View getView() {
		return _View;
	}

	private void onCreate() {
		doInit();
		
	}

	private void doInit() {
		btn_curriculum = (Button) _View.findViewById(R.id.btn_curriculum);
		btn_alltimetable = (Button) _View.findViewById(R.id.btn_alltimetable);
		btn_office = (Button) _View.findViewById(R.id.btn_office);
		btn_setting = (Button) _View.findViewById(R.id.btn_setting);

		btn_curriculum.setOnClickListener(this);
		btn_alltimetable.setOnClickListener(this);
		btn_office.setOnClickListener(this);
		btn_setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_curriculum:
			intent = new Intent(_Context,CurriculumActivity.class);
			break;
		case R.id.btn_alltimetable:
			intent = new Intent(_Context,AlltimetableActivity.class);
			break;
		case R.id.btn_office:
			intent = new Intent(_Context, OfficeActivity.class);
			break;
		case R.id.btn_setting:
			intent = new Intent(_Context, TimetableSettingActivity.class);
			break;
		}
		if (intent != null) {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			_Context.startActivity(intent);
		}
	}

}
