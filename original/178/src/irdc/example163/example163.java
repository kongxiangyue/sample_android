package irdc.example163;

import irdc.example163.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class example163 extends Activity 
{
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);   
    Gallery g = (Gallery) findViewById(R.id.mygallery);
            
    /*新增几ImageAdapter并设定给Gallery对象*/
    g.setAdapter(new ImageAdapter(this,getSD()));

    /*设定几个itemclickListener事件*/
    g.setOnItemClickListener(new OnItemClickListener() 
    {
      public void onItemClick(AdapterView<?> parent, 
                       View v, int position, long id) 
      { 
        
      }
    });
  }
  
  private List<String> getSD()
  {
    /* 设定目前所?路径 */
    List<String> it=new ArrayList<String>();      
    File f=new File("/sdcard/");  
    File[] files=f.listFiles();
 
    /* 将所有档案叵丈ArrayList中 */
    for(int i=0;i<files.length;i++)
    {
      File file=files[i];
      if(getImageFile(file.getPath()))
        it.add(file.getPath());
    }
    return it;
  }
    
  private boolean getImageFile(String fName)
  {
    boolean re;
    
    /* 取得副檔宅 */
    String end=fName.substring(fName.lastIndexOf(".")+1,
                  fName.length()).toLowerCase(); 
    
    /* 依附檔宅的类型决定MimeType */
    if(end.equals("jpg")||end.equals("gif")||end.equals("png")
            ||end.equals("jpeg")||end.equals("bmp"))
    {
      re=true;
    }
    else
    {
      re=false;
    }
    return re; 
  }

  /*改写BaseAdapter告订几ImageAdapter class*/
  public class ImageAdapter extends BaseAdapter 
  {
    /*宣╳变数*/
    int mGalleryItemBackground;
    private Context mContext;
    private List<String> lis;
    
    /*ImageAdapter的建构巳*/
    public ImageAdapter(Context c,List<String> li) 
    {
      mContext = c;
      lis=li;
      /* 使用?res/values/attrs.xml中的<declare-styleable>定义
      * 的Gallery属性.*/
      TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
      /*取得Gallery属性的Index id*/
      mGalleryItemBackground = a.getResourceId(
          R.styleable.Gallery_android_galleryItemBackground, 0);
      /*让对象的styleable属性能够反复使用*/ 
      a.recycle();
    }
    
    /*几定要覆写的方法getCount,传并图仞数目*/
    public int getCount() 
    {
      return lis.size();
    }
    
    /*几定要覆写的方法getItem,传并position*/
    public Object getItem(int position) 
    {
      return position;
    }
    
    /*几定要覆写的方法getItemId,传并position*/
    public long getItemId(int position) 
    {
      return position;
    }
    
    /*几定要覆写的方法getView,传并几View对象*/
    public View getView(int position, View convertView, 
                          ViewGroup parent) 
    {
      /*产生ImageView对象*/
      ImageView i = new ImageView(mContext);
      /*设定图仞给imageView对象*/
      Bitmap bm = BitmapFactory.decodeFile(lis.
                            get(position).toString());
      i.setImageBitmap(bm);
      /*重新设定图仞的宽高*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*重新设定Layout的宽高*/
      i.setLayoutParams(new Gallery.LayoutParams(136, 88));
      /*设定Gallery背景图*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*传并imageView物件*/
      return i;
    }     
  } 
}

