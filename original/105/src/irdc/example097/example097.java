package irdc.example097;
import irdc.example097.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class example097 extends Activity
{
  private Button mButton01;  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    mButton01 = (Button)findViewById(R.id.myButton1);    
    /*����Button��OnClickListener�����¼�*/
    mButton01.setOnClickListener(new Button.OnClickListener()
    {     
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub        
        /* ����ImageView */
        ImageView mView01 = new ImageView(example097.this);
        TextView mTextView = new TextView(example097.this);        
        /* ����LinearLayout���� */
        LinearLayout lay = new LinearLayout(example097.this);
        
        /* ����mTextViewȥץȡstringֵ */
        mTextView.setText(R.string.app_url);       
        /* �ж�mTextView������Ϊ�Σ�����ϵͳ������ */
        Linkify.addLinks
        (
          mTextView,Linkify.WEB_URLS|
          Linkify.EMAIL_ADDRESSES|
          Linkify.PHONE_NUMBERS
        );        
        /*��Toast��ʾ*/ 
        Toast toast = Toast.makeText
                      (
                        example097.this,
                        mTextView.getText(),
                        Toast.LENGTH_LONG
                      );       
        /* �Զ���View���� */
        View textView = toast.getView();        
        /* ��ˮƽ��ʽ���� */
        lay.setOrientation(LinearLayout.HORIZONTAL);       
        /* ��ImageView Widget��ָ����ʾ��ͼƬ */
        mView01.setImageResource(R.drawable.icon);        
        /* ��Layout����Ӹմ�����View */
        lay.addView(mView01);
        /* ��Toast����ʾ���� */
        lay.addView(textView);
        
        /* ��Toast��setView��������Layout */
        toast.setView(lay);
        /* ��ʾToast��ʾ*/
        toast.show(); 
      }      
    }); 
  }
}
