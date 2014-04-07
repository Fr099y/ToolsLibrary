package fr099y.lib.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataLoaderDB extends SQLiteOpenHelper{

	private final static String DATABASE_NAME="toolsLibrary.db";
	private final String TABLE_NAME="loadedDatas";
	private final String COL_NAME_1="url";
	private final String COL_NAME_2="value";
	public DataLoaderDB(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE="create table "+TABLE_NAME+" ("+COL_NAME_1+" text, "+COL_NAME_2+" text)";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertData(String url, String data)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(COL_NAME_1, url);
		values.put(COL_NAME_2, data);
		if(db.update(TABLE_NAME, values, COL_NAME_1+"=?", new String[] {url})==0)
			db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public String getData(String url)
	{
		String data=null;
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursor=db.query(TABLE_NAME, new String[] {COL_NAME_1, COL_NAME_2}, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			do {
				if(cursor.getString(0).equals(url))
				{
					data=cursor.getString(1);
					break;
				}
			} while (cursor.moveToNext());
		}
		if(cursor!=null || !cursor.isClosed())
			cursor.close();
		db.close();
		return data;
	}

}
