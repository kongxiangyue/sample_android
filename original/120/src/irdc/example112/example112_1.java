package irdc.example112;

/* import���class */
import irdc.example112.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* ʵ����������Dialog��Activity */
public class example112_1 extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    /* ���������徯ʾ  */
    new AlertDialog.Builder(example112_1.this)
        .setIcon(R.drawable.clock)
        .setTitle("��������!!")
        .setMessage("�Ͽ��𴲰�!!!")
        .setPositiveButton("�ص���",
         new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichButton)
          {
            /* �ر�Activity */
            example112_1.this.finish();
          }
        })
        .show();
  } 
}
