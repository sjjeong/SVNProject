package com.unicodi.android.unicodi;

import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.unicodi.android.unicodi.utilhangyoung.DatabaseHandler;
import com.unicodi.android.unicodi.utilhangyoung.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	Button btnRegister;
	// Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	EditText inputPhone;
	RadioGroup radioGenderGroup;
	RadioButton radioGenderButton;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_PHONE = "phone";
	private static String KEY_GENDER = "gender";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		inputFullName = (EditText) findViewById(R.id.registerName);
		inputEmail = (EditText) findViewById(R.id.registerEmail);
		inputPhone = (EditText) findViewById(R.id.registerPhone);
		inputPassword = (EditText) findViewById(R.id.registerPassword);
		radioGenderGroup = (RadioGroup) findViewById(R.id.radioGender);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		// btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				new CommTask().execute();
				// Launch Dashboard Screen
				Intent dashboard = new Intent(getApplicationContext(),
						LoginActivity.class);
				// Close all views before launching Dashboard
				dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(dashboard);

			}
		});

		// Link to Login Screen
		// btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View view) {
		// Intent i = new Intent(getApplicationContext(), LoginActivity.class);
		// startActivity(i);
		// // Close Registration View
		// finish();
		// }
		// });
	}

	class CommTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected Void doInBackground(Void... params) {
			
			String name = inputFullName.getText().toString();
			String email = inputEmail.getText().toString();
			String password = inputPassword.getText().toString();
			String phone = inputPhone.getText().toString();

			int selectedGender = radioGenderGroup.getCheckedRadioButtonId();
			radioGenderButton = (RadioButton) findViewById(selectedGender);
			String gender = radioGenderButton.getText().toString();

			try{
			name = URLEncoder.encode(inputFullName.getText().toString(),"UTF-8");
			gender = URLEncoder.encode(radioGenderButton.getText().toString(),"UTF-8");
			} catch(Exception e){}
			
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.registerUser(name, email, password,
					phone, gender);

			try {
				if (json.getString(KEY_SUCCESS) != null) {
					String res = json.getString(KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						// user successfully registred
						// Store user details in SQLite Database
						DatabaseHandler db = new DatabaseHandler(
								getApplicationContext());
						JSONObject json_user = json.getJSONObject("user");

						// Clear all previous data in database
						userFunction.logoutUser(getApplicationContext());
						db.addUser(json_user.getString(KEY_NAME),
								json_user.getString(KEY_EMAIL),
								json_user.getString(KEY_PHONE),
								json_user.getString(KEY_GENDER),
								json.getString(KEY_UID),
								json_user.getString(KEY_CREATED_AT));

						// Close Registration Screen
						finish();
					} else {
						// Error in registration
						// registerErrorMsg.setText("Error occured in registration");
						finish();
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

	}
}
