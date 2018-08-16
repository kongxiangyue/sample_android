package irdc.example134;

import irdc.example134.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class example134 extends Activity
{ 
  /*分别声明TextView对象,String类型数组，两个文本字符串变量*/
  private TextView mTextView1;
  public String[] jieshour;
  public String zhuti;
  public String zhengweny;
  
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
    
    /*创建两个TextView对象*/ 
    mTextView1 = (TextView) findViewById(R.id.myTextView1); 
    mTextView1.setText("没有任何未读信息..."); 
    
    try
    {
      /*取得从短信传来的Bundle对象bunde*/
      Bundle bunde = this.getIntent().getExtras(); 
      if (bunde!= null)
      {
        /*取出bunde中的字符串*/
        String mm = bunde.getString("STR_INPUT");
        /*定义Intent对象mEmailIntent来传送E-mail*/
        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);
        /*设置邮件格式为"plain/text"*/
        mEmailIntent.setType("plain/text");
        
        /*
        * 分别获取收件人地址、附件、主题和正文信息
        */
        jieshour =new String[]{"jay.mingchieh@gmail.com"};
        zhuti = "有一条短信未读!!";
        zhengweny = mm.toString();
        
        /*将取得的字符串放入mEmailIntent中*/
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
