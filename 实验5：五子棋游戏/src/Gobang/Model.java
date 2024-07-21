package Gobang;

import java.util.ArrayList;

public class Model {
     //单例模式
	 private static Model instance;
	 private Model() {};
	 public static Model getInstance() {
		 if(instance==null) {
			 instance=new Model();
		 }
		 return instance;
	 }
	 //行数组
	 ArrayList<Integer>rowlist=new ArrayList<Integer>(Model_Wide*Model_Wide);
	 private int failrow=-1;
	 //列数组
	 ArrayList<Integer>collist=new ArrayList<Integer>(Model_Wide*Model_Wide);
	 private int failcol=-1;
	 //棋子模型
	 public static final int White=1;
	 public static final int Space=0;
	 public static final int Black=-1;
	 //棋盘模型
	 public static final int Model_Wide=15;
	 private int[][] checkerboard=new int[Model_Wide][Model_Wide];
	 //最新的下棋位置
	 private int lastRow;
	 private int lastCol;
	 //
	 public boolean isnull() {
		 if(failrow==-1)return true;
		 return false;
	 }
	 //悔棋
	 public void regret() {
		 checkerboard[rowlist.get(failrow)][collist.get(failcol)]=0;
		 rowlist.remove(failrow);
		 collist.remove(failcol);
		 failrow--;
		 failcol--;
	 }
	 //清空
	 public void clear() {
		 for(int i=0;i<Model_Wide;i++) {
			 for(int j=0;j<Model_Wide;j++) {
				 checkerboard[i][j]=0;
			 }
		 }
		 rowlist.clear();
		 collist.clear();
		 failrow=-1;
		 failcol=-1;	
	 }
	 //下棋函数
	 public boolean putChess(int row,int col,int color) {
		 if(row<Model_Wide&&row>=0&&col<Model_Wide&&col>=0) {
			 if(checkerboard[row][col]==Space) {
				 checkerboard[row][col]=color;
				 lastRow=row;
				 lastCol=col;
				 rowlist.add(lastRow);
				 failrow++;
				 collist.add(lastCol);
				 failcol++;
				 ChessPanel.getInstance().repaint();
				 return true;
			 }
		 }
		 return false;
	 }
	 //读棋子
	 public int getChess(int row,int col) {
		 if(checkerboard[row][col]==White) {
			 return White;
		 }
		 if(checkerboard[row][col]==Black) {
			 return Black;
		 }
		 return Space;
	 }
	 //判断输赢
	 public int Whowin() {
		 if(instance.left(lastRow, lastCol)==Black||
				   instance.right(lastRow, lastCol)==Black||
				   instance.left_right(lastRow, lastCol)==Black||
				   instance.up_down(lastRow, lastCol)==Black) {
					    return Black;
				}
				if(instance.left(lastRow, lastCol)==White||
						   instance.right(lastRow, lastCol)==White||
						   instance.left_right(lastRow, lastCol)==White||
						   instance.up_down(lastRow, lastCol)==White) {
							    return White;
						}
				return Space;
	 } 
	 //判断横向是否五个
	 public int left_right(int row,int col) {
	    	int count=0;//计时器
	    	//白棋
	    	if(checkerboard[row][col]==White) {
	    		//从左到右
	    	    for(int i=col;i>=0&&i<Model_Wide;i++) {
	    		        if(checkerboard[row][i]==White) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return White;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从右到左
	    	    for(int i=col;i>=0&&i<Model_Wide;i--) {
			        if(checkerboard[row][i]==White) {
			        	count++;
			        	if(count==5) {
			        		return White;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	count=0;
	    	//黑棋
	    	if(checkerboard[row][col]==Black) {
	    		//从左到右
	    	    for(int i=col;i>=0&&i<Model_Wide;i++) {
	    		        if(checkerboard[row][i]==Black) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return Black;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从右到左
	    	    for(int i=col;i>=0&&i<Model_Wide;i--) {
			        if(checkerboard[row][i]==Black) {
			        	count++;
			        	if(count==5) {
			        		return Black;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	return Space;
	    }
	 //竖着是否五个
	 public int up_down(int row,int col) {
	    	int count=0;//计时器
	    	//白棋
	    	if(checkerboard[row][col]==White) {
	    		//从上到下
	    	    for(int i=row;i>=0&&i<Model_Wide;i++) {
	    		        if(checkerboard[i][col]==White) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return White;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从下到上
	    	    for(int i=row;i>=0&&i<Model_Wide;i--) {
			        if(checkerboard[i][col]==White) {
			        	count++;
			        	if(count==5) {
			        		return White;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	count=0;
	    	//黑棋
	    	if(checkerboard[row][col]==Black) {
	    		//从上到下
	    	    for(int i=row;i>=0&&i<Model_Wide;i++) {
	    		        if(checkerboard[i][col]==Black) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return Black;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从下到上
	    	    for(int i=row;i>=0&&i<Model_Wide;i--) {
			        if(checkerboard[i][col]==Black) {
			        	count++;
			        	if(count==5) {
			        		return Black;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	return Space;
	    }
	 //右斜
	 public int right(int row,int col) {
	    	int count=0;//计时器
	    	//白棋
	    	if(checkerboard[row][col]==White) {
	    		//从左上到右下
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i++,j++) {
	    		        if(checkerboard[i][j]==White) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return White;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从右下到左上
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i--,j--) {
			        if(checkerboard[i][j]==White) {
			        	count++;
			        	if(count==5) {
			        		return White;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	count=0;
	    	//黑棋
	    	if(checkerboard[row][col]==Black) {
	    		//从左上到右下
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i++,j++) {
	    		        if(checkerboard[i][j]==Black) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return Black;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //左上边
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i--,j--) {
			        if(checkerboard[i][j]==Black) {
			        	count++;
			        	if(count==5) {
			        		return Black;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	return Space;
	    }
	 //左斜
	 public int left(int row,int col) {
	    	int count=0;//计时器
	    	//白棋
	    	if(checkerboard[row][col]==White) {
	    		//从右上到左下
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i--,j++) {
	    		        if(checkerboard[i][j]==White) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return White;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从左下到右上
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i++,j--) {
			        if(checkerboard[i][j]==White) {
			        	count++;
			        	if(count==5) {
			        		return White;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	count=0;
	    	//黑棋
	    	if(checkerboard[row][col]==Black) {
	    		//从右上到左下
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i--,j++) {
	    		        if(checkerboard[i][j]==Black) {
	    		        	count++;
	    		        	if(count==5) {
	    		        		return Black;
	    		        	}
	    		        }
	    		        else {
	    		        	break;
	    		        }
	    	    }
	    	    count=0;
	    	    //从左下到右上
	    	    for(int i=row,j=col;i>=0&&i<Model_Wide&&j>=0&&j<Model_Wide;i++,j--) {
			        if(checkerboard[i][j]==Black) {
			        	count++;
			        	if(count==5) {
			        		return Black;
			        	}
			        }
			        else {
			        	break;
			        }
		        }
	    	}
	    	return Space;
	    }
}
