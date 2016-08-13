package com.unicodi.android.unicodi.utilhangyoung;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "unicodi14";

	// Login table name
	private static final String TABLE_LOGIN = "login";
	private static final String TABLE_BOARD = "board";
	private static final String TABLE_IMAGE = "image";

	// Login Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_UID = "uid";
	public static final String KEY_CREATED_AT = "created_at";
	public static String KEY_SUCCESS = "success";
    public static String KEY_ERROR = "error";
    public static String KEY_ERROR_MSG = "error_msg";
    
	public static final String KEY_BID = "bid";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_CONTENT = "content";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_PHONE + " TEXT,"
				+ KEY_GENDER + " TEXT," + KEY_UID + " TEXT," + KEY_CREATED_AT
				+ " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);

		String CREATE_BOARD_TABLE = "CREATE TABLE " + TABLE_BOARD + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_CONTENT + " TEXT,"
				+ KEY_BID + " TEXT," + KEY_CREATED_AT + " TEXT" + ")";
		db.execSQL(CREATE_BOARD_TABLE);

		String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_BID + " TEXT,"
				+ KEY_IMAGE + " BLOB" + ")";
		db.execSQL(CREATE_IMAGE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String phone, String gender,
			String uid, String created_at) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_PHONE, phone);
		values.put(KEY_GENDER, gender);
		values.put(KEY_UID, uid); // Email
		values.put(KEY_CREATED_AT, created_at); // Created At

		// Inserting Row
		db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put(KEY_NAME, cursor.getString(1));
			user.put(KEY_EMAIL, cursor.getString(2));
			user.put(KEY_PHONE, cursor.getString(3));
			user.put(KEY_GENDER, cursor.getString(4));
			user.put(KEY_UID, cursor.getString(5));
			user.put(KEY_CREATED_AT, cursor.getString(6));
		}
		cursor.close();
		db.close();
		// return user
		return user;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
//		db.delete(TABLE_BOARD, null, null);
//		db.delete(TABLE_IMAGE, null, null);
		db.close();
	}

}