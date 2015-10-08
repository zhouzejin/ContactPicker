package com.sunny.contactpicker;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactPickerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_picker);
		
		final Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		
		String[] from = new String[] { Contacts.DISPLAY_NAME_PRIMARY };
		int[] to = new int[] { R.id.itemTextView };
		
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, R.layout.item_contacter, cursor, from, to);
		ListView lv = (ListView) findViewById(R.id.contactListView);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				cursor.moveToPosition(position);
				int rowId = cursor.getInt(cursor.getColumnIndex("_id"));
				Uri outURI = ContentUris
						.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
				
				Intent outData = new Intent();
				outData.setData(outURI);
				setResult(Activity.RESULT_OK, outData);
				finish();
			}
		});
	}

}
