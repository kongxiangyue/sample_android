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
  /* Ҫ�̳�ͨѶ¼���ֶ� */
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

    /* ȡ��ContentResolver */
    ContentResolver content = getContentResolver();

    /* ȡ��ͨѶ¼��Cursor */
    contactCursor = content.query
    (
      Contacts.People.CONTENT_URI,
      PEOPLE_PROJECTION, null, null,
      Contacts.People.DEFAULT_SORT_ORDER
    );

    /* ��Cursor�����Լ�ʵ�ֵ�ContactsAdapter */
    myContactsAdapter = new ContactsAdapter(this, contactCursor);

    myAutoCompleteTextView.setAdapter(myContactsAdapter);

    myAutoCompleteTextView.setOnItemClickListener
    (new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick
      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
      {
        /* ȡ��Cursor */
        Cursor c = myContactsAdapter.getCursor();
        /* �Ƶ��������λ�� */
        c.moveToPosition(arg2);
        String number = c.getString
        (c.getColumnIndexOrThrow(Contacts.People.NUMBER));
        /* ���Ҳ����绰ʱ��ʾ������绰 */
        number = number == null ? "û������绰����" : number;
        myTextView1.setText(c.getString
        (c.getColumnIndexOrThrow(Contacts.People.NAME))
        + "�ĵ绰������" + number);
      }
    });  }
}
