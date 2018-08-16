package com.xiangqi;

import java.util.ArrayList;//引入包 
import java.util.List;

public class GuiZe {
	boolean isRedGo = false;//是否红方走棋
	public boolean canMove(int[][] qizi, int fromY, int fromX, int toY, int toX){
		int i = 0;
		int j = 0;
		int qiID;//起始位置的棋子类型
		int muID;//目标位置
		if(toX<0){//左边出界
			return false;
		}
		if(toX>8){//右边出界
			return false;
		}
		if(toY<0){//上边出界
			return false;
		}
		if(toY>9){//下边出界
			return false;
		}
		if(fromX==toX && fromY==toY){//目标位置与起始位置相同，
			return false;
		}
		qiID = qizi[fromY][fromX];//起始棋子
		muID = qizi[toY][toX];
		if(isSameSide(qiID,muID)){//一伙的
			return false;
		}
		switch(qiID){
			case 1://黑帅
				if(toY>2||toX<3||toX>5){//出了九宫格
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//只能走一步
					return false;
				}
				break;
			case 5://黑士
				if(toY>2||toX<3||toX>5){//出了九宫格
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//走斜线
					return false;
				}
				break;
			case 6://黑象
				if(toY>4){//不过河
					return false;
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//相走田
					return false;
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;
				}
				break;
			case 7://黑兵
				if(toY < fromY){//不回头
					return false;
				}
				if(fromY<5 && fromY == toY){//过河前只能直走
					return false;
				}
				if(toY - fromY + Math.abs(toX-fromX) > 1){//只走一步直线 
					return false;
				}
				break;
			case 8://红将
				if(toY<7||toX>5||toX<3){//出了九宫格
					return false;
				}
				if((Math.abs(fromY-toY)+Math.abs(toX-fromX))>1){//只走一步
					return false;
				}
				break;
			case 2://黑车
			case 9://红车
				if(fromY != toY && fromX != toX){//只走直线
					return false;
				}
				if(fromY == toY){//走横线
					if(fromX < toX){//向右走
						for(i = fromX + 1; i < toX; i++){//循环
							if(qizi[fromY][i] != 0){
								return false;//返回false
							}
						}
					}
					else{//向左走
						for(i = toX + 1; i < fromX; i++){//循环
							if(qizi[fromY][i] != 0){
								return false;//返回false
							}
						}
					}
				}
				else{//走的是竖线
					if(fromY < toY){//向右走
						for(j = fromY + 1; j < toY; j++){
							if(qizi[j][fromX] != 0)
							return false;//返回false							
						}
					}
					else{//想左走
						for(j= toY + 1; j < fromY; j++){
							if(qizi[j][fromX] != 0)
							return false;//返回false							
						}
					}
				}
				break;
			case 10://红马 
			case 3://黑马
				if(!((Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)==2)
						|| (Math.abs(toX-fromX)==2 && Math.abs(toY-fromY)==1))){
					return false;//不是日字时
				}
				if(toX-fromX==2){//向右
					i=fromX+1;//移动
					j=fromY;
				}
				else if(fromX-toX==2){//向左
					i=fromX-1;
					j=fromY;
				}
				else if(toY-fromY==2){//向下
					i=fromX;
					j=fromY+1;
				}
				else if(fromY-toY==2){//向上
					i=fromX;
					j=fromY-1;
				}
				if(qizi[j][i] != 0)
					return false;//绊马腿
				break;
			case 11://红砲
			case 4://黑炮
				if(fromY!=toY && fromX!=toX){//走直线
					return false;
				}
				if(qizi[toY][toX] == 0){
					if(fromY == toY){//横线
						if(fromX < toX){//想右走
							for(i = fromX + 1; i < toX; i++){
								if(qizi[fromY][i] != 0){
									return false;
								}
							}
						}
						else{//向走走
							for(i = toX + 1; i < fromX; i++){
								if(qizi[fromY][i]!=0){
									return false;
								}
							}
						}
					}
					else{//竖线
						if(fromY < toY){//向下走
							for(j = fromY + 1; j < toY; j++){
								if(qizi[j][fromX] != 0){
									return false;//返回false
								}
							}
						}
						else{//向上走
							for(j = toY + 1; j < fromY; j++){
								if(qizi[j][fromX] != 0){
									return false;//返回false
								}
							}
						}
					}
				}
				else{//吃子
					int count=0;
					if(fromY == toY){//走的横线
						if(fromX < toX){//向右
							for(i=fromX+1;i<toX;i++){
								if(qizi[fromY][i]!=0){
									count++;
								}
							}
							if(count != 1){
								return false;//返回false
							}
						}
						else{//向左走
							for(i=toX+1;i<fromX;i++){
								if(qizi[fromY][i] != 0){
									count++;
								}
							}
							if(count!=1){
								return false;//返回false
							}
						}
					}
					else{//走竖线
						if(fromY<toY){//向下走
							for(j=fromY+1;j<toY;j++){
								if(qizi[j][fromX]!=0){
									count++;
								}
							}	
							if(count!=1){
								return false;
							}
						}
						else{//向上走
							for(j=toY+1;j<fromY;j++){
								if(qizi[j][fromX] != 0){
									count++;//返回false
								}
							}
							if(count!=1){
								return false;//返回false
							}
						}
					}
				}
				break;
			case 12://红仕
				if(toY<7||toX>5||toX<3){//出了九宫格
					return false;
				}
				if(Math.abs(fromY-toY) != 1 || Math.abs(toX-fromX) != 1){//走斜线
					return false;
				}
				break;
			case 13://红相
				if(toY<5){//不能过河
					return false;//返回false
				}
				if(Math.abs(fromX-toX) != 2 || Math.abs(fromY-toY) != 2){//相走“田”字
					return false;//返回false
				}
				if(qizi[(fromY+toY)/2][(fromX+toX)/2] != 0){
					return false;//相眼处有棋子
				}
				break;
			case 14://红卒
				if(toY > fromY){//不能回头
					return false;
				}
				if(fromY > 4 && fromY == toY){
					return false;//不让走
				}
				if(fromY - toY + Math.abs(toX - fromX) > 1){//只能走一步，并且是直线 
					return false;//返回false不让走
				}
				break;
			default:
				return false;
		}
		return true;
	}

	public Move searchAGoodMove(int[][] qizi){//查询一个好的走法
		List<Move> ret = allPossibleMoves(qizi);//产生所有走法
		try {
			Thread.sleep(4000);//睡眠四秒钟，以便调试
		} catch (InterruptedException e) {//捕获异常
			e.printStackTrace();//打印堆栈信息
		}
		return ret.get((int)(Math.random()*ret.size()));
	}
	public List<Move> allPossibleMoves(int qizi[][]){//产生所有可能的走法
		 List<Move> ret = new ArrayList<Move>();//用来装所有可能的走法 
		 for (int x = 0; x < 10; x++){
			 for (int y = 0; y < 9; y++){//循环所有的棋牌位置
				 int chessman = qizi[x][y];
				 if (chessman != 0){//当次位置不为空时，即有棋子时
					 if(chessman > 7){//是红方，即是玩家棋子时跳过
						 continue;
					 }
					 switch (chessman){
					 	case 1://黑帅
					 		if(canMove(qizi, x, y, x, y+1)){//向下走一格
	                    		ret.add(new Move(chessman, x, y, x, y+1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x, y-1)){//向上走一格
	                    		ret.add(new Move(chessman, x, y, x, y-1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+1, y)){//向左走一格
	                    		ret.add(new Move(chessman, x, y, x+1, y, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x-1, y)){//向右走一格
	                    		ret.add(new Move(chessman, x, y, x-1, y, 0));
	                    	}
	                    	break;
						case 5://黑士
						case 12://红仕
							if(canMove(qizi, x, y, x-1, y+1)){//左下走
								ret.add(new Move(chessman, x, y, x-1, y+1, 1));
							}
							if(canMove(qizi, x, y, x-1, y-1)){//左上走
								ret.add(new Move(chessman, x, y, x-1, y-1, 1));
							}
							if(canMove(qizi, x, y, x+1, y+1)){//右下走
								ret.add(new Move(chessman, x, y, x+1, y+1, 1));
							}
							if(canMove(qizi, x, y, x+1, y-1)){//右上走
								ret.add(new Move(chessman, x, y, x+1, y-1, 1));
							}
							break;
	         			case 6://黑象
	         			case 13://红相
							if(canMove(qizi, x, y, x-2, y+2)){//左上走
								ret.add(new Move(chessman, x, y, x-2, y+2, 1));
							}
							if(canMove(qizi, x, y, x-2, y-2)){//左下走
								ret.add(new Move(chessman, x, y, x-2, y-2, 1));
							}
							if(canMove(qizi, x, y, x+2, y+2)){//右下走
								ret.add(new Move(chessman, x, y, x+2, y+2, 1));
							}
							if(canMove(qizi, x, y, x+2, y-2)){//右上走
								ret.add(new Move(chessman, x, y, x+2, y-2, 1));
							}
	         				break;
	         			case 7://黑兵
         					if(canMove(qizi, x, y, x, y+1)){//直走
         						ret.add(new Move(chessman, x, y, x, y+1, 2));
         					}
         					if(y >= 5){//过河了 
                                if (canMove(qizi, x, y, x - 1, y)) {//过河后向左走
                                    ret.add(new Move(chessman, x, y, x - 1, y, 2));
                                }
                                if (canMove(qizi, x, y, x + 1, y)) {//过河走向右走
                                    ret.add(new Move(chessman, x, y, x + 1, y, 2));
                                }
         					}
         					break;
	         			case 14://红兵
         					if(canMove(qizi, x, y, x, y-1)){//向前走
         						ret.add(new Move(chessman, x, y, x, y-1, 2));
         					}
         					if(y <=4 ){//过河了
                                if (canMove(qizi, x, y, x - 1, y)) {//过河后向左走
                                    ret.add(new Move(chessman, x, y, x - 1, y, 2));
                                }
                                if (canMove(qizi, x, y, x + 1, y)) {//过河走向右走
                                    ret.add(new Move(chessman, x, y, x + 1, y, 2));
                                }
         					}
	         				break;
	         			case 8://红将
	                    	if(canMove(qizi, x, y, x, y+1)){//向下走一格
	                    		ret.add(new Move(chessman, x, y, x, y+1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x, y-1)){//向上走一格
	                    		ret.add(new Move(chessman, x, y, x, y-1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+1, y)){//向右走一格
	                    		ret.add(new Move(chessman, x, y, x+1, y, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x-1, y)){//向左走一格
	                    		ret.add(new Move(chessman, x, y, x-1, y, 0));
	                    	}
	                    	break;
	         			case 2://黑车
	         			case 9://红车
	         				for(int i=y+1; i<10; i++){//向下走
	         					if(canMove(qizi, x, y, x, i)){ //可以走时
	         						ret.add(new Move(chessman, x, y, x, i, 0));
	         					}else{//不可以走时直接 break
	         						break;
	         					}
	         				}
	         				for(int i=y-1; i>-1; i++){//向上走
	         					if(canMove(qizi, x, y, x, i)){//可以走时
	         						ret.add(new Move(chessman, x, y, x, i, 0));
	         					}else{//不可以走时
	         						break;
	         					}
	         				}
	         				for(int j=x-1; j>-1; j++){//向走走
	         					if(canMove(qizi, x, y, j, y)){//可以走时 
	         						ret.add(new Move(chessman, x, y, j, y, 0));
	         					}else{//不可以走时
	         						break;
	         					}
	         				}
	         				for(int j=x+1; j<9; j++){//向右走
	         					if(canMove(qizi, x, y, j, y)){//可以走时
	         						ret.add(new Move(chessman, x, y, j, y, 0));
	         					}else{//不可以走时
	         						break;
	         					}
	         				}
	         				break;
	         			case 10://红马 
	         			case 3://黑马
	                    	if(canMove(qizi, x, y, x-1, y-2)){//向上左走“日”字
	                    		ret.add(new Move(chessman, x, y, x-1, y-2, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x-1, y+2)){//向下走“日”字
	                    		ret.add(new Move(chessman, x, y, x-1, y+2, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+1, y-2)){//向上右走“日”字
	                    		ret.add(new Move(chessman, x, y, x+1, y-2, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+1, y+2)){//向下右走“日”字
	                    		ret.add(new Move(chessman, x, y, x+1, y+2, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x-2, y-1)){//向上右走“日”字
	                    		ret.add(new Move(chessman, x, y, x-2, y-1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x-2, y+1)){//向下右走“日”字
	                    		ret.add(new Move(chessman, x, y, x-2, y+1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+2, y-1)){//向上右走“日”字
	                    		ret.add(new Move(chessman, x, y, x+2, y-1, 0));
	                    	}
	                    	if(canMove(qizi, x, y, x+2, y+1)){//向下右走“日”字
	                    		ret.add(new Move(chessman, x, y, x+2, y+1, 0));
	                    	}
	         				break;
	         			case 11://红砲
	         			case 4://黑炮
	         				for(int i=y+1; i<10; i++){//向下走时
	         					if(canMove(qizi, x, y, x, i)){//当可以走时
	         						ret.add(new Move(chessman, x, y, x, i, 0));
	         					}
	         				}
	         				for(int i=y-1; i>-1; i--){//向上走时
	         					if(canMove(qizi, x, y, x, i)){//当可以走时
	         						ret.add(new Move(chessman, x, y, x, i, 0));
	         					}
	         				}
	         				for(int j=x-1; j>-1; j--){//向左走时
	         					if(canMove(qizi, x, y, j, y)){//当可以走时
	         						ret.add(new Move(chessman, x, y, j, y, 0));
	         					}
	         				}
	         				for(int j=x+1; j<9; j++){//向右走时
	         					if(canMove(qizi, x, y, j, y)){//当可以走时
	         						ret.add(new Move(chessman, x, y, j, y, 0));
	         					}
	         				}
	         				break;
					}
				 }
			 }
		 }
		 return ret.isEmpty() ? null : ret;//当ret中没有走法时，返回空，有时返回ret
	}
	public boolean isSameSide(int qiID, int muID){//判断两个子是否为同一阵营
		if(muID == 0){// 当目标地位空地时
			return false;
		}
		if(qiID>7&&muID>7){//当都为红色棋子时
			return true;
		}
		else if(qiID<=7&&muID<=7){//都为黑色棋子时
			return true;
		}
		else{//其他情况
			return false;
		}
	}
}