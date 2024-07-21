package Gobang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class secondpanel extends JPanel{
	ImageIcon icon=new ImageIcon("src/imagines/background.jpg");
	JButton btn1=new JButton("单机模式");
	JButton btn2=new JButton("联机模式");
	JRadioButton btn3=new JRadioButton("背景音乐",true);
      //单例模式
	private static secondpanel instance=new secondpanel();
	private secondpanel() {
		setLayout(null);
		btn1.setFont(new Font("楷体", Font.BOLD, 20));
	    btn1.setContentAreaFilled(false);//透明
		btn1.setFocusable(false);//去焦点
		add(btn1);
		btn2.setFont(new Font("楷体", Font.BOLD, 20));
	    btn2.setContentAreaFilled(false);//透明
		btn2.setFocusable(false);//去焦点
		add(btn2);
		btn3.setFont(new Font("楷体", Font.BOLD, 15));
		btn3.setForeground(new Color(200, 0, 0));
	    btn3.setContentAreaFilled(false);//透明
		btn3.setFocusable(false);//去焦点
		add(btn3);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  Play.getInstance().card.next(Play.getInstance().getContentPane());
//			  Control.getInstance().setisInternet(true);
			}
		});
        btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  Play.getInstance().card.show(Play.getInstance().getContentPane(),"5");

			}
		});
        btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btn3.isSelected()) {
					 Control.getInstance().player.start(true);
					 thirdpanel.getInstance().mbtn.setSelected(true);
					 fifthpanel.getInstance().mbtn.setSelected(true);
					 toolpanel.getInstance().mbtn.setSelected(true);
				}
				else {
					Control.getInstance().player.stop();
					thirdpanel.getInstance().mbtn.setSelected(false);
					fifthpanel.getInstance().mbtn.setSelected(false);
					toolpanel.getInstance().mbtn.setSelected(false);
				}
				
			}
		});
	};
	public static secondpanel getInstance() {
		return instance;
	}
	@Override
	protected void paintComponent(Graphics g) {
		 g.drawImage(icon.getImage(),0,0,getWidth(),getHeight(),this);
		 btn1.setBounds((int)(getWidth()*0.41), (int)(getHeight()*0.30), 137, 47);
		 btn2.setBounds((int)(getWidth()*0.41), (int)(getHeight()*0.65), 137, 47);
		 btn3.setBounds((int)(getWidth()*0.05), (int)(getHeight()*0.05), 127, 47);
	}
}
