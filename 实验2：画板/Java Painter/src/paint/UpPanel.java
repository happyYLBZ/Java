package paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class UpPanel {
	 //单例模式
	 private static UpPanel Instance;
     private UpPanel(){}
     public  static UpPanel getInstance() {
    	 if(Instance==null) {
    		 Instance=new UpPanel();
    	 }
    	 return Instance;
     }
     //顶层面板设置
          //标签
	 String labelname[]={"Shape Color","Text","BackGround","Size"};
	 JLabel[] label=new JLabel[4];
	      //颜色条
	 Color[]colors= {Color.black,Color.blue,Color.white,Color.red};
	      //组合框
	 class  ComboBoxRenderer extends JLabel  implements ListCellRenderer{
		 //准备 一个map，用于保存提前准备好的图片
		public HashMap<Color,ImageIcon>  icons = new HashMap<>();
		    public ComboBoxRenderer() {//构造函数
		        setOpaque(true); //设置透明
		        setHorizontalAlignment(CENTER);//设置本标签水平居中对齐
		        setVerticalAlignment(CENTER);//设置本标签垂直居中对齐
		        setPreferredSize(new Dimension(100, 20));//设置最佳尺寸
		        icons.put(Color.black,new ImageIcon("src/imagine/black.png")   );//在map中放入提前准备好图片
		        icons.put(Color.red,new ImageIcon("src/imagine/red.png"));     //用Color对象作为索引
		        icons.put(Color.blue,new ImageIcon("src/imagine/blue.png"));     //用Color对象作为索引
		        icons.put(Color.white,new ImageIcon("src/imagine/white.png"));
		}
		public Component getListCellRendererComponent(
		                                       JList list,
		                                       Object value,
		                                       int index,
		                                       boolean isSelected,
		                                       boolean cellHasFocus) {
			Color color = (Color) value;//将value转换成Color
			setIcon(icons.get(color)); //根据color，从map中提取相应图片，设置背景色
			return this;
		}
}
	 JComboBox[] box=new JComboBox[2];
	 ComboBoxRenderer renderer=new ComboBoxRenderer();
	      //文本域
	 JTextField textfield=new JTextField();
	     //CheckBox
	  JCheckBox checkbox=new JCheckBox("Fill the region");
	      //四个小面板
	 JPanel []UpSmallpanel=new JPanel[4];
	      //整体面板
	 JPanel UpBigpanel=new JPanel();
	      //滚动条
	 JPanel spanel=new JPanel();
	 JScrollPane scrollpane=new JScrollPane(spanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	 void setUpPanel() {
		  //标签
		 for(int i=0;i<=3;i++) {
			 label[i]=new JLabel(labelname[i]);
		 }
		 //文本域
		 textfield.setHorizontalAlignment(JTextField.LEFT);//靠右输出
		 textfield.setPreferredSize(new Dimension(300,30)); //设置宽高
		 textfield.setFont(new Font("楷体",Font.BOLD,30));   //设置字体类型、字体风格、大小
		  //监听器
		 ActionListener listener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 JComboBox cb=(JComboBox)e.getSource();
				 Color color=(Color)cb.getSelectedItem();
				 Draw.registeColorChanged(color);
			}
		};
         ActionListener listener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 JComboBox cb=(JComboBox)e.getSource();
				 Color color=(Color)cb.getSelectedItem();
				 Draw.getInstance().shapecolor=color;
			}
		};
		  //组合框
		 for(int i=0;i<=1;i++) {
			 box[i]=new JComboBox(colors);
			 ComboBoxRenderer renderer = new ComboBoxRenderer(); //一个自定义的能显示定制内容的组件
			 renderer.setPreferredSize(new Dimension(100, 20));//设置最佳尺寸
			 box[i].setRenderer(renderer);//让渲染组件起作用
			 box[i].setMaximumRowCount(4);//设置最大显示多少行
		 }
		 box[1].addActionListener(listener1);
		 box[0].addActionListener(listener2);
		  //滚动条
		       //监听器
		 ActionListener  sizelistener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String command=e.getActionCommand();
				Draw.getInstance().size=Integer.parseInt(command);
			}
		};
		  spanel.setLayout(new GridLayout(30,1));
		  scrollpane.setPreferredSize(new Dimension(100, 30));
		  //设置滚动速度
		  scrollpane.getVerticalScrollBar().setUnitIncrement(30);
		  String[]number=new String[30];
		  JButton[] sizebtn=new JButton[30];
		  for(int i=1;i<=30;i++) {
			  number[i-1]=new String(String.valueOf(i));
			  sizebtn[i-1]=new JButton(number[i-1]);
			  sizebtn[i-1].setPreferredSize(new Dimension(70, 30));
			  sizebtn[i-1].addActionListener(sizelistener);
			  spanel.add(sizebtn[i-1]);
		  }
		  //四个小面板
		 for(int i=0;i<=3;i++) {
			 UpSmallpanel[i]=new JPanel();
			 UpSmallpanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			 UpSmallpanel[i].add(label[i]);
		 }
		 UpSmallpanel[0].add(box[0]);
		 UpSmallpanel[1].add(textfield);
		 UpSmallpanel[2].add(box[1]);
		 UpSmallpanel[3].add(scrollpane);
		 UpSmallpanel[3].add(checkbox);
		  //大面板
		 UpBigpanel.setLayout(new GridLayout(2,2));
		 for(int i=0;i<=3;i++) {
			 UpBigpanel.add(UpSmallpanel[i]);
		 }
	 }
	 
	 
}
