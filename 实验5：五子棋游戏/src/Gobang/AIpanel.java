package Gobang;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AIpanel extends JPanel{
	 //属性
		private final int levelwidth=586;
		private final int levelheight=563;
		private int width;
		private int height;
		private final int coordinate=9;
		private final int big=26;
		private ImageIcon icon=new ImageIcon("src/imagines/chessboard.jpg");
		private ImageIcon whitechess=new ImageIcon("src/imagines/whitechess.png");
		private ImageIcon blackchess=new ImageIcon("src/imagines/blackchess.png");
		private final int xgap=39;
		private final int ygap=37;
	//单例模式
	 private static AIpanel instance=new AIpanel();
	 private AIpanel() {
		    setSize(new Dimension(levelwidth, levelheight));
			//添加改变大小监听器
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					width=getWidth();
					height=getHeight();
					repaint();
				}
			});
			//下棋监听器
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					double x=coordinate*width/levelwidth;
					double y=coordinate*height/levelheight;
					double a=xgap*width/levelwidth;
					double b=ygap*height/levelheight;
				    int col=(int)((e.getX()-x)/a)+1;
				    int row=(int)((e.getY()-y)/b)+1;
			        if (!(AIcontrol.getInstance().getE(row, col) == 0 && col >= 1 && col <= 15 && row >= 1 && row <= 15)) {
			            return;
			        }
			        AIcontrol.getInstance().putChess(row, col);
			        AIpanel.getInstance().repaint();
			        //判断输赢
			        int WinOrLose = AIcontrol.getInstance().WinOrLose(row, col);
			        if (WinOrLose == 1) {
			            JOptionPane.showMessageDialog(null,
			                    "黑的赢了",
			                    "恭喜",
			                    JOptionPane.DEFAULT_OPTION);
			            AIcontrol.getInstance().setcanput(false);
			        } else if (WinOrLose == 2) {
			            JOptionPane.showMessageDialog(null,
			                    "白的赢了",
			                    "恭喜",
			                    JOptionPane.DEFAULT_OPTION);
			            AIcontrol.getInstance().setcanput(false);
			        }
			        //电脑下
			        int[] res = AIcontrol.getInstance().evaluate();
			        AIcontrol.getInstance().putChess(res[0], res[1]);
			        AIpanel.getInstance().repaint();
			        WinOrLose = AIcontrol.getInstance().WinOrLose(res[0], res[1]);
			        if (WinOrLose == 1) {
			            JOptionPane.showMessageDialog(null,
			                    "黑的赢了",
			                    "恭喜",
			                    JOptionPane.DEFAULT_OPTION);
			            AIcontrol.getInstance().setcanput(false);
			        } else if (WinOrLose == 2) {
			            JOptionPane.showMessageDialog(null,
			                    "白的赢了",
			                    "恭喜",
			                    JOptionPane.DEFAULT_OPTION);
			            AIcontrol.getInstance().setcanput(false);
			        }
				}
			});
		 
	 };
	 public static AIpanel getInstance() {
		 return instance;
	 };
	 @Override
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 drawPanel(g);
		 drawChess(g);
	}
		private void drawChess(Graphics g) {
			double x=(double)(coordinate*width)/(double)levelwidth;
			double y=(double)(coordinate*height)/(double)levelheight;
			double a=(double)(xgap*width)/(double)levelwidth;
			double b=(double)(ygap*height)/(double)levelheight;
			double newbig1=(double)(big*width)/(double)levelwidth;
			double newbig2=(double)(big*height)/(double)levelheight;
			for(int i=0;i<Model.Model_Wide;i++) {
				for(int j=0;j<Model.Model_Wide;j++) {
					if(AIcontrol.getInstance().getE(i+1, j+1)==1) {
						g.drawImage(blackchess.getImage(), (int)Math.round(x+a*j), (int)Math.round(y+b*i), (int)Math.round(newbig1),(int)Math.round(newbig2), this);
					}
					if(AIcontrol.getInstance().getE(i+1, j+1)==2) {
						g.drawImage(whitechess.getImage(), (int)Math.round(x+a*j), (int)Math.round(y+b*i), (int)Math.round(newbig1), (int)Math.round(newbig2), this);
					}
				}
			}
		}
		private void drawPanel(Graphics g) {
		    g.drawImage(icon.getImage(),0,0,width,height,this);
		}
		
}