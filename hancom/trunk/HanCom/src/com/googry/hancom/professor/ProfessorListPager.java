package com.googry.hancom.professor;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.googry.hancom.R;

public class ProfessorListPager {
	private Context _Context;
	private View _View;

	private ListView lv_professor;
	private ListViewAdapter mAdapter;

	private ArrayList<Professor> alProfessor;

	public ProfessorListPager(Context context) {
		this._Context = context;
		_View = LayoutInflater.from(context).inflate(
				R.layout.viewpager_professor, null);
		onCreate();
	}

	public View getView() {
		return _View;
	}

	private void onCreate() {
		getLayoutId();
		makeData();
		mAdapter = new ListViewAdapter();
		lv_professor.setAdapter(mAdapter);
		lv_professor.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Professor pf = alProfessor.get(position);
				Intent intent = new Intent(_Context,
						ProfessorDetailActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("professor", pf);
				_Context.startActivity(intent);

			}
		});

	}

	private void makeData() {
		alProfessor = new ArrayList<Professor>();
		for (Professor pf : ProfessorData.professor) {
			alProfessor.add(pf);
		}

	}

	private void getLayoutId() {
		lv_professor = (ListView) _View.findViewById(R.id.lv_professor);
	}

	private class ViewHolder {
		public ImageView iv_prof;
		public TextView tv_name;
		public TextView tv_lab;
		public TextView tv_email;
		public Button btn_callLab;
	}

	private class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return alProfessor.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return alProfessor.get(position);
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
				convertView = inflater.inflate(R.layout.item_professor, null);
				holder.iv_prof = (ImageView) convertView
						.findViewById(R.id.iv_prof);
				holder.tv_name = (TextView) convertView
						.findViewById(R.id.tv_name);
				holder.tv_lab = (TextView) convertView
						.findViewById(R.id.tv_lab);
				holder.tv_email = (TextView) convertView
						.findViewById(R.id.tv_email);
				holder.btn_callLab = (Button) convertView
						.findViewById(R.id.btn_callLab);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final Professor pf = alProfessor.get(position);

			holder.iv_prof.setImageResource(pf.getPictureId());
			holder.tv_name.setText(pf.getName());
			holder.tv_lab.setText(pf.getLab());
			holder.tv_email.setText(pf.getEmail());
			holder.btn_callLab.setVisibility(View.GONE);
			// holder.btn_callLab.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// Intent intent = new
			// Intent(Intent.ACTION_CALL,Uri.parse("tel:"+pf.getTel()));
			// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// _Context.startActivity(intent);
			// }
			// });

			return convertView;
		}
	}
}