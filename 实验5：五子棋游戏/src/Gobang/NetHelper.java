package Gobang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class NetHelper {
	  //属性
	  private int port;
	  private String address;
	  private BufferedReader in;
	  private PrintWriter out;
	  private Socket s;
	  private ServerSocket serversocket;
	  private boolean isserver=true;
       //单例模式
	  private static NetHelper instance;
	  private NetHelper() {};
	  public static NetHelper getInstance() {
		  if(instance==null) {
			  instance=new NetHelper();
		  }
		  return instance;
	  }
	  //返回是否连接成功
	  public boolean isConnect() {
		  if(s==null) {
			  return false;
		  }
		  return true;
	  }
	  //返回是不是服务器
	  public boolean getisserver() {
		  return isserver;
	  }
	  public void beginlisten(int p) {
		  port=p;
		  try {
		    serversocket=new ServerSocket(port);
			new Thread() {
				public void run() {
					try {
						Control.getInstance().setColor(Model.Black);
					    s=serversocket.accept();
					    Control.getInstance().setcanPut(true);
					    toolpanel.getInstance().repaint();
					    in=new BufferedReader(new InputStreamReader(s.getInputStream()));
    	        		out=new PrintWriter(s.getOutputStream(),true);
    	        		accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();;
		} catch (IOException e) {
			beginconnect(p,"localhost");
		}
	  }
	public void beginconnect(int p,String a) {
		 try {
				port=p;
				address=a;
				s=new Socket(address, port);
				isserver=false;
				Control.getInstance().setColor(Model.White);
				in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	     		out=new PrintWriter(s.getOutputStream(),true);
				accept();
		} catch (UnknownHostException e) {
			System.exit(0);
			JOptionPane.showMessageDialog(null, "房间已满","提示",JOptionPane.OK_OPTION);
			Play.getInstance().card.show(Play.getInstance().getContentPane(), "3");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	//发送棋子
	public void sendChess(int row, int col) {
		new Thread() {
			@Override
			public void run() {
				 String message="PutChess:"+row+","+col;
			     out.println(message);
			}
		}.start();
	}
	//发送信息
	public void sendmessage(String s) {
		new Thread() {
			@Override
			public void run() {
				 String message="Chat:"+s;
			     out.println(message);
			}
		}.start();
	}
	//发送提示
	public void sendtips(String s) {
		if(s.equals("你赢了")) {
		new Thread() {
			@Override
			public void run() {
				 String message="Tipt";
			     out.println(message);
			  }
		    }.start();
		}
		if(s.equals("你输了")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tipf";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("对方认输")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tipr";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("求和")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tiph";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("求和成功")) {
			new Thread() {
				@Override
				public void run() {
					 String message="TipY";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("求和失败")) {
			new Thread() {
				@Override
				public void run() {
					 String message="TipN";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求悔棋")) {
			new Thread() {
				@Override
				public void run() {
					 String message="TipQ";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求悔棋失败")) {
			new Thread() {
				@Override
				public void run() {
					 String message="TipF";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求悔棋成功")) {
			new Thread() {
				@Override
				public void run() {
					 String message="TipS";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求重开")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tip1";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求重开成功")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tip2";
				     out.println(message);
				  }
			    }.start();
		}
		if(s.equals("请求重开失败")) {
			new Thread() {
				@Override
				public void run() {
					 String message="Tip3";
				     out.println(message);
				  }
			    }.start();
		}
	}
	//接收函数
	public void accept() {
		new Thread() {
			@Override
			public void run() {
				try {
					String message;
					while((message=in.readLine())!=null) {
						if(message.startsWith("PutChess:")) {
							acceptChess(message);
						}
						if(message.startsWith("Chat:")) {
							acceptmessage(message);
						}
						if(message.startsWith("Tip")) {
							accepttips(message);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	private  void accepttips(String message) {
	   if(message.startsWith("Tipf")) {
		   Control.getInstance().showtip("你输了");
	   }
	   if(message.startsWith("Tipt")) {
		   Control.getInstance().showtip("你赢了");
	   }
	   if(message.startsWith("Tipr")) {
		   Control.getInstance().showtip("对方认输");
	   }
	   if(message.startsWith("Tiph")) {
		   Control.getInstance().showtip("对方求和");
	   }
	   if(message.startsWith("TipY")) {
		   Control.getInstance().showtip("求和成功");
	   }
	   if(message.startsWith("TipN")) {
		   Control.getInstance().showtip("求和失败");
	   }
	   if(message.startsWith("TipQ")) {
		   Control.getInstance().showtip("请求悔棋");
	   }
	   if(message.startsWith("TipF")) {
		   Control.getInstance().showtip("请求悔棋失败");
	   }
	   if(message.startsWith("TipS")) {
		   Control.getInstance().showtip("请求悔棋成功");
	   }
	   if(message.startsWith("Tip1")) {
		   Control.getInstance().showtip("请求重开");
	   }
	   if(message.startsWith("Tip2")) {
		   Control.getInstance().showtip("请求重开成功");
	   }
	   if(message.startsWith("Tip3")) {
		   Control.getInstance().showtip("请求重开失败");
	   }
	}
	//接受聊天消息
	private void acceptmessage(String message) {
	     String s=message.substring(5);
	     Control.getInstance().showmessage(s);
	}
	private void acceptChess(String message) {
		String line=message.substring(9);
		String []arrays=line.split(",");
		int row=Integer.parseInt(arrays[0]);
		int col=Integer.parseInt(arrays[1]);
		Control.getInstance().setisOther(true);
		Control.getInstance().setcanPut(true);
		Control.getInstance().putChess(row, col);
	}
	public void close() {
			try {
				if(serversocket==null) {
				    s.close();
				}
				else {
					if(s==null) {
						serversocket.close();
					}
					else {
						s.close();
						serversocket.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
} 