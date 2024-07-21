package paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Shape {
    //成员
	Shape(){
		
	}
	Shape(int a,int b,int c,int d,String s,Color color,String h,int m){
		x1=a;
		x2=b;
		y1=c;
		y2=d;
		type=s;
		this.c=color;
		text=h;
		size=m;
		t=false;
	}
	Shape(int a,int b,int c,int d,String s,Color color,String h,int m,boolean t){
		x1=a;
		x2=b;
		y1=c;
		y2=d;
		type=s;
		this.c=color;
		text=h;
		size=m;
		this.t=t;
	}
    int x1,x2,y1,y2;//存储坐标
    String type;//存储图形类型
    Color  c;// 存储颜色
    boolean t;//是否实心
    //只为文字
    String text;//存储文本
    int size;//文字大小
}
