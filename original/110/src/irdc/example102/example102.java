package irdc.example102;

/* import���class */
import irdc.example102.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class example102 extends ListActivity
{
  /* �������� 
     items�������ʾ������
     paths������ļ�·��
     rootPath����ʼĿ¼
  */
  private List<String> items=null;
  private List<String> paths=null;
  private String rootPath="/";
  private TextView mPath;
  private View myView;
  private EditText myEditText;

  @Override
  protected void onCreate(Bundle icicle)
  {
    super.onCreate(icicle);
    /* ����main.xml Layout */

    setContentView(R.layout.main);
    /* ��ʼ��mPath��������ʾĿǰ·�� */
    mPath=(TextView)findViewById(R.id.mPath);
    getFileDir(rootPath);
  }
  
  /* ȡ���ļ��ܹ��ķ��� */
  private void getFileDir(String filePath)
  {
    /* ����Ŀǰ����·�� */
    mPath.setText(filePath);
    items=new ArrayList<String>();
    paths=new ArrayList<String>();
    
    File f=new File(filePath);  
    File[] files=f.listFiles();

    if(!filePath.equals(rootPath))
    {
      /* ��һ������Ϊ[�ص���Ŀ¼] */
      items.add("b1");
      paths.add(rootPath);
      /* �ڶ�������Ϊ[���ϲ�] */
      items.add("b2");
      paths.add(f.getParent());
    }
    /* �������ļ����ArrayList�� */
    for(int i=0;i<files.length;i++)
    {
      File file=files[i];
      items.add(file.getName());
      paths.add(file.getPath());
    }

    /* ʹ���Զ����MyAdapter�������ݴ���ListActivity */
    setListAdapter(new MyAdapter(this,items,paths));
  }

  /* ����ListItem�����ʱҪ���Ķ��� */
  @Override
  protected void onListItemClick(ListView l,View v,int position,
                                 long id)
  {
    File file = new File(paths.get(position));

    if(file.isDirectory())
    {
      /* ������ļ��о�������getFileDir() */
      getFileDir(paths.get(position));
    }
    else
    {
      /* ������ļ�����fileHandle() */
      fileHandle(file);
    }
  }

  /* �����ļ��ķ��� */
  private void fileHandle(final File file){
    /* ����ļ�ʱ��OnClickListener */
    OnClickListener listener1=new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface dialog,int which)
      {
        if(which==0)
        {
          /* ѡ���itemΪ���ļ� */
          openFile(file);
        }
        else if(which==1)
        {
          /* ѡ���itemΪ���ĵ��� */
          LayoutInflater factory=LayoutInflater.from(example102.this);
          /* ��ʼ��myChoiceView��ʹ��rename_alert_dialogΪlayout */
          myView=factory.inflate(R.layout.alert_dialog,null);
          myEditText=(EditText)myView.findViewById(R.id.mEdit);
          /* ��ԭʼ�ļ����ȷ���EditText�� */
          myEditText.setText(file.getName());

          /* newһ�������ļ�����Dialog��ȷ����ť��listener */
          OnClickListener listener2=
          new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
              /* ȡ���޸ĺ���ļ�·�� */
              String modName=myEditText.getText().toString();
              final String pFile=file.getParentFile().getPath()+"/";
              final String newPath=pFile+modName;

              /* �жϵ����Ƿ��Ѵ��� */
              if(new File(newPath).exists())
              {
                /* �ų��޸ĵ���ʱû�޸�ֱ���ͳ���״�� */
                if(!modName.equals(file.getName()))
                {
                  /* ����Alert���浵���ظ�����ȷ���Ƿ��޸� */
                  new AlertDialog.Builder(example102.this)
                      .setTitle("ע��!")
                      .setMessage("�Ѿ����ڣ��Ƿ�Ҫ����?")
                      .setPositiveButton("ȷ��",
                       new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                          /* �����ظ���Ȼ�޸ĻḲ�ĵ��Ѵ��ڵ��ļ� */
                          file.renameTo(new File(newPath));
                          /* ���²����ļ��б��ListView */
                          getFileDir(pFile);
                        }
                      })
                      .setNegativeButton("ȡ��",
                       new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                        }
                      }).show();
                }
              }
              else
              {
                /* ���������ڣ�ֱ�����޸Ķ��� */
                file.renameTo(new File(newPath));
                /* ���²����ļ��б��ListView */
                getFileDir(pFile);
              }
            }
          };

          /* create���ĵ���ʱ������Dialog */
          AlertDialog renameDialog=
            new AlertDialog.Builder(example102.this).create();
          renameDialog.setView(myView);

          /* ���ø��ĵ������ȷ�Ϻ��Listener */
          renameDialog.setButton("ȷ��",listener2);
          renameDialog.setButton2("ȡ��",
          new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface dialog, int which)
            {
            }
          });
          renameDialog.show();
        }
        else
        {
          /* ѡ���itemΪɾ���ļ� */
          new AlertDialog.Builder(example102.this).setTitle("��ע��!")
              .setMessage("ȷ��ɾ��?")
              .setPositiveButton("ȷ��",
               new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog,
                                    int which)
                {          
                  /* ɾ���ļ� */
                  file.delete();
                  getFileDir(file.getParent());
                }
              })
              .setNegativeButton("ȡ��",
               new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface dialog,
                                    int which)
                {
                }
              }).show();
        }
      }
    };

    /* ѡ��һ���ļ�ʱ������Ҫ��δ����ļ���ListDialog */
    String[] menu={"���ļ�","����","ɾ��"};
    new AlertDialog.Builder(example102.this)
        .setTitle("׼����ɶ?")
        .setItems(menu,listener1)
        .setPositiveButton("ȡ��",
         new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int which)
          {
          }
        })
        .show();
  }

  /* ���ֻ��ϴ��ļ��ķ��� */
  private void openFile(File f) 
  {
    Intent intent = new Intent();
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setAction(android.content.Intent.ACTION_VIEW);

    /* ����getMIMEType()��ȡ��MimeType */
    String type = getMIMEType(f);
    /* ����intent��file��MimeType */
    intent.setDataAndType(Uri.fromFile(f),type);
    startActivity(intent); 
  }

  /* �ж��ļ�MimeType�ķ��� */
  private String getMIMEType(File f) 
  { 
    String type="";
    String fName=f.getName();
    /* ȡ����չ�� */
    String end=fName.substring(fName.lastIndexOf(".")+1,
                               fName.length()).toLowerCase(); 

    /* �������������;���MimeType */
    if(end.equals("m4a")||end.equals("mp3")||end.equals("mid")
       ||end.equals("xmf")||end.equals("ogg")||end.equals("wav"))
    {
      type = "audio"; 
    }
    else if(end.equals("3gp")||end.equals("mp4"))
    {
      type = "video";
    }
    else if(end.equals("jpg")||end.equals("gif")||end.equals("png")
             ||end.equals("jpeg")||end.equals("bmp"))
    {
      type = "image";
    }
    else
    {
      /* ����޷�ֱ�Ӵ򿪣�����������б���û�ѡ�� */
      type="*";
    }
    type += "/*"; 
    return type; 
  }
}
