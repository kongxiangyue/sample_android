package irdc.example052;

import irdc.example052.R;
import android.app.Activity;
import android.os.Bundle;

/* 本范例需使用到的class */
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
// 相册 by kxy
public class example052 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    /*通过findViewById取得*/
    @SuppressWarnings("deprecation")
	Gallery g = (Gallery) findViewById(R.id.mygallery);
    /* 添加一ImageAdapter并设置给Gallery对象 */
    g.setAdapter(new ImageAdapter(this));
    
    /* 设置一个itemclickListener并Toast被点击图片的位置 */
    g.setOnItemClickListener(new OnItemClickListener() 
    {
      public void onItemClick
      (AdapterView<?> parent, View v, int position, long id)
      {
        Toast.makeText
        (example052.this, getString(R.string.my_gallery_text_pre)
        + position+ getString(R.string.my_gallery_text_post), 
        Toast.LENGTH_SHORT).show();
      }
    });
  }
  
  /* 改写BaseAdapter自定义一ImageAdapter class */
  public class ImageAdapter extends BaseAdapter 
  {
    /*声明变量*/
    int mGalleryItemBackground;
    private Context mContext;
    
    /*ImageAdapter的构造器*/
    public ImageAdapter(Context c) 
    {
      mContext = c;
      
      /* 使用在res/values/attrs.xml中的<declare-styleable>定义
      * 的Gallery属性.*/
      TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
      
      /*取得Gallery属性的Index id*/
      mGalleryItemBackground = a.getResourceId
      (R.styleable.Gallery_android_galleryItemBackground, 0);
      
      /*让对象的styleable属性能够反复使用*/ 
      a.recycle();
    }
    
    /* 覆盖的方法getCount,返回图片数目 */
    public int getCount() 
    {
      return myImageIds.length;
    }
         
    /* 覆盖的方法getItemId,返回图像的数组id */

    public Object getItem(int position) 
    {
      return position;
    }
    public long getItemId(int position) 
    {
      return position;
    }
    
    /* 覆盖的方法getView,返回一View对象 */
    public View getView
    (int position, View convertView, ViewGroup parent)
    {
      /*产生ImageView对象*/
      ImageView i = new ImageView(mContext);
      /*设置图片给imageView对象*/
      i.setImageResource(myImageIds[position]);
      /*重新设置图片的宽高*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*重新设置Layout的宽高*/
      i.setLayoutParams(new Gallery.LayoutParams(136, 88));
      /*设置Gallery背景图*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*返回imageView对象*/
      return i;
    }
    
    /*建构一Integer array并取得预加载Drawable的图片id*/
    private Integer[] myImageIds = 
    {
      R.drawable.photo1,
      R.drawable.photo2,
      R.drawable.photo3,
      R.drawable.photo4,
      R.drawable.photo5,
      R.drawable.photo6,
    };   
  } 
}
