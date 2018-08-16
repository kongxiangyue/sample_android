package irdc.example198;

import irdc.example198.R;
import android.app.Activity; 
import android.app.AlertDialog; 
import android.content.DialogInterface; 
import android.content.Intent; 
import android.os.Bundle; 
/*��������util.DisplayMetrics����ȡ����Ļ�ֱ���*/
import android.util.DisplayMetrics; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.widget.AbsoluteLayout; 
import android.widget.EditText; 
import android.widget.TextView; 

public class example198 extends Activity 
{ 
  /*��������*/
  private TextView mTextView01; 
  private LayoutInflater mInflater01; 
  private View mView01; 
  private EditText mEditText01,mEditText02; 
  private String TAG = "HIPPO_DEBUG"; 
  /* �����ֵļ�� */ 
  private int intShiftPadding = 14; 
   
  /** Called when the activity is first created. */ 
  @Override 
  public void onCreate(Bundle savedInstanceState) 
  { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.main); 
     
    /* ����DisplayMetrics����ȡ����Ļ�ֱ��� */ 
    DisplayMetrics dm = new DisplayMetrics();  
    getWindowManager().getDefaultDisplay().getMetrics(dm); 
     
    mTextView01 = (TextView)findViewById(R.id.myTextView1); 
     
    /* ������Label������Ļ���Ϸ� */
    mTextView01.setLayoutParams 
    ( 
      new AbsoluteLayout.LayoutParams(intShiftPadding*mTextView01.getText().toString().length(),18,(dm.widthPixels-(intShiftPadding*mTextView01.getText().toString().length()))-10,0) 
    ); 
     
    /* �����û����TextView���ֵ��¼����� -��¼*/ 
    mTextView01.setOnClickListener(new TextView.OnClickListener() 
    { 
      @Override 
      public void onClick(View v) 
      { 
        // TODO Auto-generated method stub 
         
        /* ��ʾ��¼�Ի��� */  
        showLoginForm(); 
      } 
    }); 
  } 
   
  /* �Զ����¼�Ի����� */ 
  private void showLoginForm() 
  { 
    try 
    {  /* ��LayoutInflaterȡ����Activity��context */ 
      mInflater01 = LayoutInflater.from(example198.this); 
      /* ���ô�����View��Ҫʹ�õ�Layout Resource */ 
      mView01 = mInflater01.inflate(R.layout.login, null); 
       
      /* �˺�EditText */ 
      mEditText01=(EditText)mView01.findViewById(R.id.myEditText1);
       
      /* ����EditText */ 
      mEditText02=(EditText)mView01.findViewById(R.id.myEditText2);
      
      /*����AlertDialog������ȡ���û��˺�����*/  
      new AlertDialog.Builder(this) 
      .setView(mView01) 
      .setPositiveButton("OK", 
      new DialogInterface.OnClickListener() 
      { 
        /*����onClick()������ȡ��Token�¼�����ɵ�¼�¼�*/
        public void onClick(DialogInterface dialog, int whichButton) 
        { 
          if(processGoogleLogin(mEditText01.getText().toString(), mEditText02.getText().toString())) 
          { 
            Intent i = new Intent(); 
            /*��¼�����ע������(EX09_01_02.java)*/
            i.setClass(example198.this, example198_01_02.class); 
            startActivity(i); 
            finish(); 
          } 
        } 
      }).show(); 
    } 
    catch(Exception e) 
    { 
      e.printStackTrace(); 
    } 
  } 
  /*����GoogleAuthSub��ȡ��Google�˺ŵ�Authentication Token*/
  private boolean processGoogleLogin(String strUID, String strUPW) 
  { 
    try 
    { 
      /*�����Զ����GoogtleAuthSub����*/
      GoogleAuthSub gas = new GoogleAuthSub(strUID, strUPW); 
      /*ȡ��Google Token*/
      String strAuth =  gas.getAuthSubToken(); 
      /*��ȡ�ص�Google Tokenд��log��*/
      Log.i(TAG, strAuth); 
       
    } 
    catch (Exception e) 
    { 
      e.printStackTrace(); 
    } 
    return true; 
  } 
} 
