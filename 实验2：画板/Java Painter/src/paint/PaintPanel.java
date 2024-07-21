package paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	     //单例模式
		 private static PaintPanel Instance;
	     private PaintPanel() {}
	     public  static PaintPanel getInstance() {
	    	 if(Instance==null) {
	    		 Instance=new PaintPanel();
	    	 }
	    	 return Instance;
	     }
	     //重写paintComponent函数
	     public void paintComponent(Graphics g) {
	    	 super.paintComponent(g);
	    	 for(paint.Shape s: Draw.shape) {
	    		 g.setColor(s.c);
	    		 if(s.type.equals("圆形")) {
	    			 if(s.t) {
	    				 //计算下圆的外接正方形边长
	    			 g.fillOval(s.x1,s.y1,(int)(Math.sqrt(2)*Math.abs(s.x1-s.x2)),(int)( Math.sqrt(2)*Math.abs(s.x1-s.x2)));
	    			 }
	    			 else {
	    			g.drawOval(s.x1,s.y1,(int)(Math.sqrt(2)*Math.abs(s.x1-s.x2)),(int)( Math.sqrt(2)*Math.abs(s.x1-s.x2)));
	    			 }
	    		 }
                  if(s.type.equals("矩形")) {
                	  if(s.t) {
                		  g.fillRect(s.x1, s.y1, s.x2-s.x1,s.y2-s.y1);
                	  }
                	  else {
                		  g.drawRect(s.x1, s.y1, s.x2-s.x1,s.y2-s.y1);
                	  }
	    		 }
                  if(s.type.equals("椭圆")) {
                	  if(s.t) {
     	    			 g.fillOval(s.x1,s.y1,s.x2-s.x1,s.y2-s.y1);
     	    			 }
     	    			 else {
     	    			 g.drawOval(s.x1,s.y1,s.x2-s.x1,s.y2-s.y1);
     	    			 }
 	    		 }
                  if(s.type.equals("文字")) {
 	    			g.setFont(new Font("楷体",Font.BOLD,s.size));
 	    			g.drawString(s.text, s.x1, s.y1);
 	    		 }
                  if(s.type.equals("直线")) {
  	    			 g.drawLine(s.x1, s.y1, s.x2, s.y2);
  	    		 }
	    	 }
	     }
		
}
