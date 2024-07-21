package Internet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server {
	 ServerSocket ss;
	 Socket s;
	 BufferedReader in;
	 PrintWriter out;
	 int port;
	 User user[]=new User[5];//储存多个客户端
	 int i=0;//客户端数量
	 String c;
	 boolean T=true;
	 //按钮
	 JButton btn1;
	 JButton btn2;
	 //文本域
	 JTextField cinport;
	 JTextArea chatzone;
	 JTextArea journal;
	 //标签
	 JLabel label1;
	 JLabel label2;
	 JLabel label3;
	 JLabel label4;
	 //ComboBox
	 String[] clients= {"Client1","Client2","Client3","Client4","Client5"};
	 JComboBox box;
	 //上面的小面板
	 JPanel [] upsmallpanel=new JPanel[2];
	 //下面的小面板
	 JPanel []downsmallpanel=new JPanel[2];
	 //上面大面板
	 JPanel uppanel;
	 //下面大面板
	 JPanel downpanel;
	 //frame
	 JFrame f;
	     public  Server () throws Exception {
		 //按钮
		 btn1=new JButton("Stop");
		 btn2=new JButton("发送至");
		 //文本域
		 cinport=new JTextField();
		 cinport.setPreferredSize(new Dimension(40, 20));
		 chatzone=new JTextArea();
		 journal=new JTextArea();
		 //标签
		 label1=new JLabel("请输入服务端口号:");
		 label2=new JLabel("需要发送的内容");
		 label3=new JLabel("日志");
		 label4=new JLabel("");
		 //ComboBox
		 box=new JComboBox<>(clients);
		 //上面的小面板
		 upsmallpanel[0]=new JPanel();
		 upsmallpanel[1]=new JPanel();
		 upsmallpanel[0].setLayout(new FlowLayout());//流式默认为中部
		 upsmallpanel[0].add(label1);
		 upsmallpanel[0].add(cinport);
		 upsmallpanel[0].add(btn1);
		 upsmallpanel[1].setLayout(new BorderLayout());
		 upsmallpanel[1].add(label2, BorderLayout.NORTH);
		 upsmallpanel[1].add(chatzone,BorderLayout.CENTER);
		 //下面的小面板
		 downsmallpanel[0]=new JPanel();
		 downsmallpanel[1]=new JPanel();
		 downsmallpanel[0].setLayout(new FlowLayout());
		 downsmallpanel[0].add(btn2);
		 downsmallpanel[0].add(box);
		 downsmallpanel[1].setLayout(new BorderLayout());
		 downsmallpanel[1].add(label3, BorderLayout.NORTH);
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
		 downpanel.add(label4, BorderLayout.SOUTH);
		 //frame
		 f=new JFrame("服务器");
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.setSize(400, 500);
		 f.getContentPane().setLayout(new GridLayout(2, 1));
		 f.add(uppanel);
		 f.add(downpanel);
		 f.setVisible(true);
	 }
	     public void begin() throws Exception {
         Thread SeverThread=new Thread() {
        	 public void run() {
        		 try {
        			ss=new ServerSocket(port);
 					label4.setText("服务启动，端口为"+port);
 					journal.append("服务启动，端口为"+port+"\n");
        			 while(true) {
        				 s=ss.accept();
        				 user[i]=new User(s,clients[i]);
        				 journal.append("接受客户端"+clients[i]+"的请求"+"\n");
        				 i++;
        				 new Thread() {
             				public void run() {
             					String message;
             					try {
             						in=new BufferedReader(new InputStreamReader(user[i-1].socket.getInputStream()));
                	        		out=new PrintWriter(user[i-1].socket.getOutputStream(),true);
             						while((message=in.readLine())!=null) {
             							journal.append("来自客户端"+user[i-1].name+"的信息:"+message+"\n");
             						}
             					} catch (IOException e) {
             						// TODO Auto-generated catch block
             						e.printStackTrace();
             					}
             				};
             			 }.start();
        			 }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	 };
         };
	     //发送按钮监听器
	     btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					pressbtn2();
				}
		 });
	     //stop按钮监听器
	     btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					pressbtn1();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	    //组合框监听器
	     box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb=(JComboBox)e.getSource();
				String clt=(String)cb.getSelectedItem();
				new Thread() {
					public void run() {
						for(User u:user) {
							if(u==null)break;
							if(u.name.equals(clt)) {
								try {
									in=new BufferedReader(new InputStreamReader(u.socket.getInputStream()));
									out=new PrintWriter(u.socket.getOutputStream(),true);
									c=u.name;
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							}
						}
						
					};
				}.start();
			}
		});
	 }
	     public void pressbtn2() {
			 String message;
	         message=chatzone.getText();
	         if(message!=null) {
	        	 out.println(message);
	        	 journal.append("向客户端"+c+"发送："+message+"\n");
	        	 chatzone.setText("");
	         }
		 }
	     public void pressbtn1() throws Exception {
	    	 ss.close();
	    	 s.close();
	    
	     }
     public static void main(String[] args)throws Exception {
    	Server server=new Server();
    	server.begin();
     }
}
class User{
	Socket socket;
	String name;
	public User() {
		
	}
	public User(Socket s,String n) {
		socket=s;
		name=n;
	}
	public Socket getsocket() {
		return socket;
	}
}
