package irdc.example111;

import irdc.example111.R;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class example111_2 extends Activity
{
  private String path;
  private String data;
  private EditText myEditText1;

  private EditText myDialogEditText;
  protected final static int MENU_SAVE = Menu.FIRST;
  private String fileName;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ex111_2);

    /* 放置文件内容的EditText */
    myEditText1 = (EditText) findViewById(R.id.myEditText1);
    
    Bundle bunde = this.getIntent().getExtras();
    path = bunde.getString("path");
    data = bunde.getString("data");
    fileName = bunde.getString("fileName");
    myEditText1.setText(data);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    super.onOptionsItemSelected(item);
    switch (item.getItemId())
    {
      case MENU_SAVE:
        saveFile();
        break;
    }
    return true;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    super.onCreateOptionsMenu(menu);
    /* 添加MENU */
    menu.add(Menu.NONE, MENU_SAVE, 0, R.string.strSaveMenu);
    return true;
  }

  private void saveFile()
  {
    /* 跳出存档的Dialog */
    LayoutInflater factory = LayoutInflater.from(this);
    
    final View textEntryView = factory.inflate
    (R.layout.dialog1, null);
    
    Builder mBuilder1 = new AlertDialog.Builder(example111_2.this);
    
    mBuilder1.setView(textEntryView);
    /* 取得Dialog里的EditText */
    myDialogEditText = (EditText) textEntryView.findViewById
                                  (R.id.myDialogEditText);
    
    myDialogEditText.setText(fileName);

    mBuilder1.setPositiveButton
    (
      R.string.str_alert_ok,new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface dialoginterface, int i)
        {
          /* 存档 */
          String Filename = path + java.io.File.separator
              + myDialogEditText.getText().toString();
          java.io.BufferedWriter bw;
          try
          {
            bw = new java.io.BufferedWriter(new java.io.FileWriter(
                new java.io.File(Filename)));
            String str = myEditText1.getText().toString();
            bw.write(str, 0, str.length());
            bw.newLine();
            bw.close();
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
          /* 返回到example111_1 */
          Intent intent = new Intent();
          intent.setClass(example111_2.this, example111_1.class);
          Bundle bundle = new Bundle();
          /* 将路径传到example111_1 */
          bundle.putString("path", path);
          intent.putExtras(bundle);
          startActivity(intent);
          
          finish();
        }
      });
    mBuilder1.setNegativeButton(R.string.str_alert_cancel, null);
    mBuilder1.show();
  }
}
