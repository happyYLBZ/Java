package calculator;

import java.text.NumberFormat;

public class Model {
	     // 用于将数字转化成字符串 
	    NumberFormat numberformat=NumberFormat.getInstance();
        private static Model instance;
        private Model() {
        	
        }
        public static Model getInstance() {
        	if(instance==null) {
        		instance=new Model();
        	}
        	return instance;
        }
        StringBuilder num1 =new StringBuilder();//第一个操作数
        StringBuilder num2 =new StringBuilder();//第二个操作数
        String operator1=null;
        String operator2=null;//用来实现%
        public void addNumber(String num) {
        	if(operator1==null) {
        		num1.append(num);
        	}
        	else {
        		num2.append(num);
        	}
        }
        public void setOperator1(String opt) {
        	operator1=opt;
        }
        public void setOperator2(String opt) {
        	operator2=opt;
        }
        public String getResult() {
        	if(operator1.equals("1/x")) {
        	    if(num1.toString()=="0") {
        	    	return "0没有倒数";
        	    }
        	    else {
        	    	double num=1/Double.valueOf(num1.toString());
        	    	numberformat.setGroupingUsed(false);//不用科学计数法
        	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
        	    	return numberformat.format(num);
        	    }
        	}
        	if(operator1.equals("x²")) {
        		double num=Double.valueOf(num1.toString())*Double.valueOf(num1.toString());
    	    	numberformat.setGroupingUsed(false);//不用科学计数法
    	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
    	    	return numberformat.format(num);
        	}
        	if(operator1.equals("√x")) {
        		if(Double.valueOf(num1.toString())<0) {
        			return "负数不能开根号";
        		}
        		else {
        		    double num=Math.sqrt(Double.valueOf(num1.toString()));
        		    return numberformat.format(num);
        		}
        	}
        	if(operator1.equals("÷")) {
        		if(num2.toString().equals("0")) {
        			return "0不能作除数";
        		}
        		else {
        			double n;
        			if(operator1!=null&&operator2!=null&&operator2.equals("%")) {
                		 n=Double.valueOf(num2.toString())/100;
                	}
        			else {
        				n=Double.valueOf(num2.toString());
        			}
        			double num=Double.valueOf(num1.toString())/n;
        		    numberformat.setGroupingUsed(false);//不用科学计数法
        	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
        	    	return numberformat.format(num);
        		}
        	}
        	if (operator1.equals("×")) {
        		double n;
    			if(operator1!=null&&operator2!=null&&operator2.equals("%")){
            		 n=Double.valueOf(num2.toString())/100;
            	}
    			else {
    				n=Double.valueOf(num2.toString());
    			}
        		double num=Double.valueOf(num1.toString())*n;
    		    numberformat.setGroupingUsed(false);//不用科学计数法
    	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
    	    	return numberformat.format(num);
			}
        	if(operator1.equals("—")) {
        		double n;
    			if(operator1!=null&&operator2!=null&&operator2.equals("%")) {
            		 n=Double.valueOf(num2.toString())/100;
            	}
    			else {
    				n=Double.valueOf(num2.toString());
    			}
        		double num=Double.valueOf(num1.toString())-n;
    		    numberformat.setGroupingUsed(false);//不用科学计数法
    	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
    	    	return numberformat.format(num);
        	}
        	if(operator1.equals("+")) {
        		double n;
    			if(operator1!=null&&operator2!=null&&operator2.equals("%")) {
            		 n=Double.valueOf(num2.toString())/100;
            	}
    			else {
    				n=Double.valueOf(num2.toString());
    			}
        		double num=Double.valueOf(num1.toString())+n;
    		    numberformat.setGroupingUsed(false);//不用科学计数法
    	    	numberformat.setMaximumFractionDigits(10);//输出十个小数位
    	    	return numberformat.format(num);
        	}
        	if(operator1.equals("+/-")){
        		if(Model.getInstance().operator2!=null) {
        			if(Double.valueOf(num2.toString())<=0) {
                		return"+"+num2;
                		}
                		else {
                			return "-"+num2;
                		}
				  }
				  else {
					  if(Double.valueOf(num1.toString())<=0) {
			        		return"+"+num1;
			        		}
			        		else {
			        			return "-"+num1;
			        		}
				  }
        	}  
        	   return"输入不正确";
        }
        //重置函数，用于等于之后归零
		public void clear() {
			     num1.delete(0, num1.length());
		         num2.delete(0, num2.length());
		         operator1=null;
		         operator2=null;
		}
}
