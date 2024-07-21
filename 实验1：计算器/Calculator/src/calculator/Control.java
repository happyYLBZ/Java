package calculator;

public class Control {
	  //单例模式
      private static Control instance;
      private Control() {
    	  
      }
      public static Control getInstance() {
    	  if(instance==null) {
    		  instance=new Control();
    	  }
    	  return instance;
      }
      String []num= {"0","1","2","3","4","5","6","7","8","9","."};
	  public void pressBtn(String command) {
		  //输入数字
		  boolean T=false;
		  for(int i=0;i<=10;i++) {
			  if(command.equals(num[i])) {
				  T=true;
			  }
		  }
		  if(T) {
			  Model.getInstance().addNumber(command);
			  if(Model.getInstance().operator1!=null) {
				  calculator.getInstance().showNumber(Model.getInstance().num2.toString());
				  System.out.println(Model.getInstance().num2.toString());
			  }
			  else {
				  calculator.getInstance().showNumber(Model.getInstance().num1.toString());
				  System.out.println(Model.getInstance().num1.toString());
			  }
		  }
		  //输入运算符
		 else {
			 //“=”实现
			  if(command.equals("=")) {
					calculator.getInstance().showNumber(Model.getInstance().getResult());
					System.out.println(Model.getInstance().getResult());
					Model.getInstance().clear();//显示结果，并将数字和运算符全清空，方便下一次输入
			  }
			  else {
				  //排除CE和→因为很特殊
				  if(Model.getInstance().operator1==null) {
					  if(command.equals("CE")||command.equals("→")) {}
					  else {
					  calculator.getInstance().showNumber(command);
					  System.out.println(command);
					  Model.getInstance().setOperator1(command);
				      }
				  }
				  else {
					  if(command.equals("CE")||command.equals("→")) {}
					  else {
					  calculator.getInstance().showNumber(command);
					  System.out.println(command);
					  Model.getInstance().setOperator2(command);
				     }
				  }
			  }
			  //“C”实现
			  if(command.equals("C")) {
				    calculator.getInstance().showNumber(null);
				    System.out.println(command);
				    System.out.println("清空");
					Model.getInstance().clear();
			  }
			  //“→”实现
			  if(command.equals("→")) {
				  //判断该改变num1,num2,operator1,operator2中的哪个
				  if ( Model.getInstance().operator2!=null) {
					  calculator.getInstance().showNumber(null);
					  System.out.println(command);
					  System.out.println("退格");
					  Model.getInstance().operator2=null;
				  }
				  else {
					  //Model.getInstance().num2.length()!=0而不能Model.getInstance().num2!=null
					  if (Model.getInstance().num2.length()!=0) {
						  Model.getInstance().num2.delete(Model.getInstance().num2.length()-1, Model.getInstance().num2.length());
						  calculator.getInstance().showNumber( Model.getInstance().num2.toString());
						  System.out.println("退格");
						  System.out.println(Model.getInstance().num2.toString());
					  }
					  else {
						  if(Model.getInstance().operator1!=null) {
							  calculator.getInstance().showNumber(null);
							  System.out.println(command);
							  System.out.println("退格");
							  Model.getInstance().operator1=null;
						  }
						  else {
							  Model.getInstance().num1.delete(Model.getInstance().num1.length()-1, Model.getInstance().num1.length());
							  calculator.getInstance().showNumber( Model.getInstance().num1.toString());
							  System.out.println("退格");
							  System.out.println(Model.getInstance().num1.toString());
						  }
					  }
					  
				  }	  
			  }
			  //CE的实现
			  if(command.equals("CE")) {	
				  //判断该改变num1,num2,operator1,operator2中的哪个
				  if ( Model.getInstance().operator2!=null) {
					  calculator.getInstance().showNumber(null);
					  System.out.println(command);
					  System.out.println("删除");
					  Model.getInstance().operator2=null;
				  }
				  else {
					  if (Model.getInstance().num2.length()!=0) {
						  calculator.getInstance().showNumber(null);
						  System.out.println(command);
						  System.out.println("删除");
						  Model.getInstance().num2.delete(0, Model.getInstance().num2.length());
					  }
					  else {
						  if(Model.getInstance().operator1!=null) {
							  calculator.getInstance().showNumber(null);
							  System.out.println(command);
							  System.out.println("删除");
							  Model.getInstance().operator1=null;
						  }
						  else {
							  calculator.getInstance().showNumber(null);
							  System.out.println(command);
							  System.out.println("删除");
							  Model.getInstance().num1.delete(0, Model.getInstance().num1.length());
						  }
					  }
					  
				  }	  
			  }
			  //单操作数运算符实现，直接输出结果
			  if(command.equals("1/x")) {
				   calculator.getInstance().showNumber(Model.getInstance().getResult());
				   System.out.println(Model.getInstance().getResult());
					Model.getInstance().clear();
			  }
			  if(command.equals("x²")) {
				  calculator.getInstance().showNumber(Model.getInstance().getResult());
				  System.out.println(Model.getInstance().getResult());
					Model.getInstance().clear();
			  }
			  if(command.equals("√x")) {
					calculator.getInstance().showNumber(Model.getInstance().getResult());
					System.out.println(Model.getInstance().getResult());
					Model.getInstance().clear();
			  }
			  if(command.equals("+/-")) {
				  //判断把num1,num2中的哪个加+/-
				  if (Model.getInstance().num2.length()!=0) {
					  if(Double.valueOf(Model.getInstance().num2.toString())<=0) {
						  calculator.getInstance().showNumber("+"+Model.getInstance().num2);
						  System.out.println("+"+Model.getInstance().num2);
						  //把String变成StringBuilder
						  StringBuilder h=new StringBuilder("+"+Model.getInstance().num2);
						  Model.getInstance().num2=h;
	                		}
	                		else {
	                			 calculator.getInstance().showNumber("-"+Model.getInstance().num2);
	                			 System.out.println("-"+Model.getInstance().num2);
	   						  StringBuilder h=new StringBuilder("-"+Model.getInstance().num2);
	   						 Model.getInstance().num2=h;
	                		}
					  if(Model.getInstance().operator2!=null) {
						  Model.getInstance().operator2=null;
					  }
					  else {
						  Model.getInstance().operator1=null;
					  }
				  }
				  else {
					  calculator.getInstance().showNumber(Model.getInstance().getResult());
					  System.out.println(Model.getInstance().getResult());
					  StringBuilder h=new StringBuilder(Model.getInstance().getResult());
					  Model.getInstance().num1=h;
					  if(Model.getInstance().operator2!=null) {
						  Model.getInstance().operator2=null;
					  }
					  else {
						  Model.getInstance().operator1=null;
					  }
				  }
			  }
			  
			  
	  }
	}
}
