package irdc.example103;

import irdc.example103.R;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class example103 extends Activity
{
  private Button myButton1;
  private Button myButton2;
  private File cacheDir;
  private File fileDir;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    myButton1 = (Button) findViewById(R.id.myButton1);
    myButton2 = (Button) findViewById(R.id.myButton2);

    /*  ȡ��ĿǰCacheĿ¼ */
    cacheDir = this.getCacheDir();
    /*  ȡ��ĿǰFileĿ¼ */
    fileDir = this.getFilesDir();

    myButton1.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {

        String path = fileDir.getParent() + java.io.File.separator
            + fileDir.getName();

        showListActivity(path);

      }
    });

    myButton2.setOnClickListener(new Button.OnClickListener()
    {

      @Override
      public void onClick(View arg0)
      {
        String path = cacheDir.getParent() + java.io.File.separator
            + cacheDir.getName();

        showListActivity(path);
      }
    });

  }

  /*����shili13_1����·������ */
  private void showListActivity(String path)
  {
    Intent intent = new Intent();
    intent.setClass(example103.this, example103_1.class);

    Bundle bundle = new Bundle();
    bundle.putString("path", path);
    intent.putExtras(bundle);

    startActivity(intent);
  }

}