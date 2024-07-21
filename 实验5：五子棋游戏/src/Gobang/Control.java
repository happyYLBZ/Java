package Gobang;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Control {
	  //音乐播放器
    Music player=new Music("D:\\微信\\WeChat Files\\wxid_2f6yts2xld9h22\\FileStorage\\File\\2022-12\\华夏民族乐团 - 高山流水 (古筝独奏版).wav");
    Music put=new Music("D:\\浏览器下载\\put.wav");
	//是不是可以下棋
	private boolean canPut=false;
	//是不是对方传来的
	private boolean isOther=false;
	//现在的颜色
	private int yourcolor=Model.Black;
	private int othercolor=-yourcolor;
     //单例模式
	private static Control instance;
	private Control() {};
	public static Control getInstance() {
		if(instance==null) {
			instance=new Control();
		}
		return instance;
	}
	//设置可不可以下棋
	public void setcanPut(boolean t) {
		 canPut=t;
	}
	//设置颜色
	public void setColor(int color) {
		yourcolor=color;
	}
	//设置是不是对方的棋
	public void setisOther(boolean t) {
		isOther=t;
	}
	public void putChess(int row, int col) {
		 if(!canPut) {
			  return;
		  }
		  boolean success;
		  if(isOther) {
			   success=Model.getInstance().putChess(row, col, -yourcolor);
		  }
		  else {
			   success=Model.getInstance().putChess(row, col, yourcolor);
		  }
		  if(success) {
			  put.start(false);
			  if(isOther) {
				  setisOther(false);
				  setcanPut(true);
			  }
			  else {
				  NetHelper.getInstance().sendChess(row,col);
				  setcanPut(false);
			  }
			  if(Model.getInstance().Whowin()==yourcolor) {
			   JOptionPane.showMessageDialog(null, "你赢了！","恭喜！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
			   NetHelper.getInstance().sendtips("你输了");
			   canPut=false;
			   return;
			   }
			  if(Model.getInstance().Whowin()==othercolor) {
				 JOptionPane.showMessageDialog(null, "你输了！","再接再厉！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/ku.jpeg"));
				 NetHelper.getInstance().sendtips("你赢了");
				 canPut=false;   
				 return;
			   }
		  }

	}
	//显式输赢
	public void showtip(String s) {
		if(s.equals("你赢了")){
			setcanPut(false);
			JOptionPane.showMessageDialog(null, "你赢了！","恭喜！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
		}
		if(s.equals("你输了")) {
			 setcanPut(false);
			 JOptionPane.showMessageDialog(null, "你输了！","再接再厉！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/ku.jpeg"));
		}
		if(s.equals("对方认输")) {
			setcanPut(false);
			 JOptionPane.showMessageDialog(null, "对方已认输，你赢了！","恭喜！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
		}
		if(s.equals("对方求和")) {
			int i=JOptionPane.showConfirmDialog(null, "对方求和","求和",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagines/qiuhe.jpeg"));
			if(i==0) {
				 JOptionPane.showMessageDialog(null, "平局","恭喜！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
				 setcanPut(false);
				 NetHelper.getInstance().sendtips("求和成功");
			}
			else {
				 NetHelper.getInstance().sendtips("求和失败");
			}
		}
		if(s.equals("求和成功")) {
				 JOptionPane.showMessageDialog(null, "对方已同意！平局","恭喜！",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
				 setcanPut(false);
		}
		if(s.equals("求和失败")) {
			 JOptionPane.showMessageDialog(null, "对方拒绝，比赛继续！","遗憾",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/ku.jpeg"));
	     }
		if(s.equals("请求悔棋")) {
			int i=JOptionPane.showConfirmDialog(null, "对方请求悔棋","悔棋",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagines/ku.jpeg"));
			if(i==0) {
				Model.getInstance().regret();
				 ChessPanel.getInstance().repaint();
				 setcanPut(false);
				 NetHelper.getInstance().sendtips("请求悔棋成功");
			}
			else {
				 NetHelper.getInstance().sendtips("请求悔棋失败");
			}
	     }
		if(s.equals("请求悔棋成功")) {
			 JOptionPane.showMessageDialog(null, "对方同意，悔棋成功！","成功",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
			 Model.getInstance().regret();
			  ChessPanel.getInstance().repaint();
			  setcanPut(true);
	     }
		if(s.equals("请求悔棋失败")) {
			 JOptionPane.showMessageDialog(null, "对方拒绝，比赛继续！","遗憾",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/ku.jpeg"));
	     }
		if(s.equals("请求重开")) {
			int i=JOptionPane.showConfirmDialog(null, "对方请求重开","重开",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagines/qiuhe.jpeg"));
			if(i==0) {
				Model.getInstance().clear();
				if(yourcolor==Model.White)setcanPut(false);
				else {setcanPut(true);}
				setisOther(false);
				 ChessPanel.getInstance().repaint();
				 NetHelper.getInstance().sendtips("请求重开成功");
			}
			else {
				 NetHelper.getInstance().sendtips("请求重开失败");
			}
	     }
		if(s.equals("请求重开成功")) {
			 JOptionPane.showMessageDialog(null, "对方同意，比赛重开！","高兴",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/face.jpeg"));
			 Model.getInstance().clear();
				if(yourcolor==Model.White)setcanPut(false);
				else {setcanPut(true);}
				setisOther(false);
				 ChessPanel.getInstance().repaint();
	     }
		if(s.equals("请求重开失败")) {
			 JOptionPane.showMessageDialog(null, "对方拒绝，比赛继续！","遗憾",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/imagines/ku.jpeg"));
	     }
	}
	//显示聊天信息
	public void showmessage(String s) {
		 if(NetHelper.getInstance().getisserver()) {
			 new Thread() {
				 public void run() {
					 try {
						 toolpanel.getInstance().kuang[0].setText(s);
						 sleep(3000);
						 toolpanel.getInstance().kuang[0].setText("");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 };
			 }.start();
		 }
		 else {
			 new Thread() {
				 public void run() {
					 try {
						 toolpanel.getInstance().kuang[1].setText(s);
						 sleep(1000);
						 toolpanel.getInstance().kuang[1].setText("");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 };
			 }.start();
		 }
	}
}
