package irdc.example099;

import irdc.example099.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class example099 extends Activity
{
  private AutoCompleteTextView myAutoCompleteTextView;
  private TextView myTextView1;
  private Cursor contactCursor;
  private ContactsAdapter myContactsAdapter;
  /* 要捞出通讯录的字段 */
  public static final String[] PEOPLE_PROJECTION = new String[]
  { Contacts.People._ID, Contacts.People.PRIMARY_PHONE_ID,
      Contacts.People.TYPE, Contacts.People.NUMBER, Contacts.People.LABEL,
      Contacts.People.NAME };

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myAutoCompleteTextView = (AutoCompleteTextView)
    findViewById(R.id.myAutoCompleteTextView);
    myTextView1 = (TextView) findViewById(R.id.myTextView1);

    /* 取得ContentResolver */
    ContentResolver content = getContentResolver();

    /* 取得通讯录的Cursor */
    contactCursor = content.query
    (
      Contacts.People.CONTENT_URI,
      PEOPLE_PROJECTION, null, null,
      Contacts.People.DEFAULT_SORT_ORDER
    );

    /* 将Cursor传入自己实现的ContactsAdapter */
    myContactsAdapter = new ContactsAdapter(this, contactCursor);

    myAutoCompleteTextView.setAdapter(myContactsAdapter);

    myAutoCompleteTextView.setOnItemClickListener
    (new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick
      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
      {
        /* 取得Cursor */
        Cursor c = myContactsAdapter.getCursor();
        /* 移到所点击的位置 */
        c.moveToPosition(arg2);
        String number = c.getString
        (c.getColumnIndexOrThrow(Contacts.People.NUMBER));
        /* 当找不到电话时显示无输入电话 */
        number = number == null ? "没有输入电话号码" : number;
        myTextView1.setText(c.getString
        (c.getColumnIndexOrThrow(Contacts.People.NAME))
        + "的电话号码是" + number);
      }
    });  }
}
