package Gobang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class toolpanel extends JPanel{
	 //图片
	ImageIcon backicon=new ImageIcon("src/imagines/back.jpg");
	ImageIcon heicon=new ImageIcon("src/imagines/he.png");
	ImageIcon huiicon=new ImageIcon("src/imagines/hui.png");
	ImageIcon xiangicon=new ImageIcon("src/imagines/xiang.png");
	ImageIcon leaveicon=new ImageIcon("src/imagines/leave.png");
	ImageIcon m=new ImageIcon("src/imagines/message.png");
	ImageIcon t11=new ImageIcon("src/imagines/t11.jpg");
	ImageIcon t22=new ImageIcon("src/imagines/t22.jpg");
	ImageIcon t1=new ImageIcon("src/imagines/t1.jpg");
	ImageIcon t2=new ImageIcon("src/imagines/t2.jpg");
	ImageIcon girl=new ImageIcon("src/imagines/girl.jpeg");
	 //组件
	JLabel xiang=new JLabel("认输");
	JLabel he=new JLabel("求和");
	JLabel hui=new JLabel("悔棋");
	JLabel mes=new JLabel("发消息");
	JLabel leave=new JLabel("离开");
	JLabel kuang[]=new JLabel[2];
	JButton btn[]=new JButton[5];
	JButton cbtn=new JButton("重开");
	JRadioButton mbtn=new JRadioButton("背景音乐",true);
	//字符串
	Object[] info= {"快点吧！我等的花都要谢了","大哥，手下留情!","不要走！决战到天亮","你好菜啊！","棋逢对手！"};
      //单例模式
	private static toolpanel instance=new toolpanel();
    private toolpanel() {
    	setPreferredSize(new Dimension(400, 700));
    	//按钮
    	for(int i=0;i<=4;i++) {
    		btn[i]=new JButton();
    		btn[i].setContentAreaFilled(false);
    		btn[i].setFocusable(false);
    		add(btn[i]);
    	}
    	mbtn.setFont(new Font("楷体", Font.BOLD, 20));
		mbtn.setForeground(new Color(200, 0, 0));
	    mbtn.setContentAreaFilled(false);//透明
		mbtn.setFocusable(false);//去焦点
		add(mbtn);
		mbtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(mbtn.isSelected()) {
						 Control.getInstance().player.start(true);
						 thirdpanel.getInstance().mbtn.setSelected(true);
						 secondpanel.getInstance().btn3.setSelected(true);
						 fifthpanel.getInstance().mbtn.setSelected(true);
					}
					else {
						Control.getInstance().player.stop();
						thirdpanel.getInstance().mbtn.setSelected(false);
						secondpanel.getInstance().btn3.setSelected(false);
						fifthpanel.getInstance().mbtn.setSelected(false);
					}
					
				}
			});
		cbtn.setFont(new Font("楷体", Font.BOLD, 20));
		cbtn.setForeground(new Color(200, 0, 0));
	    cbtn.setContentAreaFilled(false);//透明
		cbtn.setFocusable(false);//去焦点
		add(cbtn);
		//重开监听器
		cbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   int i=JOptionPane.showConfirmDialog(null, "你确定要重开吗?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				   if(i==0) {
					   NetHelper.getInstance().sendtips("请求重开");
				   }
			}
		});
    	//标签
    	xiang.setOpaque(false);
    	he.setOpaque(false);
    	hui.setOpaque(false);
    	mes.setOpaque(false);
    	leave.setOpaque(false);
    	xiang.setFont(new Font("楷体", Font.BOLD, 20));
    	xiang.setForeground(new Color(111,222,0));
    	he.setFont(new Font("楷体", Font.BOLD, 20));
    	he.setForeground(new Color(111,222,0));
    	hui.setFont(new Font("楷体", Font.BOLD, 20));
    	hui.setForeground(new Color(111,222,0));
    	mes.setFont(new Font("楷体", Font.BOLD, 20));
    	mes.setForeground(new Color(111,222,0));
    	leave.setFont(new Font("楷体", Font.BOLD, 20));
    	leave.setForeground(new Color(200,0,0));
    	kuang[0]=new JLabel("",JLabel.RIGHT);
    	kuang[1]=new JLabel("",JLabel.RIGHT);
    	kuang[0].setFont(new Font("楷体", Font.BOLD, 20));
    	kuang[0].setForeground(new Color(111,222,0));
    	kuang[1].setFont(new Font("楷体", Font.BOLD, 20));
    	kuang[1].setForeground(new Color(111,222,0));
        add(kuang[0]);
        add(kuang[1]);
    	add(xiang);
    	add(he);
    	add(hui);
    	add(mes);
    	add(leave);
    	//离开监听器
    	btn[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
            int i=JOptionPane.showConfirmDialog(null, "你确定要退出吗?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(i==0) {
            	Play.getInstance().card.previous(Play.getInstance().getContentPane());
            	new Thread() {
            	   public void run() {
            		  Model.getInstance().clear();
            		  NetHelper.getInstance().close();
            	   };
            	}.start();
              }
			}
		});
    	//发消息监听器
    	btn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    String s=(String)JOptionPane.showInputDialog(toolpanel.getInstance(),"选择发送的消息","消息框",JOptionPane.QUESTION_MESSAGE,girl,info,info[0]);
			    NetHelper.getInstance().sendmessage(s);
			    Control.getInstance().showmessage(s);
			}
		});
    	//悔棋监听器
    	btn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			int i=JOptionPane.showConfirmDialog(null, "你确定要悔棋吗?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		    if(i==0) {
		            NetHelper.getInstance().sendtips("请求悔棋");
		      }
			}
		});
    	//求和监听器
    	btn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  int i=JOptionPane.showConfirmDialog(null, "你确定要求和吗?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		            if(i==0) {
		             NetHelper.getInstance().sendtips("求和");
		              }
			}
		});
    	//认输监听器
    	btn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  int i=JOptionPane.showConfirmDialog(null, "你确定要认输吗?","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		            if(i==0) {
		            	NetHelper.getInstance().sendtips("对方认输");
		            	Control.getInstance().setcanPut(false);
//		            	Play.getInstance().card.previous(Play.getInstance().getContentPane());
		              }
				
			}
		});
    };
    public static toolpanel getInstance() {
    	return instance;
    }
    @Override
    protected void paintComponent(Graphics g) {
    	g.drawImage(backicon.getImage(), 0, 0, getWidth(), getHeight(), this);
    	g.drawImage(xiangicon.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.87), 43, 43, this);
    	g.drawImage(heicon.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.74), 43, 43, this);
    	g.drawImage(huiicon.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.62), 43, 43, this);
    	g.drawImage(leaveicon.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.49), 43, 43, this);
    	g.drawImage(m.getImage(), (int)(getWidth()*0.2), (int)(getHeight()*0.87), 43, 43, this);
    	if(NetHelper.getInstance().getisserver()) {
    		if(NetHelper.getInstance().isConnect()) {
    			g.drawImage(t1.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.01), 43,43, this);
            	g.drawImage(t2.getImage(), (int)(getWidth()*0.86), (int)(getHeight()*0.90), 43,43, this);
    		}
    		else {
    			g.drawImage(t11.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.01), 43,43, this);
            	g.drawImage(t2.getImage(), (int)(getWidth()*0.86), (int)(getHeight()*0.90), 43,43, this);
    		}
    	}
    	else {
    		g.drawImage(t2.getImage(),(int)(getWidth()*0.03), (int)(getHeight()*0.01), 43,43, this);
        	g.drawImage(t1.getImage(), (int)(getWidth()*0.86), (int)(getHeight()*0.90), 43,43, this);
    	}
    	xiang.setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.94), 50, 43);
    	he.setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.81), 50, 43);
    	hui.setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.68), 50, 43);
    	mes.setBounds((int)(getWidth()*0.2), (int)(getHeight()*0.94), 80, 43);
    	leave.setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.56), 80, 43);
    	kuang[0].setBounds((int)(getWidth()*0.32), (int)(getHeight()*0.83), 273,43);
    	kuang[1].setBounds((int)(getWidth()*0.2), (int)(getHeight()*0.01), 273,43);
    	btn[0].setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.87), 43, 43);
    	btn[1].setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.74), 43, 43);
    	btn[2].setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.62), 43, 43);
    	btn[3].setBounds((int)(getWidth()*0.2), (int)(getHeight()*0.87), 43, 43);
    	btn[4].setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.49), 44, 44);
    	mbtn.setBounds((int)(getWidth()*0.03), 70, 130, 80);
    	cbtn.setBounds((int)(getWidth()*0.03), (int)(getHeight()*0.39), 75, 43);
    }
}
