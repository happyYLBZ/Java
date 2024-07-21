package Gobang;

import java.util.ArrayList;
import java.util.regex.Pattern;
public class AIcontrol {
	 //音效
	  Music put=new Music("D:\\浏览器下载\\put.wav");
	 //行数组
	 ArrayList<Integer>rowlist=new ArrayList<Integer>(16*16);
	 private int frontrow=-1;
	 //列数组
	 ArrayList<Integer>collist=new ArrayList<Integer>(16*16);
	 private int frontcol=-1;
	 //能不能下
	private boolean canput=true;
      //单例模式
	private static AIcontrol instance=new AIcontrol();
	private AIcontrol() {};
	public static AIcontrol getInstance() {
		return instance;
	}
    public int[][] Edge = new int[16][16];
    public boolean black = true;              //来判断谁下
    /**
     * 计算盘面价值
     * @return res[1]为黑色得分，res[2]为白色得分
     */

    public int[] evaALL(){
        int []res = new int[3];
        int scoreWhite=0;
        int scoreBlack=0;

        ArrayList<StringBuffer> list = new ArrayList<>();
        for(int i=1;i<=15;i++){
            //读取4个方向，斜的需要两种
            StringBuffer line[] = new StringBuffer[7];
            line[0] = getLine(1,0,8,i,15);
            line[1] = getLine(0,1,i,8,15);
            line[2] = getLine(1,1,15,i,20);
            line[3] = getLine(1,1,1,i,20);
            line[4] = getLine(1,-1,i,15,20);
            line[5] = getLine(1,-1,i,1,20);

            for (int j = 0; j < 6; j++) {
                scoreBlack+=getScoreBlack(line[j]);
                scoreWhite+=getScoreWhite(line[j]);
            }
        }
        res[0]=0;
        res[1]=scoreBlack;
        res[2]=scoreWhite;
        return res;
    }

    /**
     * 评估函数，评估棋盘
     *
     * @return 返回最终结果点，为res[3] row col 得分
     */
    public int[] evaluate() {
        int score = 0;
        int finrow = -1, fincol = -1, finscore = 0;
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <= 15; j++) {
                score = 0;
                //有子跳过
                if (getE(i, j) != 0) {
                    continue;
                }
                //方向数组       右上     右下   列    行
                int[][] dir = {{1, -1}, {1, 1}, {1, 0}, {0, 1}};
                for (int o = 0; o <= 3; o++) {
                    //指定方向
                    int dirx = dir[o][0];
                    int diry = dir[o][1];
                    //假设下在i，j
                    Edge[i][j] = 2;
                    //line为得到后的行
                    StringBuffer line = getLine(dirx, diry, i, j, 5);
                    //获取该行得分，并添加到总得分
                    score += getScoreWhite(line);
                }
                if (score > finscore) {
                    finrow = i;
                    fincol = j;
                    finscore = score;
                }
                //把模拟下的棋子收回去
                Edge[i][j] = 0;
            }
        }
        int[] res = new int[3];
        res[0] = finrow;
        res[1] = fincol;
        res[2] = finscore;
        return res;
    }
    //悔棋
	 public void regret() {
		 Edge[rowlist.get(frontrow)][collist.get( frontcol)]=0;
		 rowlist.remove(frontrow);
		 collist.remove(frontcol);
		 frontrow--;
		 frontcol--;
		 AIpanel.getInstance().repaint();
	 }
    /**
     * 白色得分函数，对传入的字符串进行正则匹配返回得分
     *
     * @param line
     * @return 该行字符串得分
     */
    public int getScoreWhite(StringBuffer line) {
        int OneLineScore = 0;
        Pattern p;

        //正则表达式匹配

        //对自己
        //连五 500000分
        //22222
        p = Pattern.compile("22222");
        if (p.matcher(line).find()) {
            OneLineScore += 500000;
        }
        //活四 80000
        //022220
        p = Pattern.compile("022220");
        if (p.matcher(line).find()) {
            OneLineScore += 80000;
        }
        //冲四 40000
        //22022 20222 22202 122220 022221
        String[] s = {"22022", "20222", "22202", "122220", "022221"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 40000;
            }
            break;
        }
        //活三 20000
        //02220 020220 022020
        s = new String[]{"02220", "020220", "022020"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 20000;
            }
            break;
        }
        //眠三 10000
        //002221 122200 020221 122020 022021 120220 20022 22002 20202 1022201
        s = new String[]{"002221", "020221", "022021", "1022201", "120220", "122020"
                , "122200", "20022", "20202", "22002"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 10000;
            }
            break;
        }
        //活二 5000
        //002200 02020 020020
        s = new String[]{"002200", "02020", "020020"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 5000;
            }
            break;
        }
        //眠二 2500
        //000221 122000 002021 120200 020021 120020 20002
        s = new String[]{"000221", "122000", "002021", "120200", "020021", "120020"
                , "20002"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 2500;
            }
            break;
        }
        //初始2子 500
        //21 12
        s = new String[]{"21", "12"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 500;
            }
            break;
        }

        //对敌人
        //300000分
        //211112
        p = Pattern.compile("211112");
        if (p.matcher(line).find()) {
            OneLineScore += 300000;
        }
        //300000分
        //011112 211110 11211 12111 11121 011112 211110 1211 1121
        s = new String[]{"011112", "211110", "11211", "1211", "1121", "011112"
                , "211110", "012110", "011210"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 300000;
            }
            break;
        }
        //300000分
        //如果有 21110 01112 011210 012110

        s = new String[]{"21110", "01112", "011210", "012110"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 300000;
            }
            break;
        }

        return OneLineScore;
    }

    /**
     * 黑色得分函数，对传入的字符串进行正则匹配返回得分
     *
     * @param line
     * @return 该行字符串得分
     */
    public int getScoreBlack(StringBuffer line) {
        int OneLineScore = 0;
        Pattern p;

        //对自己
        //连五 500000分
        //11111
        p = Pattern.compile("11111");
        if (p.matcher(line).find()) {
            OneLineScore += 500000;
        }
        //活四 80000
        //011110
        p = Pattern.compile("011110");
        if (p.matcher(line).find()) {
            OneLineScore += 80000;
        }
        //冲四 40000
        //11011 10111 11101 211110 011112
        String[] s = {"11011", "10111", "11101", "211110", "011112"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 40000;
            }
            break;
        }
        //活三 20000
        //01110 010110 011010
        s = new String[]{"01110", "010110", "011010"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 20000;
            }
            break;
        }
        //眠三 10000
        //001112 211100 010112 211010 011012 210110 10011 11001 10101 2011102
        s = new String[]{"001112", "010112", "011012", "2011102", "210110",
                "211010", "211100", "10011", "10101", "11001"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 10000;
            }
            break;
        }
        //活二 5000
        //001100 01010 010010
        s = new String[]{"001100", "01010", "010010"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 5000;
            }
            break;
        }
        //眠二 2500
        //000112 211000 001012 210100 010012 210010 10001
        s = new String[]{"000112", "211000", "001012", "210100", "010012",
                "210010", "10001"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 2500;
            }
            break;
        }
        //初始2子 500
        //12 21
        s = new String[]{"12", "21"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 500;
            }
            break;
        }

        //对敌人
        //300000分
        //122221
        p = Pattern.compile("122221");
        if (p.matcher(line).find()) {
            OneLineScore += 300000;
        }
        //200000分
        //022221 122220 22122 21222 22212 022221 122220 021220 022120
        s = new String[]{"022221", "122220", "22122", "21222", "22212",
                "022221", "122220", "021220", "022120"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 200000;
            }
            break;
        }
        //150000分
        //如果有 12220 02221 022120 021220

        s = new String[]{"12220", "02221", "022120", "021220"};
        for (int k = 0; k < s.length; k++) {
            p = Pattern.compile(s[k]);
            if (p.matcher(line).find()) {
                OneLineScore += 150000;
            }
            break;
        }

        return OneLineScore;
    }

    /**
     * 判断哪方胜利
     * @param i
     * @param j
     * @return 1为黑色胜利，2为白色胜利，0为继续
     */
    public int WinOrLose(int i, int j) {
        //正则表达式匹配连续5个1或2
        Pattern p = Pattern.compile("([1,2])\\1{4}");
        //方向数组       右上     右下   列    行
        int[][] dir = {{1, -1}, {1, 1}, {1, 0}, {0, 1}};
        for (int o = 0; o <= 3; o++) {
            //指定方向
            int dirx = dir[o][0];
            int diry = dir[o][1];
            //获得被判断的行
            StringBuffer s1 = getLine(dirx, diry, i, j, 4);
            if (p.matcher(s1).find()) {
                return getE(i, j);
            }
        }
        return 0;
    }


    /**
     * 返回落子后指定方向的字符串
     *
     * @param dirx 行方向
     * @param diry 列方向
     * @param i    落子的行
     * @param j    落子的列
     * @param len  读取的添加长度，最终长度为2*len+1
     * @return 返回落子后指定方向的字符串
     */
    public StringBuffer getLine(int dirx, int diry, int i, int j, int len) {
        StringBuffer s = new StringBuffer();
        //加入落子的位置
        s.append(getE(i, j));
        for (int k = 1; k <= len; k++) {
            int ii = i + k * dirx;
            int jj = j + k * diry;
            //下标越界不加入
            if (ii < 1 || ii > 15 || jj < 1 || jj > 15) {
                break;
            }
            s.append(getE(ii, jj));
        }
        //比如判断第4种行插入后的棋子为 黑黑黑白白，那么需要反向后接着插入
        s.reverse();
        for (int k = 1; k <= len; k++) {
            int ii = i - k * dirx;
            int jj = j - k * diry;
            //下标越界不加入
            if (ii < 1 || ii > 15 || jj < 1 || jj > 15) {
                break;
            }
            s.append(getE(ii, jj));
        }
        return s;
    }
    /**
     * 下棋,注意防止越界
     *
     * @param i 行标
     * @param j 列标
     * @return 成功返回真，失败返回假
     */
    public void setcanput(boolean t) {
    	canput=t;
    }
    public boolean putChess(int i, int j) {
    	if(!canput)return false;
        if (i > 15 || i < 1) {
            return false;
        }
        if (j > 15 || j < 1) {
            return false;
        }
        if (getE(i, j) == 0) {
            if (black) {
                put.start(false);
                Edge[i][j] =1;
                rowlist.add(i);
                collist.add(j);
                frontrow++;
       		    frontcol++;
                black = false;

            } else {
            	put.start(false);
                Edge[i][j] = 2;
                rowlist.add(i);
                collist.add(j);
                frontrow++;
       		    frontcol++;
                black = true;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean isnull() {
    	if(frontrow==-1)return true;
    	return false;
    }
    public int getE(int i, int j) {
        return Edge[i][j];
    }
	public void replay() {
		for(int i=1;i<=15;i++) {
			for(int j=1;j<=15;j++) {
				Edge[i][j]=0;
			}
		}
		rowlist.clear();
		collist.clear();
		frontcol=-1;
		frontrow=-1;
		setcanput(true);
	}
}
