package com.jeniskasundra.sqlitealloprations.database;

import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ID;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_NAME;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_GENDER;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ADDRESS;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_MOBILE_NO;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_EMAIL;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.TABLE_STUDENT;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jeniskasundra.sqlitealloprations.model.Student;

public class DBManager {
	private static final String LOG_TAG = "Database";
	private static DBManager dbManager = null;
	private static DBContext databaseContext = null;
	private static SQLiteDatabase database = null;
	private AtomicInteger openCounter = new AtomicInteger();

	public synchronized static DBManager getInstance() {
		if (dbManager == null) {
			throw new IllegalStateException(
					String.format(
							"%s is not initialized, App initializeDB(..) method first.",
							DBManager.class.getSimpleName()));
		}
		return dbManager;
	}

	public static synchronized boolean initializeDB(Context context) {
		try {
			if (dbManager == null) {
				dbManager = new DBManager();
				databaseContext = new DBContext(context);
			}
			return true;
		} catch (Exception ex) {
			Log.e(LOG_TAG, "Error initializing DB", ex);
			return false;
		}
	}

	public synchronized SQLiteDatabase getWritableDB() {
		try {
			if (openCounter.incrementAndGet() == 1) {
				database = databaseContext.getWritableDatabase();
			}
			return database;
		} catch (Exception ex) {
			Log.e(LOG_TAG, "Error opening DB in W mode ", ex);
			return null;
		}
	}

	public synchronized boolean closeDatabase() {
		if (openCounter.decrementAndGet() == 0) {
			database.close();
		}
		return false;
	}

	public synchronized ArrayList<Student> getAllStudent() {
		ArrayList<Student> allStudent = new ArrayList<>();
		Cursor cursor = getInstance().getWritableDB().query(TABLE_STUDENT,
				null, null, null, null, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {

					int id = cursor.getInt(cursor
							.getColumnIndex(COLUMN_ID));
					String name = cursor.getString(cursor
							.getColumnIndex(COLUMN_NAME));
					String gender = cursor.getString(cursor
							.getColumnIndex(COLUMN_GENDER));
					String address = cursor.getString(cursor
							.getColumnIndex(COLUMN_ADDRESS));
					String mobileno = cursor.getString(cursor
							.getColumnIndex(COLUMN_MOBILE_NO));
					String email = cursor.getString(cursor
							.getColumnIndex(COLUMN_EMAIL));

					Student student = new Student(id,name,gender,address,mobileno,email);
					allStudent.add(student);

				} while (cursor.moveToNext());
			}
		}
		return allStudent;
	}

	public synchronized int getAllStudentCount() {

		Cursor cursor = getInstance().getWritableDB().query(TABLE_STUDENT,
				null, null, null, null, null, null);

		int size = 0;
		if (cursor != null) {
			size = cursor.getCount();
			cursor.close();
		}
		return size;
	}

	public synchronized Student getStudent(int id) {
		Cursor cursor = getInstance().getWritableDB()
				.query(TABLE_STUDENT,
						null,
						String.format(COLUMN_ID + " = '%s'",
								id), null, null, null, null);
		if (cursor != null)
			 cursor.moveToFirst();

					int sid = cursor.getInt(cursor
							.getColumnIndex(COLUMN_ID));
					String name = cursor.getString(cursor
							.getColumnIndex(COLUMN_NAME));
					String gender = cursor.getString(cursor
							.getColumnIndex(COLUMN_GENDER));
					String address = cursor.getString(cursor
							.getColumnIndex(COLUMN_ADDRESS));
					String mobileno = cursor.getString(cursor
							.getColumnIndex(COLUMN_MOBILE_NO));
					String email = cursor.getString(cursor
							.getColumnIndex(COLUMN_EMAIL));

					Student student = new Student(id,name,gender,address,mobileno,email);

		closeDatabase();
		return student;
	}

	public synchronized boolean addStudentDetail(Student student) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, student.getName());
		values.put(COLUMN_GENDER, student.getGender());
		values.put(COLUMN_ADDRESS, student.getAddress());
		values.put(COLUMN_MOBILE_NO, student.getMobile());
		values.put(COLUMN_EMAIL, student.getEmail());
		long id = getInstance().getWritableDB().insert(TABLE_STUDENT,
				null, values);
		closeDatabase();
		if (id != -1) {
			Log.e("tag", "item  inserted : " + id);
			return true;
		} else {
			Log.e("tag", "item not inserted : ");
			return false;
		}

	}

	public synchronized boolean updateStudent(Student student) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, student.getName());
		values.put(COLUMN_GENDER, student.getGender());
		values.put(COLUMN_ADDRESS, student.getAddress());
		values.put(COLUMN_MOBILE_NO, student.getMobile());
		values.put(COLUMN_EMAIL, student.getEmail());
		long id = getInstance().getWritableDB().update(TABLE_STUDENT,
				values,
				String.format(COLUMN_ID + "='%s'", student.getId()), null);
		closeDatabase();
		if (id != -1) {
			Log.e("tag", "item not inserted : ");
			return true;
		} else {
			Log.e("tag", "item inserted : " + id);
			return false;
		}
	}

	public synchronized boolean deleteImageItem(int id) {
		int affectedRows = getInstance().getWritableDB().delete(
				TABLE_STUDENT,
				String.format(COLUMN_ID + "='%s'",id), null);
		closeDatabase();
		if (affectedRows != 0) {
			return true;
		}
		return false;
	}


	public synchronized void clearData() {
		getInstance().getWritableDB().execSQL("DROP TABLE "+TABLE_STUDENT);
		databaseContext.onCreate(getInstance().getWritableDB());
	}

}