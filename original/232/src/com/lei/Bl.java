package com.lei;

import com.lei.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 地雷的块，继承自Button
 */
public class Bl extends Button {
	private boolean isCovered; // 块是否覆盖
	private boolean isMined; // 下个块
	private boolean isFlagged; // 是否将该块标记为一个潜在的地雷
	private boolean isQuestionMarked; // 是否是块的问题标记
	private boolean isClickable; // 是否可以单击
	private int numberOfMinesInSurrounding; // 在附近的地雷数量块

	public Bl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Bl(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public Bl(Context context, AttributeSet attrs, int defStyle)
	   	{
	   		super(context, attrs, defStyle);
	   	}


	/**
	 * 设置默认参数
	 */
	public void setDefaults() {
		isCovered = true;
		isMined = false;
		isFlagged = false;
		isQuestionMarked = false;
		isClickable = true;
		numberOfMinesInSurrounding = 0;
		
		this.setBackgroundResource(R.drawable.square_blue);
		setBoldFont();
	}

	public void setNumberOfSurroundingMines(int number) {
		this.setBackgroundResource(R.drawable.square_grey);
		updateNumber(number);
	}

	public void setMineIcon(boolean enabled) {
		this.setText("M");
		if (!enabled) {
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}

		else {
			this.setTextColor(Color.BLACK);
		}
	}

	public void setFlagIcon(boolean enabled) {
		this.setText("F");
		if (!enabled) {
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}

		else {
			this.setTextColor(Color.BLACK);
		}
	}

	public void setQuestionMarkIcon(boolean enabled) {
		this.setText("?");
		if (!enabled) {
			this.setBackgroundResource(R.drawable.square_grey);
			this.setTextColor(Color.RED);
		}

		else {
			this.setTextColor(Color.BLACK);
		}
	}

	public void setBlockAsDisabled(boolean enabled) {
		if (!enabled) {
			this.setBackgroundResource(R.drawable.square_grey);
		}

		else {
			this.setTextColor(R.drawable.square_blue);
		}
	}

	public void clearAllIcons() {
		this.setText("");
	}

	private void setBoldFont() {
		this.setTypeface(null, Typeface.BOLD);
	}

	public void OpenBlock() {
		if (!isCovered) {
			return;
		}

		setBlockAsDisabled(false);
		isCovered = false;

		if (hasMine()) {
			setMineIcon(false);
		}

		else {
			setNumberOfSurroundingMines(numberOfMinesInSurrounding);
		}
	}

	public void updateNumber(int text) {
		if (text != 0) {
			this.setText(Integer.toString(text));
			switch (text) {
			case 1:
				this.setTextColor(Color.BLUE);
				break;
			case 2:
				this.setTextColor(Color.rgb(0, 100, 0));
				break;
			case 3:
				this.setTextColor(Color.RED);
				break;
			case 4:
				this.setTextColor(Color.rgb(85, 26, 139));
				break;
			case 5:
				this.setTextColor(Color.rgb(139, 28, 98));
				break;
			case 6:
				this.setTextColor(Color.rgb(238, 173, 14));
				break;
			case 7:
				this.setTextColor(Color.rgb(47, 79, 79));
				break;
			case 8:
				this.setTextColor(Color.rgb(71, 71, 71));
				break;
			case 9:
				this.setTextColor(Color.rgb(205, 205, 0));
				break;

			}
		}
	}

	public void plantMine() {
		isMined = true;
	}

	public void triggerMine() {
		setMineIcon(true);
		this.setTextColor(Color.RED);
	}

	public boolean isCovered() {
		return isCovered;
	}

	public boolean hasMine() {
		return isMined;
	}

	public void setNumberOfMinesInSurrounding(int number) {
		numberOfMinesInSurrounding = number;
	}

	public int getNumberOfMinesInSorrounding() {
		return numberOfMinesInSurrounding;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean flagged) {
		isFlagged = flagged;
	}

	public boolean isQuestionMarked() {
		return isQuestionMarked;
	}

	public void setQuestionMarked(boolean questionMarked) {
		isQuestionMarked = questionMarked;
	}

	public boolean isClickable() {
		return isClickable;
	}

	public void setClickable(boolean clickable) {
		isClickable = clickable;
	}

}
