package com.howfun.android.antguide.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.howfun.android.antguide.GamePref;
import com.howfun.android.antguide.R;
import com.howfun.android.antguide.game.AntMap;
import com.howfun.android.antguide.game.CanvasManager;
import com.howfun.android.antguide.game.GameStatus;
import com.howfun.android.antguide.game.UpdateThread;
import com.howfun.android.antguide.utils.Utils;
import com.howfun.android.hf2d.Pos;

public class AntView extends SurfaceView implements SurfaceHolder.Callback {

   private static final String TAG = "AntView";

   private float touchDownX = 10;

   private float touchDownY = 10;

   private float touchUpX = 200;

   private float touchUpY = 300;

   private boolean mShowBlockLine;

   UpdateThread updateThread;

   private CanvasManager mCanvasManager;

   private Context mContext;

   private GameStatus mGameStatus;

   private AntMap mMap;

   private int mLevel;

   public AntView(Context context) {

      super(context);

      mContext = context;

      getHolder().addCallback(this);

      mCanvasManager = new CanvasManager(mContext);

   }

   public AntView(Context context, AttributeSet attrs) {
      super(context, attrs);

      mContext = context;

      getHolder().addCallback(this);

      mCanvasManager = new CanvasManager(mContext);

      mMap = new AntMap();
   }

   public void init(Handler handler, int level) {
      if (mCanvasManager == null) {
         mCanvasManager = new CanvasManager(mContext);
      }
      mCanvasManager.setHandler(handler);
      mCanvasManager.setMap(mMap);
      mLevel = level;
      mCanvasManager.setMapLevel(level);
   }

   public void setHandler(Handler handler) {
      if (mCanvasManager != null) {
         mCanvasManager.setHandler(handler);
      }
   }

   public void setDownPos(float x, float y) {
      touchDownX = x;
      touchDownY = y;
   }

   public void setUpPos(float x, float y) {
      touchUpX = x;
      touchUpY = y;
   }

   public void showBlockLine() {
      mShowBlockLine = true;

   }

   @Override
   public void onDraw(Canvas canvas) {
      if (mCanvasManager == null) {
         return;
      }
      if (mShowBlockLine) {

         mCanvasManager.setNewLine(new Pos(touchDownX, touchDownY), new Pos(
               touchUpX, touchUpY));
         mShowBlockLine = false;
      }

      mCanvasManager.draw(canvas);

   }

   public void setWhichAntAnim(int which) {
      if (mCanvasManager != null) {
         mCanvasManager.setWhichAntAnim(which);
      }
   }

   /**
    * starts a new game
    */
   public void playGame() {
      if (mCanvasManager == null) {
         Utils.log(TAG, "canvasManager is null");
         // mCanvasManager = new CanvasManager(mContext);
         return;
      }
      mCanvasManager.initAllSprite();
   }

   /**
    * continue the game if it is paused
    */
   public void resumeGame() {
      updateThread.startUpdate();
   }

   /**
    * pause the game,u can continue the game by func continueGame() if pause btn
    * clicked or activity onpaused
    */
   public void pauseGame() {
      updateThread.stopUpdate();
   }

   /**
    * when ant gets home or lost,this func will execute
    */
   public void stopGame() {
      // TODO
      updateThread.stopUpdate();
   }

   public void surfaceCreated(SurfaceHolder holder) {

      Utils.log(TAG, "surfaceCreated()....");

      playGame();

      updateThread = new UpdateThread(AntView.this);

      updateThread.setRunning(true);

      updateThread.start();

      if (mGameStatus != null) {
         if (mGameStatus.getStatus() == GameStatus.GAME_PAUSED) {
            pauseGame();
            if (mCanvasManager != null) {
               mCanvasManager.restoreGame(mGameStatus);
            }
            try {
               updateThread.sleep(500); // let game init in a short time.
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }

   }

   public void surfaceChanged(SurfaceHolder holder, int format, int width,
         int border) {
      Utils.log(TAG, "surfaceChanged()....");

   }

   public void surfaceDestroyed(SurfaceHolder holder) {

      Utils.log(TAG, "surfaceDestroyed()....");

      updateThread.setRunning(false);

      // TODO: save running data if press HOME, and enter into pause mode

      if (mCanvasManager != null) {
         mCanvasManager.clear();
         mCanvasManager = null;
      }

      boolean retry = true;

      while (retry) {

         try {

            updateThread.join();

            retry = false;

         } catch (InterruptedException e) {

         }

      }

   }

   public void setRestoredState(GameStatus gameStatus) {
      this.mGameStatus = gameStatus;
   }

   public void getGameStatus(GameStatus gameStatus) {
      if (mCanvasManager != null) {
         if (gameStatus == null) {
            gameStatus = new GameStatus();
         }
         mCanvasManager.getGameStatus(gameStatus);
      }
      mGameStatus = gameStatus;
   }

   public void resetGame() {

      if (mCanvasManager != null) {
         mCanvasManager.reset();
         mCanvasManager.setMapLevel(mLevel);
      }

      touchDownX = 1000;
      touchDownY = 1000;
      touchUpX = 1001;
      touchUpY = 1001;

      mShowBlockLine = true;

      if (updateThread != null) {
         updateThread.setRunning(true);
         updateThread.startUpdate();
      } else {
         updateThread = new UpdateThread(AntView.this);
         updateThread.setRunning(true);
         updateThread.start();
      }
   }

   public void goNextLv() {

      int passedLevel = GamePref.getInstance(mContext).getLevelPref();
      if (mLevel > passedLevel) {
         Utils.log(TAG, "set new passed level = " + mLevel);
         GamePref.getInstance(mContext).setLevelPref(mLevel);
      }
      
      if (mLevel == mMap.getMapCount() - 1) {
         int speed = GamePref.getInstance(mContext).getSpeedRef();
         speed ++;
         GamePref.getInstance(mContext).SetSpeedPref(speed);
         Toast.makeText(mContext, R.string.new_round, Toast.LENGTH_LONG).show();
      }
     
      Utils.log(TAG, "current level = " + mLevel);

      mLevel = ++mLevel % mMap.getMapCount();

      Utils.log(TAG, "next level = " + mLevel);

      if (mCanvasManager != null) {
//         mCanvasManager.setMapLevel(mLevel);
      }
   }

}