package com.unicodi.android.unicodi;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.unicodi.android.unicodi.utilgoogry.AppDataManagerUtil;
import com.unicodi.android.unicodi.utilhangyoung.DatabaseHandler;
import com.unicodi.android.unicodi.utilhangyoung.UserFunctions;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_identity, et_password;
	private Button btn_signin, btn_login;
	private CheckBox cb_autoLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initLayoutId();
		registerButtonClickListener();

		cb_autoLogin.setChecked(AppDataManagerUtil.getBooleanPreferences(
				getApplicationContext(), AppDataManagerUtil.AUTOLOGIN));
		if (cb_autoLogin.isChecked()) {
			et_identity.setText(AppDataManagerUtil.getStringPreferences(
					getApplicationContext(), AppDataManagerUtil.AUTOLOGINID));
			et_password.setText(AppDataManagerUtil.getStringPreferences(
					getApplicationContext(),
					AppDataManagerUtil.AUTOLOGINPASSWORD));
			new CommTask().execute();
		}

	}

	private void registerButtonClickListener() {
		btn_signin.setOnClickListener(this);
		btn_login.setOnClickListener(this);
	}

	private void initLayoutId() {
		et_identity = (EditText) findViewById(R.id.et_identity);
		et_identity.requestFocus();
		et_password = (EditText) findViewById(R.id.et_password);
		btn_signin = (Button) findViewById(R.id.btn_signin);
		btn_login = (Button) findViewById(R.id.btn_login);
		cb_autoLogin = (CheckBox) findViewById(R.id.cb_autoLogin);
	}

	@Override
	public void onClick(View v) {
		Intent activityIntent = null;
		switch (v.getId()) {
		case R.id.btn_signin:
			activityIntent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(activityIntent);
			break;
		case R.id.btn_login:
			new CommTask().execute();
			// activityIntent = new Intent(LoginActivity.this,
			// MainActivity.class);
			// startActivity(activityIntent);
			// finish();
			break;
		}
	}

	class CommTask extends AsyncTask<Void, Void, String> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected String doInBackground(Void... params) {

			String email = et_identity.getText().toString();
			String password = et_password.getText().toString();
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.loginUser(email, password);

			// check for login response
			try {
				if (json.getString(DatabaseHandler.KEY_SUCCESS) != null) {
					String res = json.getString(DatabaseHandler.KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						// user successfully logged in
						// Store user details in SQLite Database
						DatabaseHandler db = new DatabaseHandler(
								getApplicationContext());
						JSONObject json_user = json.getJSONObject("user");

						// Clear all previous data in database
						userFunction.logoutUser(getApplicationContext());
						db.addUser(
								json_user.getString(DatabaseHandler.KEY_NAME),
								json_user.getString(DatabaseHandler.KEY_EMAIL),
								json_user.getString(DatabaseHandler.KEY_PHONE),
								json_user.getString(DatabaseHandler.KEY_GENDER),
								json.getString(DatabaseHandler.KEY_UID),
								json_user
										.getString(DatabaseHandler.KEY_CREATED_AT));

						AppDataManagerUtil instance = AppDataManagerUtil
								.getInstance();
						instance.setUser_name(json_user
								.getString(DatabaseHandler.KEY_NAME));
						instance.setUser_id(json_user
								.getString(DatabaseHandler.KEY_EMAIL));
						instance.setUser_phone(json_user
								.getString(DatabaseHandler.KEY_PHONE));
						instance.setUser_gender(json_user
								.getString(DatabaseHandler.KEY_GENDER));
						// autoLogin check, id, password save
						AppDataManagerUtil.savePreferences(
								getApplicationContext(),
								AppDataManagerUtil.AUTOLOGIN,
								cb_autoLogin.isChecked());
						AppDataManagerUtil.savePreferences(
								getApplicationContext(),
								AppDataManagerUtil.AUTOLOGINID, et_identity
										.getText().toString());
						AppDataManagerUtil.savePreferences(
								getApplicationContext(),
								AppDataManagerUtil.AUTOLOGINPASSWORD,
								et_password.getText().toString());

						// Launch Dashboard Screen
						Intent dashboard = new Intent(getApplicationContext(),
								MainActivity.class);

						// Close all views before launching Dashboard
						dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(dashboard);

						// Close Login Screen
						finish();
					} else {
						// Error in login
						return "ID 혹은 암호가 틀립니다.";
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_SHORT).show();

			}
		}
	}

}
