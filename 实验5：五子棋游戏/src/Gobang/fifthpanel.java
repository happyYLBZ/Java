package Gobang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class fifthpanel extends JPanel{
	 //图片
	ImageIcon icon=new ImageIcon("src/imagines/back.jpg");
	//按钮
	JButton btn[]=new JButton[3];
	JButton color=new JButton("变颜色");
	JRadioButton mbtn=new JRadioButton("背景音乐",true);
	//工具面板
	JPanel panel[]=new JPanel[4];
	//标签
	JLabel label=new JLabel("绝 悟 人 机");
     //单例模式
	private static fifthpanel instance=new fifthpanel();
	private fifthpanel() {
		       //标签
				label.setPreferredSize(new Dimension(250, 50));
				label.setOpaque(false);
				label.setForeground(new Color(70,0,0));
				label.setFont(new Font("楷体", Font.BOLD, 40));
				//按钮
				for(int i=0;i<=2;i++) {
					 if(i==0)btn[i]=new JButton("悔棋");
					 if(i==1)btn[i]=new JButton("返回");
					 if(i==2)btn[i]=new JButton("重玩");
					 btn[i].setFont(new Font("宋体", Font.BOLD, 20));
					 btn[i].setForeground(new Color(10, 200, 10));
					 btn[i].setContentAreaFilled(false);//透明
					 btn[i].setFocusable(false);//去焦点
				}
				mbtn.setFont(new Font("楷体", Font.BOLD, 20));
				mbtn.setForeground(new Color(200, 0, 0));
				mbtn.setContentAreaFilled(false);//透明
				mbtn.setFocusable(false);//去焦点
				color.setFont(new Font("楷体", Font.BOLD, 20));
				color.setForeground(new Color(200, 0, 0));
				color.setContentAreaFilled(false);//透明
				color.setFocusable(false);//去焦点
				//背景音乐监听器
				mbtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(mbtn.isSelected()) {
							 Control.getInstance().player.start(true);
							 secondpanel.getInstance().btn3.setSelected(true);
							 thirdpanel.getInstance().mbtn.setSelected(true);
							 toolpanel.getInstance().mbtn.setSelected(true);
						}
						else {
							Control.getInstance().player.stop();
							secondpanel.getInstance().btn3.setSelected(false);
							 thirdpanel.getInstance().mbtn.setSelected(false);
							 toolpanel.getInstance().mbtn.setSelected(false);
						}
						
					}
				});
				//悔棋监听器
				btn[0].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if( !AIcontrol.getInstance().isnull()) {
							 AIcontrol.getInstance().regret();
						}
					}
				});
				//返回监听器
				btn[1].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int i=JOptionPane.showConfirmDialog(null, "你确定要返回大厅吗","提示",JOptionPane.YES_NO_OPTION);
						if(i==0) {
							int j=JOptionPane.showConfirmDialog(null, "你要保存吗","提示",JOptionPane.YES_NO_OPTION);
							if(j==1)AIcontrol.getInstance().replay();
							Play.getInstance().card.show(Play.getInstance().getContentPane(), "2");
						}
					}
				});
				//重玩监听器
				btn[2].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int i=JOptionPane.showConfirmDialog(null, "你确定要重玩吗","提示",JOptionPane.YES_NO_OPTION);
						if(i==0)AIcontrol.getInstance().replay();
						AIpanel.getInstance().repaint();
					}
				});
				//变换颜色监听器
				color.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AIcontrol.getInstance().black=!AIcontrol.getInstance().black;
					}
				});
		//四个小面板设置
		for(int i=0;i<=3;i++) {
		panel[i]=new JPanel();
		}
		panel[0].setLayout(new FlowLayout());
		panel[0].setOpaque(false);
		panel[0].setPreferredSize(new Dimension(150, 50));
		panel[0].add(label);
		for(int i=1;i<=3;i++) {
			panel[i].setOpaque(false);
			panel[i].setPreferredSize(new Dimension(150,20));
		}
		panel[1].add(color);
		panel[2].add(mbtn);
		panel[3].setLayout(new GridLayout(1,3));
		panel[3].setPreferredSize(new Dimension(10, 50));
		for(int i=0;i<=2;i++) {
			panel[3].add(btn[i]);
		}
		setLayout(new BorderLayout());
		add(AIpanel.getInstance(),BorderLayout.CENTER);
		add(panel[3],BorderLayout.SOUTH);
		add(panel[2],BorderLayout.WEST);
		add(panel[1],BorderLayout.EAST);
		add(panel[0],BorderLayout.NORTH);
	};
	public static fifthpanel getInstance() {
		return instance;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
		mbtn.setBounds(10, 0, 120,60);
	}
}