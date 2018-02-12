package com.jeniskasundra.sqlitealloprations.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBContext extends SQLiteOpenHelper {
	private static final String LOG_TAG = "Database";
	private static final int DATABASE_VERSION = 1;

	// -----------------------------------------//
	private static final String DATABASE_NAME = "studentPortal.db";

	public static final String TABLE_STUDENT = "student";

	private static final String[] DATABASE_TABLES = {TABLE_STUDENT};

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_MOBILE_NO = "mono";
	public static final String COLUMN_EMAIL = "email";

	private static final String QUERY_CREATE_TABLE_STUDENT = String.format(
			"CREATE TABLE %s (" + // Table Name
					"%s INTEGER PRIMARY KEY AUTOINCREMENT, " + // Id
					"%s TEXT, " + // Name
					"%s TEXT, " + // Gender
					"%s TEXT, " + // Address
					"%s TEXT, " + // Mono
					"%s TEXT)", // Email
			TABLE_STUDENT, COLUMN_ID, COLUMN_NAME,
			COLUMN_GENDER, COLUMN_ADDRESS,COLUMN_MOBILE_NO,COLUMN_EMAIL);

	private static final String[] DATABASE_TABLES_QUERY = {QUERY_CREATE_TABLE_STUDENT};

	public DBContext(Context context) {
			super(context,DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		for (String table : DATABASE_TABLES_QUERY) {
			try {
				db.execSQL(table);
			} catch (Exception ex) {
				Log.e(LOG_TAG, "Error creating table", ex);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (String table : DATABASE_TABLES) {
			try {
				db.execSQL("DROP TABLE IF EXISTS " + table);
			} catch (Exception ex) {
				Log.e(LOG_TAG, "Error deleting table", ex);
			}
		}
		onCreate(db);
	}
}
