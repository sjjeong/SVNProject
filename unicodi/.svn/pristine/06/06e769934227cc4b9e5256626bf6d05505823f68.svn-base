package com.unicodi.android.unicodi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unicodi.android.unicodi.utilgoogry.AppDataManagerUtil;
import com.unicodi.android.unicodi.utilgoogry.Closet;
import com.unicodi.android.unicodi.utilgoogry.ImageUtil;
import com.unicodi.android.unicodi.utilhangyoung.ImageLoadTask;

public class MainActivity extends Activity implements OnClickListener {
	private ViewPager mPager;
	private PagerAdapterClass pagerAdapter;
	private final int REQUESTCODESETTINGACTIVITY = 1;
	private final int REQUESTCODEPROFILEMODIFYACTIVITY = 2;

	private Button btn_newsfeed, btn_mycloset, btn_setting;
	// gridview test
	private Activity act = this;
	// private List<ResolveInfo> apps;
	// private PackageManager pm;

	// private List<List<ResolveInfo>> applist;

	// myClosetes
	private GridView gv_mycloset_closetes;
	private int myclosetMode = 0;
	private ArrayList<ArrayList<Closet>> myClosetList;
	// When completed the implementation of myClosetes query,
	// CATEGORYSIZE variable value change 2 to 4
	private final int CATEGORYSIZE = 2;
	// When completed the implementation of myClosetes query, Delete this final
	// variable
	private final int MYCLOSETESTOPCOUNT = 9;
	private final int MYCLOSETESBOTTOMCOUNT = 7;
	// Delete End

	// AppDataManager Instance;
	private AppDataManagerUtil instance = AppDataManagerUtil.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setLayoutId();

		// myClosetes
		myClosetList = new ArrayList<ArrayList<Closet>>();
		// Set data in myCllosets
		ArrayList<Closet> topList = new ArrayList<Closet>();
		for (int i = 0; i < MYCLOSETESTOPCOUNT; i++) {
			String resNAme = "@drawable/page_closet_top" + (i + 1);
			int resID = getResources().getIdentifier(resNAme, "drawable",
					this.getPackageName());
			Drawable drawable = getResources().getDrawable(resID);
			Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			drawable.draw(canvas);
			Closet closet = new Closet(bitmap,
					"캐주얼룩 정장룩 등 아무데나 잘어울려서 자주 입는 옷!! 인터넷 쇼핑몰에서 샀음~");
			topList.add(closet);
		}
		myClosetList.add(topList);
		ArrayList<Closet> bottomList = new ArrayList<Closet>();
		for (int i = 0; i < MYCLOSETESBOTTOMCOUNT; i++) {
			String resNAme = "@drawable/page_closet_bottom" + (i + 1);
			int resID = getResources().getIdentifier(resNAme, "drawable",
					this.getPackageName());
			Drawable drawable = getResources().getDrawable(resID);
			Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
			drawable.draw(canvas);
			Closet closet = new Closet(bitmap,
					"계절에 구애받지 않고 매치하기 쉬움! 젤 중요한건 정말 편함...!");
			bottomList.add(closet);
		}
		myClosetList.add(bottomList);
		myClosetList.add(new ArrayList<Closet>());
		myClosetList.add(new ArrayList<Closet>());

		// // gridview test
		// Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		// mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		//
		// pm = getPackageManager();
		// apps = pm.queryIntentActivities(mainIntent, 0);
		// applist = new ArrayList<List<ResolveInfo>>();
		//
		// for (int i = 0; i < CATEGORYSIZE; i++) {
		// List<ResolveInfo> tempList = new ArrayList<ResolveInfo>();
		// int listSize = apps.size() / CATEGORYSIZE;
		// for (int j = i * listSize; j < (i + 1) * listSize; j++) {
		// tempList.add(apps.get(j));
		// }
		// applist.add(tempList);
		// }
		// // gridview test end

		ImageUtil.setScreenHeight(this);
		ImageUtil.setScreenWidth(this);

		mPager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new PagerAdapterClass(getApplicationContext());
		mPager.setAdapter(pagerAdapter);

	}

	private void setLayoutId() {
		btn_newsfeed = (Button) findViewById(R.id.btn_newsfeed);
		btn_mycloset = (Button) findViewById(R.id.btn_mycloset);
		btn_setting = (Button) findViewById(R.id.btn_setting);

		btn_newsfeed.setOnClickListener(this);
		btn_mycloset.setOnClickListener(this);
		btn_setting.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(MainActivity.this, TestActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setCurrentInflateItem(int type) {
		if (type == 0) {
			mPager.setCurrentItem(0);
		} else {
			mPager.setCurrentItem(1);
		}
	}

	/*
	 * Layout
	 */

	private View.OnClickListener mPagerListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String text = ((Button) v).getText().toString();
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
					.show();
		}
	};

	/**
	 * PagerAdapter
	 */
	private class PagerAdapterClass extends PagerAdapter {

		private LayoutInflater mInflater;

		public PagerAdapterClass(Context c) {
			super();
			mInflater = LayoutInflater.from(c);
		}

		@Override
		public int getCount() {
			return 2;
		}

		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if (position == 0) {
				// newsfeed
				v = mInflater.inflate(R.layout.inflate_newsfeed, null);
				Button btn_newsfeed_write = (Button) v
						.findViewById(R.id.btn_newsfeed_write);
				btn_newsfeed_write.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(MainActivity.this,
								AddNewsfeedActivity.class));
					}
				});
			} else if (position == 1) {
				// mycloset
				v = mInflater.inflate(R.layout.inflate_mycloset, null);
				ImageView iv_mycloset_profilePicture = (ImageView) v
						.findViewById(R.id.iv_mycloset_profilePicture);
				Bitmap ret = null;
				try {
					// ret = new CommTask().execute().get();
					ret = new ImageLoadTask(getApplicationContext()).execute()
							.get();
				} catch (Exception e) {
				}
				if (ret != null) {
					iv_mycloset_profilePicture.setImageBitmap(ret);
				}
				/*
				 * else{ if(instance.getBmpProfilePicture()!= null)
				 * iv_mycloset_profilePicture
				 * .setImageBitmap(instance.getBmpProfilePicture()); }
				 */

				TextView tv_mycloset_id = (TextView) v
						.findViewById(R.id.tv_mycloset_id);
				tv_mycloset_id.setText(instance.getUser_id());

				TextView tv_mycloset_profileMemo = (TextView) v
						.findViewById(R.id.tv_mycloset_profileMemo);
				tv_mycloset_profileMemo.setText(AppDataManagerUtil
						.getStringPreferences(getApplicationContext(),
								AppDataManagerUtil.PROFILEMEMO));

				Button btn_profileModify = (Button) v
						.findViewById(R.id.btn_mycloset_profileModify);
				// profile modify
				btn_profileModify.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						startActivityForResult(new Intent(MainActivity.this,
								ProfileModifyActivity.class),
								REQUESTCODEPROFILEMODIFYACTIVITY);
					}
				});

				// closet amount
				TextView tv_mycloset_topAmount = (TextView) v
						.findViewById(R.id.tv_mycloset_topAmount);
				TextView tv_mycloset_bottomAmount = (TextView) v
						.findViewById(R.id.tv_mycloset_bottomAmount);
				TextView tv_mycloset_outwearAmount = (TextView) v
						.findViewById(R.id.tv_mycloset_outwearAmount);
				TextView tv_mycloset_etcAmount = (TextView) v
						.findViewById(R.id.tv_mycloset_etcAmount);
				tv_mycloset_topAmount.setText(myClosetList.get(0).size() + "");
				tv_mycloset_bottomAmount.setText(myClosetList.get(1).size()
						+ "");
				tv_mycloset_outwearAmount.setText(myClosetList.get(2).size()
						+ "");
				tv_mycloset_etcAmount.setText(myClosetList.get(3).size() + "");

				// add clothes
				Button btn_addClothes = (Button) v
						.findViewById(R.id.btn_mycloset_addClothes);
				btn_addClothes.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(MainActivity.this,
								AddClothesActivity.class));
					}
				});

				gv_mycloset_closetes = (GridView) v
						.findViewById(R.id.gv_mycloset_closetes);
				final GridAdapter gridAdatper = new GridAdapter();
				gv_mycloset_closetes.setAdapter(gridAdatper);

				final Button btn_mycloset_top = (Button) v
						.findViewById(R.id.btn_mycloset_top);
				final Button btn_mycloset_bottom = (Button) v
						.findViewById(R.id.btn_mycloset_bottom);
				final Button btn_mycloset_outwear = (Button) v
						.findViewById(R.id.btn_mycloset_outwear);
				final Button btn_mycloset_etc = (Button) v
						.findViewById(R.id.btn_mycloset_etc);
				btn_mycloset_top.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						myclosetMode = 0;
						gridAdatper.notifyDataSetChanged();
						btn_mycloset_top
								.setBackgroundResource(R.drawable.page_closet_middle_bar_top_sel);
						btn_mycloset_bottom
								.setBackgroundResource(R.drawable.page_closet_middle_bar_bottom);
						btn_mycloset_outwear
								.setBackgroundResource(R.drawable.page_closet_middle_bar_outwear);
						btn_mycloset_etc
								.setBackgroundResource(R.drawable.page_closet_middle_bar_etc);

					}
				});
				btn_mycloset_bottom.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						myclosetMode = 1;
						gridAdatper.notifyDataSetChanged();
						btn_mycloset_top
								.setBackgroundResource(R.drawable.page_closet_middle_bar_top);
						btn_mycloset_bottom
								.setBackgroundResource(R.drawable.page_closet_middle_bar_bottom_sel);
						btn_mycloset_outwear
								.setBackgroundResource(R.drawable.page_closet_middle_bar_outwear);
						btn_mycloset_etc
								.setBackgroundResource(R.drawable.page_closet_middle_bar_etc);
					}
				});
				btn_mycloset_outwear.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						myclosetMode = 2;
						gridAdatper.notifyDataSetChanged();
						btn_mycloset_top
								.setBackgroundResource(R.drawable.page_closet_middle_bar_top);
						btn_mycloset_bottom
								.setBackgroundResource(R.drawable.page_closet_middle_bar_bottom);
						btn_mycloset_outwear
								.setBackgroundResource(R.drawable.page_closet_middle_bar_outwear_sel);
						btn_mycloset_etc
								.setBackgroundResource(R.drawable.page_closet_middle_bar_etc);
					}
				});
				btn_mycloset_etc.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						myclosetMode = 3;
						gridAdatper.notifyDataSetChanged();
						btn_mycloset_top
								.setBackgroundResource(R.drawable.page_closet_middle_bar_top);
						btn_mycloset_bottom
								.setBackgroundResource(R.drawable.page_closet_middle_bar_bottom);
						btn_mycloset_outwear
								.setBackgroundResource(R.drawable.page_closet_middle_bar_outwear);
						btn_mycloset_etc
								.setBackgroundResource(R.drawable.page_closet_middle_bar_etc_sel);
					}
				});
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
		public void setPrimaryItem(View container, int position, Object object) {
			switch (position) {
			case 0:
				btn_newsfeed
						.setBackgroundResource(R.drawable.page_newspeed_newspeed_bar_sel);
				btn_mycloset
						.setBackgroundResource(R.drawable.page_newspeed_closet_bar);
				break;
			case 1:
				btn_newsfeed
						.setBackgroundResource(R.drawable.page_closet_newspeed_bar);
				btn_mycloset
						.setBackgroundResource(R.drawable.page_closet_mycloset_bar_sel);
				break;
			}
		}
	}

	private class GridAdapter extends BaseAdapter {
		LayoutInflater inflater;
		// List<ResolveInfo> itemList;
		ArrayList<Closet> itemList;

		public GridAdapter() {
			inflater = (LayoutInflater) act
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// itemList = applist.get(myclosetMode);
			itemList = myClosetList.get(myclosetMode);
		}

		@Override
		public void notifyDataSetChanged() {
			// itemList = applist.get(myclosetMode);
			itemList = myClosetList.get(myclosetMode);
			super.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item, parent, false);
			}
			// final ResolveInfo info = itemList.get(position);
			final Closet info = itemList.get(position);
			ImageView item = (ImageView) convertView.findViewById(R.id.iv_item);
			// item.setImageDrawable(info.activityInfo
			// .loadIcon(getPackageManager()));
			item.setImageBitmap(info.getBmpCloset());
			item.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this,
							MyClosetDetailActivity.class);
					intent.putExtra("bmpCloset", info.getBmpCloset());
					intent.putExtra("Comment", info.getComment());
					Log.i("googrybug", info.getComment());
					startActivity(intent);
				}
			});
			// item.setOnTouchListener(new OnTouchListener() {
			//
			// @Override
			// public boolean onTouch(View v, MotionEvent event) {
			// int action = event.getAction();
			// if (action == MotionEvent.ACTION_UP) {
			// Intent intent = new Intent(MainActivity.this,
			// MyClosetDetailActivity.class);
			// intent.putExtra("closet", info);
			// startActivity(intent);
			// }
			// return false;
			// }
			// });
			return convertView;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_newsfeed:
			setCurrentInflateItem(0);
			break;
		case R.id.btn_mycloset:
			setCurrentInflateItem(1);
			break;
		case R.id.btn_setting:
			startActivityForResult(new Intent(MainActivity.this,
					SettingActivity.class), REQUESTCODESETTINGACTIVITY);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case REQUESTCODESETTINGACTIVITY:// logout
				AppDataManagerUtil.savePreferences(getApplicationContext(),
						AppDataManagerUtil.AUTOLOGIN, false);
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
				finish();
				break;
			case REQUESTCODEPROFILEMODIFYACTIVITY:
				mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
				setCurrentInflateItem(1);
				break;
			}
		}
	}

}
