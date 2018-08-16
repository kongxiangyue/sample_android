package com.ex218;

import java.io.IOException;

import com.ex218.R;

import android.content.Context;
import android.media.MediaPlayer;

public class Player
{
	public MediaPlayer	playerMusic	= null;
	private Context		mContext	= null;

	public Player(Context context)
	{
		mContext = context;
	}

	/* �������� */
	public void PlayMusic()
	{
		/* װ����Դ�е����� */
		playerMusic = MediaPlayer.create(mContext, R.raw.start);
		
		/* �����Ƿ�ѭ�� */
		playerMusic.setLooping(true);
		try
		{
			playerMusic.prepare();
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		playerMusic.start();
	}

	/* ֹͣ���ͷ����� */
	public void FreeMusic()
	{
		if (playerMusic != null)
		{
			playerMusic.stop();
			playerMusic.release();
		}
	}
}