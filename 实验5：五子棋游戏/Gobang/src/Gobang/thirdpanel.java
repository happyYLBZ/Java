package Gobang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class thirdpanel extends JPanel{
	//图片
	ImageIcon back=new ImageIcon("src/imagines/b.jpg");
	ImageIcon house=new ImageIcon("src/imagines/fang.png");
	//标签
	JLabel label[]=new JLabel[5];
	//按钮
	JButton btn[]=new JButton[4];
	JRadioButton mbtn=new JRadioButton("背景音乐",true);
	JButton  rebtn =new JButton("返回");
    //单例模式
	private static thirdpanel instance=new thirdpanel();
	private thirdpanel() {
		//标签
		for(int i=0;i<=4;i++) {
			if(i==0) {
			label[i]=new JLabel("游戏大厅");
			label[i].setForeground(new Color(0, 0, 0));
			label[i].setFont(new Font("楷体", Font.BOLD, 37));
			}
			else {
			label[i]=new JLabel("房间"+i);
			label[i].setForeground(new Color(110, 0, 60));
			label[i].setFont(new Font("楷体", Font.BOLD, 20));
			}
			label[i].setOpaque(false);
			add(label[i]);
		}
		//按钮
		for(int i=0;i<=3;i++) {
			btn[i]=new JButton();
			btn[i].setContentAreaFilled(false);
    		btn[i].setFocusable(false);
    		add(btn[i]);
		}
		btn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NetHelper.getInstance().beginlisten(8090);
			    Play.getInstance().card.next(Play.getInstance().getContentPane());
			}
		});
		btn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NetHelper.getInstance().beginlisten(8091);
			    Play.getInstance().card.next(Play.getInstance().getContentPane());
			}
		});
		btn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NetHelper.getInstance().beginlisten(8092);
			    Play.getInstance().card.next(Play.getInstance().getContentPane());
			}
		});
		btn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NetHelper.getInstance().beginlisten(8093);
			    Play.getInstance().card.next(Play.getInstance().getContentPane());
			}
		});
		mbtn.setFont(new Font("楷体", Font.BOLD, 15));
		mbtn.setForeground(new Color(200, 0, 0));
		mbtn.setContentAreaFilled(false);//透明
		mbtn.setFocusable(false);//去焦点
		add(mbtn);
		 mbtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(mbtn.isSelected()) {
						 Control.getInstance().player.start(true);
						 secondpanel.getInstance().btn3.setSelected(true);
						 fifthpanel.getInstance().mbtn.setSelected(true);
						 toolpanel.getInstance().mbtn.setSelected(true);
					}
					else {
						Control.getInstance().player.stop();
						secondpanel.getInstance().btn3.setSelected(false);
						fifthpanel.getInstance().mbtn.setSelected(false);
						toolpanel.getInstance().mbtn.setSelected(false);
					}
					
				}
			});
		 rebtn.setFont(new Font("楷体", Font.BOLD, 20));
		 rebtn.setForeground(new Color(200, 0, 0));
		 rebtn.setContentAreaFilled(false);//透明
		 rebtn.setFocusable(false);//去焦点
		 add(rebtn);
		 rebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Play.getInstance().card.previous(Play.getInstance().getContentPane());
			}
		});
		 
	};
	public static thirdpanel getInstance() {
		return instance;
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(back.getImage(), 0,0, getWidth(), getHeight(), this);
		g.drawImage(house.getImage(), (int)(getWidth()*0.15), (int)(getHeight()*0.1), 180,180, this);
		g.drawImage(house.getImage(), (int)(getWidth()*0.15), (int)(getHeight()*0.5), 180,180, this);
		g.drawImage(house.getImage(), (int)(getWidth()*0.6), (int)(getHeight()*0.1), 180,180, this);
		g.drawImage(house.getImage(), (int)(getWidth()*0.6), (int)(getHeight()*0.5), 180,180, this);
		label[0].setBounds((int)(getWidth()*0.40), (int)(getHeight()*0.05), 160, 45);
		label[1].setBounds((int)(getWidth()*0.21), (int)(getHeight()*0.45), 100,25);
		label[2].setBounds((int)(getWidth()*0.67), (int)(getHeight()*0.45), 100,25);
		label[3].setBounds((int)(getWidth()*0.21), (int)(getHeight()*0.85), 100,25);
		label[4].setBounds((int)(getWidth()*0.67), (int)(getHeight()*0.85), 100,25);
		btn[0].setBounds((int)(getWidth()*0.15), (int)(getHeight()*0.1), 180,180);
		btn[1].setBounds((int)(getWidth()*0.15), (int)(getHeight()*0.5), 180,180);
		btn[2].setBounds((int)(getWidth()*0.6), (int)(getHeight()*0.1), 180,180);
		btn[3].setBounds((int)(getWidth()*0.6), (int)(getHeight()*0.5), 180,180);
		mbtn.setBounds((int)(getWidth()*0.05), (int)(getHeight()*0.05), 127, 47);
		rebtn.setBounds((int)(getWidth()*0.41), (int)(getHeight()*0.85), 127, 47);
	}
}
