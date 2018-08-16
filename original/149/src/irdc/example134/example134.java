package irdc.example134;

import irdc.example134.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class example134 extends Activity
{ 
  /*�ֱ�����TextView����,String�������飬�����ı��ַ�������*/
  private TextView mTextView1;
  public String[] jieshour;
  public String zhuti;
  public String zhengweny;
  
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*��������TextView����*/ 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText("û���κ�δ����Ϣ..."); 
    
    try
    {
      /*ȡ�ôӶ��Ŵ�����Bundle����bunde*/
      Bundle bunde = this.getIntent().getExtras(); 
      if (bunde!= null)
      {
        /*ȡ��bunde�е��ַ���*/
        String mm = bunde.getString("STR_INPUT");
        /*����Intent����mEmailIntent������E-mail*/
        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);
        /*�����ʼ���ʽΪ"plain/text"*/
        mEmailIntent.setType("plain/text");
        
        /*
        * �ֱ��ȡ�ռ��˵�ַ�������������������Ϣ
        */
        jieshour =new String[]{"jay.mingchieh@gmail.com"};
        zhuti = "��һ������δ��!!";
        zhengweny = mm.toString();
        
        /*��ȡ�õ��ַ�������mEmailIntent��*/
        mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
        jieshour); 
        mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
        zhuti);
        mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        zhengweny);
        startActivity(Intent.createChooser(mEmailIntent, 
        getResources().getString(R.string.str_message)));
      }
      else
      {
        finish();
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
