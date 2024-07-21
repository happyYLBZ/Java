package countfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountFile {
	   private static int num1=0,num2=0;
	   private static long num=0;
       public static void main(String[] args) throws Exception {
    	   BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    	   String path=in.readLine();
    	   File f=new File(path);
    	   if(f.exists()) {
    		   getAllFile(f);
        	   System.out.println("文件夹数："+num1);
        	   System.out.println("文件数："+num2);
        	   System.out.println("文件尺寸之和："+num);
    	   }
    	   else {
    		   System.out.println("文件目录不存在");
    	   }
       }
       public static void getAllFile (File f) {
    	   File[] fileArray=f.listFiles();
    	   if(fileArray!=null) {
    		   for(File file:fileArray) {
    			   if(file.isDirectory()) {
    				   num1++;
    				   getAllFile(file);
    			   } 
    			   else {
    				   num2++;
    				   num+=file.length();
    			   }
    		   }
    	   }
       }
}
