package irdc.texiao089;

import irdc.texiao089.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/*����Layout��ʹ��Gallery widget������������Щģ��*/
import android.content.Context;
import android.widget.Gallery;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class texiao089 extends Activity
{
  private TextView mTextView01;
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    
    mTextView01 = (TextView) findViewById(R.id.myTextView01);
    mTextView01.setText(getString(R.string.str_txt1));
    mTextView01.setTextColor(Color.BLUE);
    
    ((Gallery) findViewById(R.id.myGallery1))
               .setAdapter(new ImageAdapter(this)); 
  }
  
  public class ImageAdapter extends BaseAdapter
  { 
    /* ���Ա myContextΪContext���� */ 
    private Context myContext; 
    
    /*ʹ��android.R.drawable���ͼƬ��Ϊͼ����Դ������Ϊ��������*/
    private int[] myImageIds =
                  { 
                    android.R.drawable.btn_minus,
                    android.R.drawable.btn_radio,
                    android.R.drawable.ic_lock_idle_low_battery,
                    android.R.drawable.ic_menu_camera
                  };
    /* ������ֻ��һ����������Ҫ�洢��Context */ 
    public ImageAdapter(Context c) { this.myContext = c; } 

    /* ���������Ѷ����ͼƬ������ */ 
    public int getCount() { return this.myImageIds.length; } 

    /* ����getItem������ȡ��Ŀǰ������ͼ�������ID */ 
    public Object getItem(int position) { return position; } 
    public long getItemId(int position) { return position; }
    
    /* ȡ��Ŀǰ����ʾ��ͼ��View����������IDֵʹ֮��ȡ����� */ 
    public View getView(int position, View convertView, 
                        ViewGroup parent)
    { 
      /* ����һ��ImageView���� */
      ImageView i = new ImageView(this.myContext);
      
      i.setImageResource(this.myImageIds[position]);
      i.setScaleType(ImageView.ScaleType.FIT_XY); 
      
      /* �������ImageView����Ŀ��ߣ���λΪdip */ 
      i.setLayoutParams(new Gallery.LayoutParams(120, 120)); 
      return i; 
    } 

    /*���ݾ��������λ���� ����getScale����views�Ĵ�С(0.0f to 1.0f)*/
    public float getScale(boolean focused, int offset)
    { 
      /* Formula: 1 / (2 ^ offset) */ 
      return Math.max(0,1.0f/(float)Math.pow(2,Math.abs(offset)));
     } 
   }
}