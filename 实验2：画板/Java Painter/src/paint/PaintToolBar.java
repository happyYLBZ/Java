package paint;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;



public class PaintToolBar {
	//单例模式
	 private static PaintToolBar Instance;
     private PaintToolBar(){}
     public  static PaintToolBar getInstance() {
    	 if(Instance==null) {
    		 Instance=new PaintToolBar();
    	 }
    	 return Instance;
     }
   //监听器
   		ActionListener actionListener1 =new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				String command=e.getActionCommand();
   				Draw.getInstance().pressBtn1(command);
   			}
   		};
   		ActionListener actionListener2 =new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				String command=e.getActionCommand();
   				Draw.getInstance().pressBtn2(command);
   			}
   		};
   		ActionListener actionListener3 =new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				String command=e.getActionCommand();
   				Draw.getInstance().pressBtn3(command);
   			}
   		};
   		ActionListener actionListener4 =new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				String command=e.getActionCommand();
   				Draw.getInstance().pressBtn4(command);
   			}
   		};
   		ActionListener actionListener5=new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
   				String command=e.getActionCommand();
   				Draw.getInstance().pressBtn5(command);
   			}
   		};
     //工具栏设置
	 JToolBar toolbar=new JToolBar("工具栏",JToolBar.VERTICAL);//指定垂直方向
	 JToggleButton[] btn=new JToggleButton[5];
	 void settoolbar() {
		 ButtonGroup bp=new ButtonGroup();
		 String shapename[]= {"圆形","椭圆","矩形","直线","文字"};
		 for(int i=0;i<=4;i++) {
			 btn[i]=new JToggleButton(shapename[i]);
			 btn[i].setPreferredSize(new Dimension(50, 50));
			 bp.add(btn[i]);
			 toolbar.add(btn[i]);
		 }
		 toolbar.setFloatable(true);
		 btn[0].addActionListener(actionListener1);
		 btn[1].addActionListener(actionListener2);
		 btn[2].addActionListener(actionListener3);
		 btn[3].addActionListener(actionListener4);
		 btn[4].addActionListener(actionListener5);
	 }
}
