package Gobang;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	 //属性
	private final int levelwidth=586;
	private  final int levelheight=563;
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
	private static ChessPanel instance;
	private ChessPanel() {
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
			    int col=(int)((e.getX()-x)/a);
			    int row=(int)((e.getY()-y)/b);
				Control.getInstance().putChess(row,col);
			}
		});
	};
	public static ChessPanel getInstance() {
		if(instance==null) {
			instance=new ChessPanel();
		}
		return instance;
	}
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
				if(Model.getInstance().getChess(i, j)==Model.White) {
					g.drawImage(whitechess.getImage(), (int)Math.round(x+a*j), (int)Math.round(y+b*i), (int)Math.round(newbig1), (int)Math.round(newbig2), this);
				}
				if(Model.getInstance().getChess(i, j)==Model.Black) {
					g.drawImage(blackchess.getImage(), (int)Math.round(x+a*j), (int)Math.round(y+b*i), (int)Math.round(newbig1),(int)Math.round(newbig2), this);
				}
			}
		}
	}
	private void drawPanel(Graphics g) {
	    g.drawImage(icon.getImage(),0,0,width,height,this);
	}
}
