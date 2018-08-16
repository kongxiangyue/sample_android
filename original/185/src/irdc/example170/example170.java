package irdc.example170; 

/* import���class */

import irdc.example170.R;

import java.net.URL; 
import java.net.URLConnection; 

import java.net.URLConnection; 
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap; 
import android.graphics.BitmapFactory; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText;
import android.widget.ImageView;  
 

public class example170 extends Activity 
{ 
  /* �������� */
  private Button mButton1;
  private Button mButton2;
  private EditText mEditText;
  private ImageView mImageView; 
  private Bitmap bm;

  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 

    /* ��ʼ������ */
    mButton1 =(Button) findViewById(R.id.myButton1);
    mButton2 =(Button) findViewById(R.id.myButton2);
    mEditText = (EditText) findViewById(R.id.myEdit);
    mImageView = (ImageView) findViewById(R.id.myImage);
    mButton2.setEnabled(false);

    /* Ԥ��ͼƬ��Button */
    mButton1.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v)
      {
        String path=mEditText.getText().toString();
        if(path.equals(""))
        {
          showDialog("��ַ����Ϊ�հ�!");
        }
        else
        {
          /* ����type=1ΪԤ��ͼƬ */
          setImage(path,1);
        }
      } 
    });

    /* ��ͼƬ��Ϊ�����Button */
    mButton2.setOnClickListener(new Button.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        try
        {
          String path=mEditText.getText().toString();
          if(path.equals(""))
          {
            showDialog("��ַ����Ϊ�հ�!");
          }
          else
          {
            /* ����type=2Ϊ�������� */
            setImage(path,2);
          }
        }
        catch (Exception e)
        {
          showDialog("��ȡ����!��ַ���ܲ���ͼƬ����ַ����!");
          bm = null;
          mImageView.setImageBitmap(bm);
          mButton2.setEnabled(false);
          e.printStackTrace();
        }
      } 
    }); 
  }

  /* ��ͼƬץ����Ԥ��������Ϊ����ķ��� */
  private void setImage(String path,int type)
  {
    try 
    {
      URL url = new URL(path); 
      URLConnection conn = url.openConnection(); 
      conn.connect();
      if(type==1)
      {
        /* Ԥ��ͼƬ */
        bm = BitmapFactory.decodeStream(conn.getInputStream());
        mImageView.setImageBitmap(bm);
        mButton2.setEnabled(true);
      }
      else if(type==2)
      {
        /* ����Ϊ���� */
        example170.this.setWallpaper(conn.getInputStream());
        bm = null;
        mImageView.setImageBitmap(bm);
        mButton2.setEnabled(false);
        showDialog("���汳���������!");
      }
    }
    catch (Exception e) 
    {
      showDialog("��ȡ����!��ַ���ܲ���ͼƬ����ַ����!");
      bm = null;
      mImageView.setImageBitmap(bm);
      mButton2.setEnabled(false);
      e.printStackTrace(); 
    } 
  }

  /* ����Dialog�ķ��� */
  private void showDialog(String mess){
    new AlertDialog.Builder(example170.this).setTitle("Message")
    .setMessage(mess)
    .setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog, int which)
      {          
      }
    })
    .show();
  }
}