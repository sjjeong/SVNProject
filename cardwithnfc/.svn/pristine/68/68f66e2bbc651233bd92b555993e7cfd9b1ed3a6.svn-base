package com.googry.android.cardwithnfc;

import java.nio.charset.Charset;
import java.util.Locale;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.util.Log;

import com.googry.android.cardwithnfc.data.MyCard;
import com.googry.android.cardwithnfc.data.MyCardSingleton;

public class CardSendActivity extends Activity {
	private NfcAdapter mAdapter;
	private NdefMessage mMessage;

	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;

	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		setContentView(R.layout.activity_send);

		mAdapter = NfcAdapter.getDefaultAdapter(this);
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
				Log.i("googrybug", "test1");

			}
		}

		MyCard myCard = MyCardSingleton.getInstance().getMyCard();

		// NDEF Message생성
		mMessage = new NdefMessage(new NdefRecord[] {
				createTextRecord(myCard.getName(), Locale.ENGLISH, true),
				createTextRecord(myCard.getPhone(), Locale.ENGLISH, true),
				createTextRecord(myCard.getComName(), Locale.ENGLISH, true),
				createTextRecord(myCard.getComAddr(), Locale.ENGLISH, true),
				createTextRecord(myCard.getDepartment(), Locale.ENGLISH, true),
				createTextRecord(myCard.getPosition(), Locale.ENGLISH, true),
				createTextRecord(myCard.getNumber(), Locale.ENGLISH, true),
				createTextRecord(myCard.getFax(), Locale.ENGLISH, true),
				createTextRecord(myCard.getEmail(), Locale.ENGLISH, true), });
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mAdapter != null) {
			mAdapter.disableForegroundNdefPush(this);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mAdapter != null) {
			mAdapter.enableForegroundNdefPush(this, mMessage);
			mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
					mTechLists);
		}
	}

	// NdefRecord 만드는 메서드
	public static NdefRecord createTextRecord(String text, Locale locale,
			boolean encodeInUtf8) {
		byte[] langBytes = locale.getLanguage().getBytes(
				Charset.forName("US-ASCII"));
		Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset
				.forName("UTF-16");
		byte[] textBytes = text.getBytes(utfEncoding);
		int utfBit = encodeInUtf8 ? 0 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);
		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
				textBytes.length);
		return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,
				new byte[0], data);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if (intent != null) {
			Log.i("googrybug", "test2");
		}
	}
}
