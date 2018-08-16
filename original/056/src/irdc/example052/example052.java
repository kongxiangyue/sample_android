package irdc.example052;

import irdc.example052.R;
import android.app.Activity;
import android.os.Bundle;

/* ��������ʹ�õ���class */
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

public class example052 extends Activity 
{
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    /*ͨ��findViewByIdȡ��*/
    @SuppressWarnings("deprecation")
	Gallery g = (Gallery) findViewById(R.id.mygallery);
    /* ���һImageAdapter�����ø�Gallery���� */
    g.setAdapter(new ImageAdapter(this));
    
    /* ����һ��itemclickListener��Toast�����ͼƬ��λ�� */
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
  
  /* ��дBaseAdapter�Զ���һImageAdapter class */
  public class ImageAdapter extends BaseAdapter 
  {
    /*��������*/
    int mGalleryItemBackground;
    private Context mContext;
    
    /*ImageAdapter�Ĺ�����*/
    public ImageAdapter(Context c) 
    {
      mContext = c;
      
      /* ʹ����res/values/attrs.xml�е�<declare-styleable>����
      * ��Gallery����.*/
      TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
      
      /*ȡ��Gallery���Ե�Index id*/
      mGalleryItemBackground = a.getResourceId
      (R.styleable.Gallery_android_galleryItemBackground, 0);
      
      /*�ö����styleable�����ܹ�����ʹ��*/ 
      a.recycle();
    }
    
    /* ���ǵķ���getCount,����ͼƬ��Ŀ */
    public int getCount() 
    {
      return myImageIds.length;
    }
         
    /* ���ǵķ���getItemId,����ͼ�������id */

    public Object getItem(int position) 
    {
      return position;
    }
    public long getItemId(int position) 
    {
      return position;
    }
    
    /* ���ǵķ���getView,����һView���� */
    public View getView
    (int position, View convertView, ViewGroup parent)
    {
      /*����ImageView����*/
      ImageView i = new ImageView(mContext);
      /*����ͼƬ��imageView����*/
      i.setImageResource(myImageIds[position]);
      /*��������ͼƬ�Ŀ��*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*��������Layout�Ŀ��*/
      i.setLayoutParams(new Gallery.LayoutParams(136, 88));
      /*����Gallery����ͼ*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*����imageView����*/
      return i;
    }
    
    /*����һInteger array��ȡ��Ԥ����Drawable��ͼƬid*/
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
