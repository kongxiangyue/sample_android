package mmm.example116;


import java.io.IOException;
import java.io.InputStream;

import mmm.example116.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class example116 extends Activity
{
  protected static InputStream is;
  private ImageAdapter mImageAdapter01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
      
    /*����ͼ��*/
    Integer[] myImageIds = 
    {
      R.drawable.google,
      R.drawable.helloking,
      R.drawable.chamberlain,
      R.drawable.king,
      R.drawable.with,
    };
    
    mImageAdapter01 = new ImageAdapter(example116.this, myImageIds);
    
    /*����ͼΪGallery����ʾ��ʽ*/
    Gallery g = (Gallery) findViewById(R.id.mygallery);
    g.setAdapter(mImageAdapter01);
    
    g.setOnItemClickListener(new Gallery.OnItemClickListener()
    {
      @Override
      public void onItemClick
      (AdapterView<?> parent, View v, final int position, long id)
      {
        // TODO Auto-generated method stub 
        new AlertDialog.Builder(example116.this)
        .setTitle(R.string.app_about)
        /*���õ������ڵ�ͼʽ*/
        .setIcon(mImageAdapter01.myImageIds[position]) 
        /*���õ������ڵ���Ϣ*/
        .setMessage(R.string.app_about_msg)
        /*ȷ�ϴ���*/
        .setPositiveButton(R.string.str_ok,
        new DialogInterface.OnClickListener()
        {
          public void onClick(
          DialogInterface dialoginterface, int i)
          {           
            Resources resources = getBaseContext().getResources();
            is = resources.openRawResource
            (mImageAdapter01.myImageIds[position]);
            try
            {
              /*��������*/
              setWallpaper(is);
              /*��Toast����ʾ�����Ѹ���*/
              Toast.makeText
              (
                example116.this,
                getString(R.string.my_gallery_text_pre),
                Toast.LENGTH_SHORT
              ).show();
            }
            catch (Exception e)
            {            
              e.printStackTrace();
            };
          }
        }).setNegativeButton
        (R.string.str_no, new DialogInterface.OnClickListener()
        {
          /*�����������ڵķ����¼�*/
          public void onClick(DialogInterface dialoginterface, int i)   
          {
            /*��Toast����ʾ������ȡ��*/
            Toast.makeText
            (
              example116.this,
              getString(R.string.my_gallery_text_no),
              Toast.LENGTH_SHORT
            ).show();  
          }
        }).show(); 
      }
    });
  }
  
  public class ImageAdapter extends BaseAdapter
  {
    int mGalleryItemBackground;
    private Context mContext;
    private Integer[] myImageIds;
    
    public ImageAdapter(Context c, Integer[] aid)
    {
      mContext = c;
      myImageIds = aid;
      TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
      mGalleryItemBackground = a.getResourceId
      (
        R.styleable.Gallery_android_galleryItemBackground, 0
      );
      a.recycle();
    }
    
    @Override
    public int getCount()
    {
      // TODO Auto-generated method stub
      return myImageIds.length;
    }
    
    @Override
    public Object getItem(int  position)
    {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public long getItemId(int  position)
    {
      // TODO Auto-generated method stub
      return position;
    }

    @Override
    public View getView
    (int position, View convertView, ViewGroup parent)
    {
      // TODO Auto-generated method stub
      
      /*����ImageView����*/
      ImageView i = new ImageView(mContext);
      /*����ͼƬ��imageView����*/
      i.setImageResource(myImageIds[position]);
      /*��������ͼƬ�Ŀ���*/
      i.setScaleType(ImageView.ScaleType.FIT_XY);
      /*��������Layout�Ŀ���*/
      i.setLayoutParams(new Gallery.LayoutParams(138, 108));
      /*����Gallery����ͼ*/
      i.setBackgroundResource(mGalleryItemBackground);
      /*����imageView����*/
      return i;
    }
  }
  
  @Override
  public void setWallpaper(InputStream data) throws IOException
  {
    // TODO Auto-generated method stub
    super.setWallpaper(data);
  }
}