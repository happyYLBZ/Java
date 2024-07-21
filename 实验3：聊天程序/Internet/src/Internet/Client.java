package Internet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	 Socket s;
	 BufferedReader in;
	 PrintWriter out;
	 int port;
	 String address;//
	 //按钮
	 JButton btn;
	 //文本域
	 JTextField cinaddress;
	 JTextField cinport;
	 JTextArea chatzone;
	 JTextArea journal;
	 //标签
	 JLabel label1;
	 JLabel label2;
	 JLabel label3;
	 JLabel label4;
	 JLabel label5;
	 //上面的小面板
	 JPanel []upsmallpanel=new JPanel[2];
	 //下面的小面板
	 JPanel []downsmallpanel=new JPanel[2];
	 //上面大面板
	 JPanel uppanel;
	 //下面大面板
	 JPanel downpanel;
	 //frame
	 JFrame f;
	 public Client() throws Exception {
		 //按钮
		 btn=new JButton("发送");
		 //文本域
		 cinaddress=new JTextField();
		 cinaddress.setPreferredSize(new Dimension(70,20));
		 cinport=new JTextField();
		 cinport.setPreferredSize(new Dimension(50, 20));
		 cinport.setText("");
		 chatzone=new JTextArea(20,10);
		 journal=new JTextArea(20,10);
		 //标签
		 label1=new JLabel("请输入服务器地址:");
		 label2=new JLabel("端口");
		 label3=new JLabel("需要发送的信息");
		 label4=new JLabel("日志");
		 label5=new JLabel("");
		 //上面的小面板
		 upsmallpanel[0]=new JPanel();
		 upsmallpanel[1]=new JPanel();
		 upsmallpanel[0].setLayout(new FlowLayout());//流式默认为中部
		 upsmallpanel[0].add(label1);
		 upsmallpanel[0].add(cinaddress);
		 upsmallpanel[0].add(label2);
		 upsmallpanel[0].add(cinport);
		 upsmallpanel[1].setLayout(new BorderLayout());
		 upsmallpanel[1].add(label3, BorderLayout.NORTH);
		 upsmallpanel[1].add(chatzone,BorderLayout.CENTER);
		 //下面的小面板
		 downsmallpanel[0]=new JPanel();
		 downsmallpanel[1]=new JPanel();
		 downsmallpanel[0].setLayout(new FlowLayout());
		 downsmallpanel[0].add(btn);
		 downsmallpanel[1].setLayout(new BorderLayout());
		 downsmallpanel[1].add(label4, BorderLayout.NORTH);
		 downsmallpanel[1].add(journal,BorderLayout.CENTER);
		 //上面大面板
		 uppanel=new JPanel();
		 uppanel.setLayout(new BorderLayout());
		 uppanel.add(upsmallpanel[0], BorderLayout.NORTH);
		 uppanel.add(upsmallpanel[1], BorderLayout.CENTER);
		 //下面大面板
		 downpanel=new JPanel();
		 downpanel.setLayout(new BorderLayout());
		 downpanel.add(downsmallpanel[0], BorderLayout.NORTH);
		 downpanel.add(downsmallpanel[1],BorderLayout.CENTER);
		 downpanel.add(label5,BorderLayout.SOUTH);
		 //frame
		 f=new JFrame("客户端");
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.setSize(400, 500);
		 f.getContentPane().setLayout(new GridLayout(2, 1));
		 f.add(uppanel);
		 f.add(downpanel);
		 f.setVisible(true);
	 }
	 public void begin() throws Exception{
		 Thread SeverThread=new Thread() {
			 public void run() {
				 try {
					s=new Socket(address,port);
					System.out.println(address);
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					out=new PrintWriter(s.getOutputStream(),true);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 label5.setText("连接服务器"+address+" :"+"8090"+"成功！");
				 journal.append("连接服务器"+address+" :"+"8090"+"成功！"+"\n");
				 new Thread() {
						public void run() {
							String message;
							try {
								while((message=in.readLine())!=null) {
									journal.append("来自服务器的信息:"+message+"\n");
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						};
					 }.start();
			 };
		 };
		 //发送按钮监听器
		 btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 pressbtn();
				}
			});
		 //文本框监听器(获取地址)
		 cinaddress.addKeyListener(new KeyAdapter() {
			 @Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
				if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
	    			address=cinaddress.getText();
	    		}
			}
		});
		 //文本框监听器(获取端口号)
	     cinport.addKeyListener(new KeyAdapter() {
	    	 @Override
	    	public void keyTyped(KeyEvent e) {
	    		// TODO Auto-generated method stub
	    		super.keyTyped(e);
	    		if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
	    			port=Integer.parseInt(cinport.getText());
	                SeverThread.start();
	    		}
	    	}
		});
	 }
	 public void pressbtn() {
		 String message;
         message=chatzone.getText();
         if(message!=null) {
        	 out.println(message);
        	 journal.append("向客户端发送: "+message);
        	 chatzone.setText("");
         }
	 }
	 public static void main (String[] args)throws Exception {
		 Client client=new Client();
		 client.begin();
	 }
      
}
