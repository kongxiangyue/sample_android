package irdc.add;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import irdc.add.R;

import java.util.ArrayList;
import java.util.List;

public class add extends Activity
{
  private static final String[] countriesStr =
  { "Android多媒体", "Android网络", "Android驱动", "Android游戏" };
  private TextView myTextView;
  private EditText myEditText;
  private Button myButton_add;
  private Button myButton_remove;
  private Spinner mySpinner;
  private ArrayAdapter<String> adapter;
  private List<String> allCountries;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    /* 载入main.xml Layout */
    setContentView(R.layout.main);

    allCountries = new ArrayList<String>();
    for (int i = 0; i < countriesStr.length; i++)
    {
      allCountries.add(countriesStr[i]);
    }

    /* new ArrayAdapter对象并将allCountries传入 */
    adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, allCountries);
    adapter
        .setDropDownViewResource
         (android.R.layout.simple_spinner_dropdown_item);

    /* 以findViewById()取得对象 */
    myTextView = (TextView) findViewById(R.id.myTextView);
    myEditText = (EditText) findViewById(R.id.myEditText);
    myButton_add = (Button) findViewById(R.id.myButton_add);
    myButton_remove = (Button) findViewById(R.id.myButton_remove);
    mySpinner = (Spinner) findViewById(R.id.mySpinner);

    /* 将ArrayAdapter添加Spinner对象中 */
    mySpinner.setAdapter(adapter);

    /* 将myButton_add添加OnClickListener */
    myButton_add.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        String newCountry = myEditText.getText().toString();

        /* 先比较添加的值是否已存在，不存在才可添加 */
        for (int i = 0; i < adapter.getCount(); i++)
        {
          if (newCountry.equals(adapter.getItem(i)))
          {
            return;
          }
        }

        if (!newCountry.equals(""))
        {
          /* 将值添加至adapter */
          adapter.add(newCountry);
          /* 取得添加的值的位置 */
          int position = adapter.getPosition(newCountry);
          /* 将Spinner选择在添加的值的位置 */
          mySpinner.setSelection(position);
          /* 将myEditText清空 */
          myEditText.setText("");
        }

      }
    });

    /* 将myButton_remove添加OnClickListener */
    myButton_remove.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View arg0)
      {
        if (mySpinner.getSelectedItem() != null)
        {
          /* 删除mySpinner的值 */
          adapter.remove(mySpinner.getSelectedItem().toString());
          /* 将myEditText清空 */
          myEditText.setText("");
          if (adapter.getCount() == 0)
          {
            /* 将myTextView清空 */
            myTextView.setText("");
          }
        }
      }
    });

    /* 将mySpinner添加OnItemSelectedListener */
    mySpinner.setOnItemSelectedListener
    (new Spinner.OnItemSelectedListener()
    {

      @Override
      public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
          long arg3)
      {
        /* 将所选mySpinner的值带入myTextView中 */
        myTextView.setText(arg0.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {
      }
    });
  }
}
