package irdc.example105;

import irdc.example105.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class example105 extends Activity
{
  private Button mButton01;
  private ListView mListView01;
  private ArrayAdapter<String> aryAdapter1;
  private ArrayList<String> arylistTask;
  
  /* ���Ա����ȡ�����Task�������� */
  private int intGetTastCounter=30;
  
  /* ���ԱActivityManager���� */
  private ActivityManager mActivityManager;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mButton01 = (Button)findViewById(R.id.myButton1);
    mListView01 = (ListView)findViewById(R.id.myListView1);
    
    /* �����ťȡ�����ں�̨���еĹ������� */
    mButton01.setOnClickListener(new Button.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        // TODO Auto-generated method stub
        try
        {
          /* ActivityManager������ϵͳȡ��ACTIVITY_SERVICE */
          mActivityManager = (ActivityManager)
          example105.this.getSystemService(ACTIVITY_SERVICE);
          
          arylistTask = new ArrayList<String>();
          
          /* ��getRunningTasks����ȡ�����������еĳ���TaskInfo */
          List<ActivityManager.RunningTaskInfo> mRunningTasks = 
          mActivityManager.getRunningTasks(intGetTastCounter);
          
          int i = 1;
          /* ��ѭ����baseActivity��ʽȡ�ù���������ID */ 
          for (ActivityManager.RunningTaskInfo amTask : mRunningTasks)
          {
            /* baseActivity.getClassNameȡ�����й������� */
            arylistTask.add("" + (i++) + ": "+ 
            amTask.baseActivity.getClassName()+
            "(ID=" + amTask.id +")");
          }
          aryAdapter1 = new ArrayAdapter<String>
          (example105.this, R.layout.list_item, arylistTask);
          
          if(aryAdapter1.getCount()==0)
          {
            /* ��û���κ����еĹ���������ʾ��Ϣ */
            mMakeTextToast
            (
              getResources().getText
              (R.string.str_err_no_running_task).toString(),
              true
            );
          }
          else
          {
            /* ���ֺ�̨���еĹ���������ListView Widget���г��� */
            mListView01.setAdapter(aryAdapter1);
          }
        }
        catch(SecurityException e)
        {
          /* ����GET_TASKSȨ��ʱ(SecurityException�쳣)��ʾ��Ϣ */
          mMakeTextToast
          (
            getResources().getText
            (R.string.str_err_permission).toString(),
            true
          );
        }
      }
    });
    
    /* ��User�����й���ѡ��ʱ���¼����� */
    mListView01.setOnItemSelectedListener
    (new ListView.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected
      (AdapterView<?> parent, View v, int id, long arg3)
      {
        // TODO Auto-generated method stub
        /* ���ڽ����й����������ţ�����idȡ������Ԫ������ */
        mMakeTextToast(arylistTask.get(id).toString(),false);
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg0)
      {
        // TODO Auto-generated method stub
        
      }
    });
    
    /* ��User�����й����ϵ��ʱ���¼����� */
    mListView01.setOnItemClickListener
    (new ListView.OnItemClickListener()
    {
      @Override
      public void onItemClick
      (AdapterView<?> parent, View v, int id,  long arg3)
      {
        // TODO Auto-generated method stub
        /* ���ڽ����й����������ţ�������idȡ������Ԫ������ */
        mMakeTextToast(arylistTask.get(id).toString(), false);
      }
    });
  }
  
  public void mMakeTextToast(String str, boolean isLong)
  {
    if(isLong==true)
    {
      Toast.makeText(example105.this, str, Toast.LENGTH_LONG).show();
    }
    else
    {
      Toast.makeText(example105.this, str, Toast.LENGTH_SHORT).show();
    }
  }
}
