package Gobang;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class firstpanel extends JPanel{
	ImageIcon icon=new ImageIcon("src/imagines/p1.jpg");
	JButton btn=new JButton("开始游戏");
      //单例模式
	 private static firstpanel instance=new firstpanel();
	 private firstpanel() {
		 setLayout(null);
		 btn.setFont(new Font("楷体", Font.BOLD, 20));
		 btn.setContentAreaFilled(false);//透明
		 btn.setFocusable(false);//去焦点
		 add(btn);
		 btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Play.getInstance().card.next(Play.getInstance().getContentPane());
			Control.getInstance().player.start(true);
			}
		});
	 };
	 public static firstpanel getInstance() {
		  return instance;
	 }
	 @Override
	protected void paintComponent(Graphics g) {
		 g.drawImage(icon.getImage(),0,0,getWidth(),getHeight(),this);
		 btn.setBounds((int)(getWidth()*0.17), (int)(getHeight()*0.55), 130, 60);
	}
}
