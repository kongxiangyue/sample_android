/**
 * 
 */
package org.manager;

import org.manager.R;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class ChangeService extends Service
{
	// ���嶨ʱ�����ı�ֽ��Դ
	int[] wallpapers = new int[]{
		R.drawable.shuangta,
		R.drawable.lijiang,
		R.drawable.qiao, 
		R.drawable.shui
	};
	// ����ϵͳ�ı�ֽ�������
	WallpaperManager wManager;
	// ���嵱ǰ����ʾ�ı�ֽ
	int current = 0;
	@SuppressLint("NewApi")
	@Override
	public void onStart(Intent intent, int startId)
	{
		// ����������һ�ţ�ϵͳ��ͷ��ʼ
		if(current >= 4)
			current = 0;
		try
		{
			// �ı��ֽ
			wManager.setResource(wallpapers[current++]);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		super.onStart(intent, startId);
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate()
	{
		super.onCreate();
		// ��ʼ��WallpaperManager
		wManager = WallpaperManager.getInstance(this);
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
