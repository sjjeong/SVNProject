package com.example.haniumproject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haniumproject.Util.IntentUtil;
import com.example.haniumproject.server.RequestWebServer;
import com.example.haniumproject.server.RequestWebServer.GetServerJsonMessage;

public class SearchBeaconActivity extends Activity {
	private BluetoothAdapter mBluetoothAdapter;
	// private Handler mHandler;

	private static final int REQUEST_ENABLE_BT = 1;

	// Stops scanning after 10 seconds.
	// private static final long SCAN_PERIOD = 10000;

	private TextView tv_device_mac_addr, tv_device_rssi;
	private BLEDevice belDevice;
	private String deviceId, username, gender, age;
	private TextView tv_test;
	private String str_log;
	private WebView webView;

	private ArrayList<BLEDevice> alDevice = new ArrayList<SearchBeaconActivity.BLEDevice>();
	private ProgressDialog wifiProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchbeacon);
		// tv_device_mac_addr = (TextView)
		// findViewById(R.id.tv_device_mac_addr);
		// tv_device_rssi = (TextView) findViewById(R.id.tv_device_rssi);
		// mHandler = new Handler();
		wifiProgressDialog = new ProgressDialog(SearchBeaconActivity.this);
		wifiProgressDialog.setTitle("검색중...");
		wifiProgressDialog.setMessage("Beacon을 찾는중...");
		wifiProgressDialog.show();
		wifiProgressDialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tv_test = (TextView) findViewById(R.id.textView1);
		str_log = "";

		webView = (WebView) findViewById(R.id.webView1);
		webView.setWebViewClient(new WebViewClient());
		WebSettings set = webView.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		// webView.loadUrl("http://www.google.com");
		// Use this check to determine whether BLE is supported on the device.
		// Then you can
		// selectively disable BLE-related features.
		if (!getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_BLUETOOTH_LE)) {
			Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT)
					.show();
			finish();
		}

		// Initializes a Bluetooth adapter. For API level 18 and above, get a
		// reference to
		// BluetoothAdapter through BluetoothManager.
		final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();

		// Checks if Bluetooth is supported on the device.76
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, R.string.error_bluetooth_not_supported,
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		final TelephonyManager tm = (TelephonyManager) getBaseContext()
				.getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		deviceId = deviceUuid.toString();
		Intent intent = getIntent();
		try {
			username = URLEncoder.encode(
					intent.getStringExtra(IntentUtil.USER_NAME), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		age = intent.getStringExtra(IntentUtil.USER_AGE);
		try {
			gender = URLEncoder.encode(
					intent.getStringExtra(IntentUtil.USER_GENDER), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Ensures Bluetooth is enabled on the device. If Bluetooth is not
		// currently enabled,
		// fire an intent to display a dia4 asking the user to grant permission
		// to enable it.
		if (!mBluetoothAdapter.isEnabled()) {
			if (!mBluetoothAdapter.isEnabled()) {
				Intent enableBtIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
		}

		// Initializes list view adapter.
		// mLeDeviceListAdapter = new LeDeviceListAdapter();
		// setListAdapter(mLeDeviceListAdapter);
		scanLeDevice(true);
	}

	@Override
	protected void onPause() {
		super.onPause();
		scanLeDevice(false);
		
		// mLeDeviceListAdapter.clear();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i("googrybug", "onStop");
		super.onStop();
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("googrybug","onDestory");
		for (int i = 0; i < alDevice.size(); i++) {
			BLEDevice temp = alDevice.get(i);
			// if (temp.isCheckin()) {
			RequestWebServer server = new RequestWebServer();
			String pushMsg = String
					.format("Hanium/MainProcess?device_id=%s&username=%s&position=%s&age=%s&gender=%s&preempt=%s",
							deviceId, username, temp.getDevice().getAddress(),
							age, gender, "false");
			server.setMsgs(pushMsg);
			server.start();

			Toast.makeText(SearchBeaconActivity.this,
					"disconnect : " + temp.getDevice().getAddress(),
					Toast.LENGTH_SHORT).show();
			// }
		}
		super.onDestroy();
	}

	private void scanLeDevice(final boolean enable) {
		if (enable) {
			// Stops scanning after a pre-defined scan period.
			// mHandler.postDelayed(new Runnable() {
			// @Override
			// public void run() {
			// mBluetoothAdapter.stopLeScan(mLeScanCallback);
			// }
			// }, SCAN_PERIOD);

			mBluetoothAdapter.startLeScan(mLeScanCallback);
		} else {
			mBluetoothAdapter.stopLeScan(mLeScanCallback);
		}
	}

	// Device scan callback.
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(final BluetoothDevice device, int rssi,
				byte[] scanRecord) {
			final int mRssi = rssi;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					int loop = alDevice.size();
					int i;
					for (i = 0; i < loop; i++) {
						if (alDevice.get(i).getDevice().getAddress()
								.equals(device.getAddress())) {
							alDevice.get(i).setDevice(device);
							alDevice.get(i).setRssi(mRssi);
							break;
						}
					}
					if (loop == i) {
						alDevice.add(new BLEDevice(device, mRssi, false));
					}
					loop = alDevice.size();
					for (i = 0; i < loop; i++) {
						BLEDevice temp = alDevice.get(i);
						// 들어올때
						if (temp.getRssi() > -50 && !temp.isCheckin()) {
							temp.setCheckin(true);
							RequestWebServer server = new RequestWebServer();
							String pushMsg = String
									.format("Hanium/MainProcess?device_id=%s&username=%s&position=%s&age=%s&gender=%s&preempt=%s",
											deviceId, username, temp
													.getDevice().getAddress(),
											age, gender, "true");
							server.setMsgs(pushMsg);
							server.start();
							server.setOnGetServerJsonMessageListener(new GetServerJsonMessage() {

								@Override
								public void onGetServerMsgs(String requestCode,
										String[] parserString) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onGetServerMsg(String requestCode,
										String msg) {
									String url = "";
									try {
										JSONObject json = new JSONObject(msg);
										String msg1 = "";
										String msg2 = "";
										String msg3 = "";
										String charsetName = "UTF-8";
										try {
											msg1 = URLDecoder.decode(
													json.getString("msg1"),
													charsetName);
											msg2 = URLDecoder.decode(
													json.getString("msg2"),
													charsetName);
											msg3 = URLDecoder.decode(
													json.getString("msg3"),
													charsetName);
										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										url = String
												.format("http://113.198.84.51:8090/Hanium/Web/Mobile.jsp?item=%s&name=%s&itemId=%s",
														msg2, msg1,msg3);

										// String temp = json.getString("msg1");
										// try {
										// url = URLEncoder.encode(temp,
										// "EUC-KR");
										// } catch (UnsupportedEncodingException
										// e) {
										// // TODO Auto-generated catch block
										// e.printStackTrace();
										// }
										// 113.198.84.51:8090/Hanium/Web/Display.html?name=&item=&code=

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									Log.i("googrybug", url);
									// webView.loadUrl(url);
									// wifiProgressDialog.dismiss();
									Intent intent = new Intent(
											Intent.ACTION_VIEW, Uri.parse(url));
									startActivity(intent);
								}
							});
							Toast.makeText(
									SearchBeaconActivity.this,
									"connect : "
											+ temp.getDevice().getAddress(),
									Toast.LENGTH_SHORT).show();
							str_log += temp.device.getAddress() + "connect\n";
							tv_test.setText(str_log);
						}
						// 떠날때
						if (temp.getRssi() < -60 && temp.isCheckin()) {
							temp.setCheckin(false);
							RequestWebServer server = new RequestWebServer();
							String pushMsg = String
									.format("Hanium/MainProcess?device_id=%s&username=%s&position=%s&age=%s&gender=%s&preempt=%s",
											deviceId, username, temp
													.getDevice().getAddress(),
											age, gender, "false");
							server.setMsgs(pushMsg);
							server.start();
							Toast.makeText(
									SearchBeaconActivity.this,
									"disconnect : "
											+ temp.getDevice().getAddress(),
									Toast.LENGTH_SHORT).show();
							str_log += temp.device.getAddress()
									+ "disconnect\n";
							tv_test.setText(str_log);
						}
					}

					// if (belDevice == null) {
					// belDevice = new BLEDevice(device, mRssi);
					// } else if (device.getAddress().equals(
					// belDevice.getDevice().getAddress())) {
					// /*
					// * MAC address 동일
					// */
					// if (mRssi > -45) {
					// // server
					// RequestWebServer server = new RequestWebServer();
					// String pushMsg = String
					// .format("Hanium/MainProcess?device_id=%s&username=%s&position=%s&age=%s&gender=%s",
					// deviceId, username,
					// device.getAddress(), age, gender);
					// server.setMsgs(pushMsg);
					// server.start();
					// }
					// belDevice.setRssi(mRssi);
					// } else {
					// if (mRssi > belDevice.getRssi()) {
					// /*
					// * 가장 가까운게 갱신 됬을떄
					// */
					// // server
					// RequestWebServer server = new RequestWebServer();
					// String pushMsg = String
					// .format("Hanium/MainProcess?device_id=%s&username=%s&position=%s&age=%s&gender=%s",
					// deviceId, username,
					// device.getAddress(), age, gender);
					// server.setMsgs(pushMsg);
					// server.start();
					// belDevice.setDevice(device);
					// belDevice.setRssi(mRssi);
					//
					// }
					// }
					// tv_device_mac_addr.setText(belDevice.getDevice()
					// .getAddress());
					// tv_device_rssi.setText(belDevice.getRssi() + "");
				}

			});
		}
	};

	private class BLEDevice {
		private BluetoothDevice device;
		private int rssi;
		private boolean checkin;

		public BLEDevice(BluetoothDevice device, int rssi, boolean checkin) {
			this.device = device;
			this.rssi = rssi;
			this.checkin = checkin;
		}

		public BluetoothDevice getDevice() {
			return device;
		}

		public void setDevice(BluetoothDevice device) {
			this.device = device;
		}

		public int getRssi() {
			return rssi;
		}

		public void setRssi(int rssi) {
			this.rssi = rssi;
		}

		public boolean isCheckin() {
			return checkin;
		}

		public void setCheckin(boolean checkin) {
			this.checkin = checkin;
		}

	}

	class WebClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	}
}
