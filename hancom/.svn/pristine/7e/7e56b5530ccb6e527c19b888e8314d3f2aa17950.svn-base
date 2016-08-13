package com.googry.hancom.timetable;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.googry.hancom.R;
import com.googry.hancom.professor.Professor;
import com.googry.hancom.professor.ProfessorData;

public class LectureListPager {
	private Context _Context;
	private View _View;

	private ListView lv_lecture;
	private ListViewAdapter mAdapter;

	private ArrayList<Lecture> alLectures;

	public LectureListPager(Context context) {
		this._Context = context;
		_View = LayoutInflater.from(context).inflate(
				R.layout.viewpager_lectures, null);
		onCreate();
	}

	public View getView() {
		return _View;
	}

	public void refresh() {
		alLectures = Timetable.getInstance().getLectureList();
		mAdapter.notifyDataSetChanged();
	}

	private void onCreate() {
		// alLectures = new ArrayList<Professor>();
		alLectures = Timetable.getInstance().getLectureList();
		if (alLectures.size() == 0) {
			LinearLayout ll_lecture = (LinearLayout) _View
					.findViewById(R.id.ll_lecture);
			ll_lecture.setVisibility(View.VISIBLE);
		} else {
			mAdapter = new ListViewAdapter();
			lv_lecture = (ListView) _View.findViewById(R.id.lv_lecture);
			lv_lecture.setAdapter(mAdapter);
			lv_lecture.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					// Lecture pf = alLectures.get(position);
					// Intent intent = new Intent(_Context,
					// ProfessorDetailActivity.class);
					// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// intent.putExtra("professor", pf);
					// _Context.startActivity(intent);

				}
			});
		}
	}

	private class ViewHolder {
		public ImageView iv_prof;
		public TextView tv_lecture_name;
		public TextView tv_lecture_professor;
		public TextView tv_lecture_location;
		public TextView tv_lecture_time;
	}

	private class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return alLectures.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return alLectures.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) _Context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.item_lecture, null);

				holder.iv_prof = (ImageView) convertView
						.findViewById(R.id.iv_prof);
				holder.tv_lecture_name = (TextView) convertView
						.findViewById(R.id.tv_lecture_name);
				holder.tv_lecture_professor = (TextView) convertView
						.findViewById(R.id.tv_lecture_professor);
				holder.tv_lecture_location = (TextView) convertView
						.findViewById(R.id.tv_lecture_location);
				holder.tv_lecture_time = (TextView) convertView
						.findViewById(R.id.tv_lecture_time);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// set data to view
			Lecture l = alLectures.get(position);
			holder.iv_prof.setImageResource(R.drawable.ic_launcher);
			for (Professor pf : ProfessorData.professor) {
				if (pf.getName().equals(l.getProf())) {
					holder.iv_prof.setImageResource(pf.getPictureId());
					break;
				}
			}
			String strName = "";
			strName += l.getName() + "(" + l.getYear() + "학년" + ")";
			holder.tv_lecture_name.setText(strName);
			holder.tv_lecture_professor.setText(l.getProf());
			holder.tv_lecture_location.setText(l.getLocation());
			holder.tv_lecture_time.setText(l.getTime());

			return convertView;
		}
	}
}
