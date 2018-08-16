package com.xiangqi;

public class Move {
	int ChessID;//什么棋子
	int qiX;//起始坐标
	int qiY;
	int toX;//目标坐标
	int toY;
	int score;//得分
	public Move(int ChessID, int qiX,int qiY,int toX,int toY,int score){//构造器
		this.ChessID = ChessID;//棋子类型
		this.qiX = qiX;//起始坐标
		this.qiY = qiY;
		this.toX = toX;//目标x坐标
		this.toY = toY;//目标y坐标
		this.score = score;
	}
}