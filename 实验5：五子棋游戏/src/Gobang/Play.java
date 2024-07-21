package Gobang;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Play extends JFrame{
	  //布局
	  CardLayout card=new CardLayout();
	  //图标
	  private ImageIcon icon=new ImageIcon("src/imagines/icon.jpg");
	  //框架长和宽
	  private final int WIDTH=900;
	  private final int HEIGHT=600;
	  //单例模式
	  private static Play instance;
      private Play() {
    	  setTitle("五子棋");
    	  setIconImage(icon.getImage());
    	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  getContentPane().setLayout(card);
    	  getContentPane().add("1",firstpanel.getInstance());
    	  getContentPane().add("2",secondpanel.getInstance());
    	  getContentPane().add("3",thirdpanel.getInstance());
    	  getContentPane().add("4",fourthpanel.getInstance());
    	  getContentPane().add("5", fifthpanel.getInstance());
          setSize(WIDTH, HEIGHT);
          setVisible(true); 
      }
      public static Play getInstance() {
    	  if(instance==null) {
    		  instance=new Play();
    	  }
    	  return instance;
      }
      public static void main(String[] args) {
		  Play.getInstance();
	}
}
