package com.googry.android.cardwithnfc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Charsets;
import com.google.common.primitives.Bytes;
import com.googry.android.cardwithnfc.data.DBManager;
import com.googry.android.cardwithnfc.data.MyCardSingleton;
import com.googry.android.cardwithnfc.nfc.NdefMessageParser;
import com.googry.android.cardwithnfc.nfc.ParsedRecord;
import com.googry.android.cardwithnfc.nfc.TextRecord;

public class CardListActivity extends Activity {
	private SQLiteDatabase sqldb;
	private Cursor cursor;
	private ListView lv_personlist;
	private ImageView iv_nocard;
	private ArrayList<Person> al_person;
	private PersonAdapter personAdapter;
	private DBManager dbManager;
	private MyCardSingleton singleton;
	private ImageButton btn_sendCard, btn_option;

	// Receive
	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;
	public static final int TYPE_TEXT = 1;
	public static final int TYPE_URI = 2;
	private String sqlInsert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardlist);
		setCardList();
		setCardReceive();

	}

	private void setCardList() {
		singleton = MyCardSingleton.getInstance();
		dbManager = singleton.getDbManager();

		btn_sendCard = (ImageButton) findViewById(R.id.btn_sendCard);
		btn_option = (ImageButton) findViewById(R.id.btn_option);

		btn_sendCard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(CardListActivity.this,
						CardSendActivity.class);
				startActivity(intent);
			}
		});
		btn_option.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CardListActivity.this,
						MyCardActivity.class);
				startActivity(intent);
			}
		});

		lv_personlist = (ListView) findViewById(R.id.lv_personlist);
		iv_nocard = (ImageView) findViewById(R.id.iv_nocard);
		if(dbManager == null)
			finish();
		sqldb = dbManager.getWritableDatabase();

		cursor = sqldb.rawQuery("select name,phone from mycard;", null);

		al_person = new ArrayList<Person>();

		while (cursor.moveToNext()) {
			al_person.add(new Person(cursor.getString(0), cursor.getString(1)));
		}

		if (al_person.isEmpty()) {
			lv_personlist.setVisibility(View.GONE);
			iv_nocard.setVisibility(View.VISIBLE);
		} else {
			lv_personlist.setVisibility(View.VISIBLE);
			iv_nocard.setVisibility(View.GONE);
		}

		personAdapter = new PersonAdapter(this, R.layout.row, al_person);

		setListView();
		cursor.close();
	}

	private void setListView() {
		lv_personlist.setAdapter(personAdapter);
		lv_personlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos,
					long id) {
				YourCardDialog ycd = new YourCardDialog(CardListActivity.this);
				cursor = sqldb.rawQuery("select * from mycard;", null);
				while (cursor.moveToNext()) {
					if (!cursor.getString(1).equals(
							al_person.get(pos).getNumber())
							|| !cursor.getString(0).equals(
									al_person.get(pos).getName()))
						continue;
					ycd.setName(cursor.getString(0));
					ycd.setPhone("휴대폰 : " + cursor.getString(1));
					ycd.setComName(cursor.getString(2));
					ycd.setComAddr(cursor.getString(3));
					ycd.setDepartment(cursor.getString(4));
					ycd.setPosition(cursor.getString(5));
					ycd.setNumber("전화 : " + cursor.getString(6));
					ycd.setFax("FAX : " + cursor.getString(7));
					ycd.setEmail("Email : " + cursor.getString(8));

				}
				ycd.show();

			}
		});
		lv_personlist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> list, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				final int position = pos;
				Log.i("DB", "onItemLongClick");
				new AlertDialog.Builder(CardListActivity.this)
						.setTitle("명함삭제")
						.setMessage(
								al_person.get(pos).getName()
										+ "의 명함을 삭제하시겠습니까?")
						.setPositiveButton("확인",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										sqldb.execSQL("delete from mycard where name='"
												+ al_person.get(position)
														.getName()
												+ "' and phone='"
												+ al_person.get(position)
														.getNumber() + "';");
										al_person.remove(position);
										personAdapter.notifyDataSetChanged();
										if (al_person.isEmpty()) {
											lv_personlist
													.setVisibility(View.GONE);
											iv_nocard
													.setVisibility(View.VISIBLE);
										} else {
											lv_personlist
													.setVisibility(View.VISIBLE);
											iv_nocard
													.setVisibility(View.GONE);
										}
									}
								})
						.setNegativeButton("취소",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
				return true;
			}
		});
	}

	private class PersonAdapter extends ArrayAdapter<Person> {

		private ArrayList<Person> items;

		public PersonAdapter(Context context, int textViewResourceId,
				ArrayList<Person> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}
			Person p = items.get(position);
			if (p != null) {
				TextView tt = (TextView) v.findViewById(R.id.toptext);
				TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				if (tt != null) {
					tt.setText(p.getName());
				}
				if (bt != null) {
					bt.setText("전화번호: " + p.getNumber());
				}
			}
			return v;
		}

	}

	class Person {

		private String Name;
		private String Number;

		public Person(String _Name, String _Number) {
			this.Name = _Name;
			this.Number = _Number;
		}

		public String getName() {
			return Name;
		}

		public String getNumber() {
			return Number;
		}

	}

	// Receive
	private void setCardReceive() {
		mAdapter = NfcAdapter.getDefaultAdapter(this);

		sqlInsert = "";

		Intent targetIntent = new Intent(this, CardListActivity.class);
		targetIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mPendingIntent = PendingIntent.getActivity(this, 0, targetIntent, 0);
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		try {
			ndef.addDataType("*/*");
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);
		}
		mFilters = new IntentFilter[] { ndef, };
		mTechLists = new String[][] { new String[] { NfcF.class.getName() } };
		Intent passedIntent = getIntent();
		if (passedIntent != null) {
			String action = passedIntent.getAction();
			if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
				processTag(passedIntent);
			}
		}
	}

	private void processTag(Intent passedIntent) {
		Parcelable[] rawMsgs = passedIntent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

		if (rawMsgs == null) {
			return;

		}
		NdefMessage[] msgs;
		if (rawMsgs != null) {
			msgs = new NdefMessage[rawMsgs.length];
			for (int i = 0; i < rawMsgs.length; i++) {
				msgs[i] = (NdefMessage) rawMsgs[i];
				showTag(msgs[i]);
			}
		}
	}

	private int showTag(NdefMessage mMessage) {
		List<ParsedRecord> records = NdefMessageParser.parse(mMessage);
		final int size = records.size();
		for (int i = 0; i < size; i++) {
			ParsedRecord record = records.get(i);
			int recordType = record.getType();
			if (recordType == ParsedRecord.TYPE_TEXT) {
				sqlInsert += "'" + ((TextRecord) record).getText() + "'";
				if (i == size - 1) {
					break;
				} else {
					sqlInsert += ",";
				}
			}
		}
		final ParsedRecord record1 = records.get(0);
		final ParsedRecord record2 = records.get(1);
		Cursor cursor = sqldb.rawQuery("select name,phone from mycard;", null);
		if (cursor.getCount() == 0) {
			new AlertDialog.Builder(CardListActivity.this)
					.setTitle("명함수신성공!")
					.setMessage("명함 수신에 성공하였습니다.")
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									sqldb.execSQL("insert into mycard values("
											+ sqlInsert + ");");
									al_person.add(new Person(
											((TextRecord) record1).getText(),
											((TextRecord) record2).getText()));
									Log.i("googrybug", "1-al_person size : "
											+ al_person.size());
									personAdapter.notifyDataSetChanged();
									if (al_person.isEmpty()) {
										lv_personlist.setVisibility(View.GONE);
										iv_nocard
												.setVisibility(View.VISIBLE);
									} else {
										lv_personlist
												.setVisibility(View.VISIBLE);
										iv_nocard.setVisibility(View.GONE);
									}
								}
							}).show();
		}
		while (cursor.moveToNext()) {
			if (!((TextRecord) record1).getText().equals(cursor.getString(0))
					|| !((TextRecord) record2).getText().equals(
							cursor.getString(1))) {
				new AlertDialog.Builder(CardListActivity.this)
						.setTitle("명함수신성공!")
						.setMessage("명함 수신에 성공하였습니다.")
						.setPositiveButton("확인",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										sqldb.execSQL("insert into mycard values("
												+ sqlInsert + ");");

										al_person.add(new Person(
												((TextRecord) record1)
														.getText(),
												((TextRecord) record2)
														.getText()));
										Log.i("googrybug",
												"2-al_person size : "
														+ al_person.size());
										personAdapter.notifyDataSetChanged();
										if (al_person.isEmpty()) {
											lv_personlist
													.setVisibility(View.GONE);
											iv_nocard
													.setVisibility(View.VISIBLE);
										} else {
											lv_personlist
													.setVisibility(View.VISIBLE);
											iv_nocard
													.setVisibility(View.GONE);
										}
									}
								}).show();

			} else {
				new AlertDialog.Builder(CardListActivity.this)
						.setTitle("명함수신실패!")
						.setMessage("동일한 이름과 휴대번호가 존재합니다.")
						.setPositiveButton("확인",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
									}
								}).show();
			}

		}
		Log.i("googrybug", "DataSetChanged");
		return size;
	}

	private NdefMessage createTagMessage(String msg, int type) {
		NdefRecord[] records = new NdefRecord[1];
		if (type == TYPE_TEXT) {
			records[0] = createTextRecord(msg, Locale.KOREAN, true);
		} else if (type == TYPE_URI) {
			records[0] = createUriRecord(msg.getBytes());
		}
		NdefMessage mMessage = new NdefMessage(records);
		return mMessage;
	}

	private NdefRecord createTextRecord(String text, Locale locale,
			boolean encodeInUtf8) {
		final byte[] langBytes = locale.getLanguage().getBytes(
				Charsets.US_ASCII);
		final Charset utfEncoding = encodeInUtf8 ? Charsets.UTF_8 : Charset
				.forName("UTF-16");
		final byte[] textBytes = text.getBytes(utfEncoding);
		final int utfBit = encodeInUtf8 ? 0 : (1 << 7);
		final char status = (char) (utfBit + langBytes.length);
		final byte[] data = Bytes.concat(new byte[] { (byte) status },
				langBytes, textBytes);
		return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,
				new byte[0], data);
	}

	private NdefRecord createUriRecord(byte[] data) {
		return new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, NdefRecord.RTD_URI,
				new byte[0], data);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if (intent != null) {
			processTag(intent);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mAdapter != null) {
			mAdapter.disableForegroundDispatch(this);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mAdapter != null) {
			mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
					mTechLists);
		}
	}
}
