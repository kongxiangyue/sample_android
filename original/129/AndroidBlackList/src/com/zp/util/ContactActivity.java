package com.zp.util;

import com.zp.R;

import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactActivity extends ListActivity {
	private Cursor curContacts;
	private String selContactName;
	private String selContactNumber;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle(R.string.contactsTitle);
		selContactName = "";
		selContactNumber = "";
		fillContact();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Uri uri = Contacts.People.CONTENT_URI;
		String[] projection = new String[] {
				Contacts.People.NAME,
                Contacts.People.NUMBER
        };
//		String selection = Contacts.People._ID + "=" + id + "";
//		System.out.println("=========");
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		if(cursor.moveToPosition(position)) {
			selContactName = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.People.NAME));	
			selContactNumber = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.People.NUMBER));
		}
//		if(cursor.moveToNext()) {
//			selContactNumber = cursor.getString(cursor.getColumnIndexOrThrow(Contacts.People.NUMBER));
//		}
		//System.out.println(selContactName);
		Bundle bundle = new Bundle();
		bundle.putString("Name", selContactName);
		bundle.putString("Number", selContactNumber);
		Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(RESULT_OK, mIntent);
		finish();
	}
	/**
	 * 获取android电话本
	 */
	private void fillContact() {
		//异常直接返回
		if(!getContacts()) {
			return;
		}
        String[] fields = new String[] {
        		Contacts.People.DISPLAY_NAME
        };
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.contact_entry, curContacts, fields, new int[] {R.id.contactEntryText});
        setListAdapter(adapter);		
	}
	/**
	 * 获取联系人
	 * @return
	 */
	private boolean getContacts(){
		try{
			Uri uri = Contacts.People.CONTENT_URI;
			String[] projection = new String[] {
					Contacts.People.NAME,
	                Contacts.People.NUMBER
	        };
			///String selection = Contacts.People.TYPE_MOBILE + "='1'";
	        //String sortOrder = Contacts.People.DISPLAY_NAME + " COLLATE LOCALIZED ASC";   
	        String[] selectionArgs = null;
	        curContacts = managedQuery(uri, null, null, selectionArgs, null);
	        startManagingCursor(curContacts);
//	        curContacts.moveToFirst();
//	        String strName =  curContacts.getString(curContacts.getColumnIndexOrThrow(Contacts.People.NAME));
//	        String strPhone = curContacts.getString(curContacts.getColumnIndexOrThrow(Contacts.People.NUMBER));
//	        Log.i("People", "电话本名称" +  strName);
//	        Log.i("People", "电话本号码" + strPhone);
//	        
			return true;				
		}catch(Exception e) {
			e.printStackTrace();
			setTitle(e.toString());
			return false;
		}
	
	}	
}
