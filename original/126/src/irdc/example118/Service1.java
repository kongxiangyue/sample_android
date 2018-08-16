package irdc.example118;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/* 別 */
public class Service1 extends Service
{
  /* 建 */
  private Handler objHandler = new Handler();
  
  /* 為 */
  private int intCounter=0;
  
  /*  */
  private Runnable mTasks = new Runnable() 
  {
    /*  */
    public void run()
    {
      /* 別 */
      intCounter++;
      
      /* 以 */
      Log.i("HIPPO", "Counter:"+Integer.toString(intCounter));
      
      /*  */
      objHandler.postDelayed(mTasks, 1000);
    } 
  };
  
  @Override
  public void onStart(Intent intent, int startId)
  {
    // TODO Auto-generated method stub
    
    /*  */
    objHandler.postDelayed(mTasks, 1000);
    super.onStart(intent, startId);
  }

  @Override
  public void onCreate()
  {
    // TODO Auto-generated method stub
    super.onCreate();
  }
  
  @Override
  public IBinder onBind(Intent intent)
  {
    // TODO Auto-generated method stub
    
    /*  */
    return null;
  }

  @Override
  public void onDestroy()
  {
    // TODO Auto-generated method stub
    
    /*  */
    objHandler.removeCallbacks(mTasks);
    super.onDestroy();
  }  
}
