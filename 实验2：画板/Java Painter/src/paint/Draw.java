package paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Draw {
	//用来获取坐标
	public static  int x1,x2,y1,y2;
	//用来获取该画什么文字
	public static String characters;
	//用来获取文字大小
	public static int size;
	//判断要不要加新的shape
	public static boolean T=true;
	//shape数组用来重画界面
	public static List <Shape> shape=new ArrayList<Shape>();
	//shape添加元素
	     void add(int a,int b,int c,int d,String s,Color color,String m,int n) {
	    	 for(paint.Shape h: shape) {
	    		 if(h.x1==a&&h.y1==c&&h.type.equals(s)) {
	    			T=false;
	    			h.x2=b;
	    			h.y2=d;
	    			break;
	    	     }
	    		 else {
	    			 T=true;
	    		 }
	    	 }
	    	 if(T) {
	    		 shape.add(new Shape(a,b,c,d,s,color,m,n));
	    	 }
	    	
	    }
	   //重载函数
	     void add(int a,int b,int c,int d,String s,Color color,String m,int n,boolean t) {
	    	 for(paint.Shape h: shape) {
	    		 if(h.x1==a&&h.y1==c&&h.type.equals(s)) {
	    			T=false;
	    			h.x2=b;
	    			h.y2=d;
	    			break;
	    	     }
	    		 else {
	    			 T=true;
	    		 }
	    	 }
	    	 if(T) {
	    		 shape.add(new Shape(a,b,c,d,s,color,m,n,t));
	    	 }
	    	
	    }
	 //单例模式
	 private static Draw Instance;
     private Draw(){}
     public  static Draw getInstance() {
    	 if(Instance==null) {
    		 Instance=new Draw();
    	 }
    	 return Instance;
     }
     public static Color shapecolor=Color.black;
	 //换背景颜色函数
	public static void registeColorChanged(Color color) {
		// TODO Auto-generated method stub
		PaintPanel.getInstance().setBackground(color);
	}
	//画图形
	     //鼠标监听器
	     //一共五个，来防止界面上同时画多个图形
	class drawlistener1 implements MouseListener,MouseMotionListener{
        Graphics g;
        String command;
        public drawlistener1(Graphics g,String command) {
        	this.g=g;
        	this.command=command;
        	g.setColor(Color.black);
        }
		@Override
		//来实现动态画图形
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//来防止界面上同时画多个图形
		if(!PaintToolBar.getInstance().btn[0].isSelected())return;
			x2=e.getX();
			y2=e.getY();
			if(UpPanel.getInstance().checkbox.isSelected()) {
				add(x1,x2,y1,y2,command,shapecolor,"",0,true);
			}
			else {
				add(x1,x2,y1,y2,command,shapecolor,"",0);
			}
			PaintPanel.getInstance().repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1=e.getX();
		    y1=e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	
	class drawlistener2 implements MouseListener,MouseMotionListener{
        Graphics g;
        String command;
        public drawlistener2(Graphics g,String command) {
        	this.g=g;
        	this.command=command;
        	g.setColor(Color.black);
        }
		@Override
		//来实现动态画图形
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//来防止界面上同时画多个图形
			if(!PaintToolBar.getInstance().btn[1].isSelected())return;
			x2=e.getX();
			y2=e.getY();
			if(UpPanel.getInstance().checkbox.isSelected()) {
				add(x1,x2,y1,y2,command,shapecolor,"",0,true);
			}
			else {
				add(x1,x2,y1,y2,command,shapecolor,"",0);
			}
			PaintPanel.getInstance().repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1=e.getX();
		    y1=e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	
	
	class drawlistener3 implements MouseListener,MouseMotionListener{
        Graphics g;
        String command;
        public drawlistener3(Graphics g,String command) {
        	this.g=g;
        	this.command=command;
        	g.setColor(Color.black);
        }
		@Override
		//来实现动态画图形
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//防止同时画多个图形
			if(!PaintToolBar.getInstance().btn[2].isSelected())return;
			x2=e.getX();
			y2=e.getY();
			if(UpPanel.getInstance().checkbox.isSelected()) {
				add(x1,x2,y1,y2,command,shapecolor,"",0,true);
			}
			else {
				add(x1,x2,y1,y2,command,shapecolor,"",0);
			}
			PaintPanel.getInstance().repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1=e.getX();
		    y1=e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	
	class drawlistener4 implements MouseListener,MouseMotionListener{
        Graphics g;
        String command;
        public drawlistener4(Graphics g,String command) {
        	this.g=g;
        	this.command=command;
        	g.setColor(Color.black);
        }
		@Override
		//来实现动态画图形
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//防止同时画多个图形
			if(!PaintToolBar.getInstance().btn[3].isSelected())return;
			x2=e.getX();
			y2=e.getY();
			add(x1,x2,y1,y2,command,shapecolor,"",0);
			PaintPanel.getInstance().repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1=e.getX();
		    y1=e.getY();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	class drawlistener5 implements MouseListener,MouseMotionListener{
        Graphics g;
        String command;
        public drawlistener5(Graphics g,String command) {
        	this.g=g;
        	this.command=command;
        	g.setColor(Color.black);
        }
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			x2=e.getX();
			y2=e.getY();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//防止同时画多个图形
			if(!PaintToolBar.getInstance().btn[4].isSelected())return;
			x1=e.getX();
		    y1=e.getY();
		    if(command.equals("文字")) {
		    	//获取文本域文字
		    	characters=UpPanel.getInstance().textfield.getText();
		    	add(x1,0,y1,0,command,shapecolor,characters,size);
		    }
		    PaintPanel.getInstance().repaint();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	public void pressBtn1(String command) {
		 PaintPanel.getInstance().addMouseListener(new drawlistener1( PaintPanel.getInstance().getGraphics(),command));
		 PaintPanel.getInstance().addMouseMotionListener(new drawlistener1( PaintPanel.getInstance().getGraphics(),command));
	}
	public void pressBtn2(String command) {
		 PaintPanel.getInstance().addMouseListener(new drawlistener2( PaintPanel.getInstance().getGraphics(),command));
		 PaintPanel.getInstance().addMouseMotionListener(new drawlistener2( PaintPanel.getInstance().getGraphics(),command));
	}
	public void pressBtn3(String command) {
		 PaintPanel.getInstance().addMouseListener(new drawlistener3( PaintPanel.getInstance().getGraphics(),command));
		 PaintPanel.getInstance().addMouseMotionListener(new drawlistener3( PaintPanel.getInstance().getGraphics(),command));
	}
	public void pressBtn4(String command) {
		 PaintPanel.getInstance().addMouseListener(new drawlistener4( PaintPanel.getInstance().getGraphics(),command));
		 PaintPanel.getInstance().addMouseMotionListener(new drawlistener4( PaintPanel.getInstance().getGraphics(),command));
	}
	public void pressBtn5(String command) {
		 PaintPanel.getInstance().addMouseListener(new drawlistener5( PaintPanel.getInstance().getGraphics(),command));
		 PaintPanel.getInstance().addMouseMotionListener(new drawlistener5( PaintPanel.getInstance().getGraphics(),command));
	}
}
