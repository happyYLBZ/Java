package paint;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintFrame {
	 private static PaintFrame Instance;
     private PaintFrame(){}
     public  static PaintFrame getInstance() {
    	 if(Instance==null) {
    		 Instance=new PaintFrame();
    	 }
    	 return Instance;
     }
     //标题
     JFrame frame=new JFrame("Java Painter");
     //Frame框架设置
     void setFrame() {
    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame.setSize(700, 400);
    	 frame.setVisible(true);
    	 frame.setLayout(new BorderLayout());
    	 frame.getContentPane().add(UpPanel.getInstance().UpBigpanel, BorderLayout.NORTH);
    	 frame.getContentPane().add(PaintToolBar.getInstance().toolbar,BorderLayout.WEST);
    	 frame.getContentPane().add(PaintPanel.getInstance(),BorderLayout.CENTER);
     }
     public static void main(String[]args) {
    	       PaintToolBar.getInstance().settoolbar();
    	       UpPanel.getInstance().setUpPanel();
    	       getInstance().setFrame();
     }
}

