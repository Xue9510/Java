package GoBangv1;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * GoBnagv2.GoBangLister 鼠标监听器类，在棋盘界面监听鼠标的移动和监听
 * 属性：1. chessX，chessY 当前下棋落点的坐标，已经转换为只能在交点处的坐标格式
 *      2. Graphics g 绘制棋子的组件，画笔
 *      3. step 用来判断下一步是白旗还是黑旗，如果是偶数画黑色棋子，如果是奇数画白色棋子
 *      4. chessLocationArray 一个二维数组用来存放记录棋子位置信息，初始默认值为0，下白旗改为-1，黑旗改为1，用于后面判断输赢
 *      5. chessinfor 一个QIZI类型的数组，用来存放已经落下的棋子的坐标和颜色信息
 *      6. UIwindow 一个GongbangUI，既当前所在的UI界面的实体，通过setUIwindow来设置，用于后面调用paint方法重绘界面
 *      7. state 设定一个当前界面的state，默认为0，既不可以在棋盘上画棋子，如果state=1，则可以在棋盘上绘画棋子
 *
 * 方法：1. setGraphics(Graphics g) ：给g赋值，给g一个画笔对象
 *      2. setUIwindow(GoBnagv2.GongbangUI UIwindow)，获取当前界面的对象
 *      3. getchessinfor 返回当前棋子的information，传给paint方法用于刷新时重绘
 *      4.transformLocation 用向上或向下凑整的方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
 *      5.checkR 每当落下一颗棋子，去判断当前棋子在四个方向是不是有五个连起来的棋子，如果有则返回true，否则返回false
 *
 * 重写的方法：mousePress，actionPerformed
 *      1.actionPerformed 用于判断所选功能并执行对应的逻辑
 *      2.mousePress：下棋的时候在范围内点击界面会绘制棋子
 *
 *
 */

public class GoBangLister implements MouseListener, MouseMotionListener, GoabngConfig, ActionListener {
    int chessX, chessY;
    Graphics g;
    private int step = 0;
    private final int[][] chessLocationArray = new int[ROW + 1][COLUM + 1];
    private QIZI[] chessinfor = new QIZI[(ROW+1)*(COLUM+1)+1];
    private GongbangUI UIwindow;
    int state=0;


    /**
     * setGraphics(Graphics g) ：给g赋值，给g一个画笔对象
     *
     * @param g 画笔Graphics
     */
    public void setGraphics(Graphics g) {
        this.g = g;
    }

    /**
     * 此方法用于别的类 传过来UIwindow，在同一window下操作
     */
    public void setUIwindow(GongbangUI UIwindow) {
        this.UIwindow = UIwindow;
    }

    /**
     * 此方法用于别的类获取chessLocation以便于重绘
     *
     * @return 返回值为 GoBnagv2.QIZI 数组
     */
    public QIZI[] getchessinfor() {
        return chessinfor;
    }

    /**
     * 用于判断所选功能并执行对应的逻辑
     * @param e 事件e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String operation = e.getActionCommand();
        // 如果点击双人对战，将state状态变为1，此时可以在棋盘上绘制
        if (operation.equals("双人对战")) {
            state=1;

        }// 如果点击重新开始，将state状态变为1，将棋子infor数组和棋子location矩阵重置，将step重置为0
        else if (operation.equals("重新开始")) {
            state=1;
            System.out.println("重新开始");
            //重置矩阵
            for (int i = 0; i < chessLocationArray.length; i++) {
                for (int j = 0; j < chessLocationArray[0].length; j++) {
                    chessLocationArray[i][j] = 0;
                }
            }
            // 重置chessinfor数组
            Arrays.fill(chessinfor, null);
            step=0;
            g.setColor(Color.BLACK);
            UIwindow.paint(g);

        } // 如果点击悔棋，判断chessinfor数组，找到最后一个既是上一步走的棋子，将上一步棋子的坐标找出来，还原在数组中并重置为0，将上一步的棋子坐标和颜色信息清空
        else if (operation.equals("悔棋")) {
            if (state == 1) {
                // 如果棋盘上没有棋子，不能进行悔棋
                if (chessinfor[0] == null) {
                    resultWindow.showresult("没有棋子不能悔棋！");
                } else {
                    int i = 0;
                    //通过while循环找到最后一步棋的棋子信息
                    while ( i<chessinfor.length && chessinfor[i] != null) {
                        i++;
                    }
                    //将棋子坐标转换回去，然后对应在矩阵中，重置上一步棋子在矩阵中为0
                    chessLocationArray[((chessinfor[i - 1].y + SIZE / 2) / SIZE) - 2][((chessinfor[i - 1].x + SIZE / 2) / SIZE) - 2] = 0;
                    //棋子坐标信息重置
                    chessinfor[i - 1] = null;
                    // step也应该减去1
                    step--;
                    // 然后重绘棋盘和棋子，注意设置画笔颜色为black，不然上一步棋是白色的话画笔颜色会是白色，会将棋盘的棋格线画成白色
                    g.setColor(Color.BLACK);
                    UIwindow.paint(g);
                }


            } else if (operation.equals("人机对战")) {

            }
        }

    }
    /**
     * mousePress 方法重写
     * 每当点击时，绘制一个棋子
     * tempx和tempy为当前点击坐标对SIZE取余数，如果余数<(SIZE/2),则向下取SIZE得倍数，如果余数大于SIZE/2,则向上找SIZE的倍数
     * 属性：1. x：当点点击坐标的x值
     *      2. y: 当前点击坐标的y值
     *      3. tempx 将y转换为与数组对应的行值的i （注意点击的时候x和y与二维数组中的行列是相反的，既x对应的是二维数组中的列数，y对应的是二维数组中的行数）
     *      4. tempy 将x转换为与数组对应的列值的j
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int tempx, tempy;
        // 用transformLocation方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
        transformLocation(x, y);
        tempx = chessY / SIZE - 2;
        tempy = chessX / SIZE - 2;

//        System.out.println(tempx+","+tempy);
        //判断是不是在边界内，state是不是1，当前落子是不是已经有棋子了
        if (state==1) {
            if (x > X && x < X + SIZE * ROW && y > Y && y < Y + SIZE * COLUM) {
                // 判断棋子落点没有棋子
                if (chessLocationArray[tempx][tempy] == 0) {
                    // 用step来判断是黑棋还是白棋
                    if (step % 2 == 0) {
                        //设置颜色
                        g.setColor(Color.BLACK);
                        //绘画棋子
                        g.fillOval(chessX - SIZE / 2, chessY - SIZE / 2, SIZE, SIZE);
                        //画完棋子将棋子信息放入棋子数组中
                        QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.BLACK);
                        chessinfor[step] = qizi;
                        // 将棋子状态的二维数组中的值由0变为1，代表当前为黑旗
                        chessLocationArray[tempx][tempy] = 1;
                        //每下完一步棋判断是不是赢了
                        boolean result = checkR(tempx, tempy);
                        //如果赢了返回结果
                        if (result) {
                            resultWindow.showresult("黑棋赢了");
                            state=0;
                        }
                        //白色棋子同上
                    } else {
                        g.setColor(Color.WHITE);
                        chessLocationArray[tempx][tempy] = -1;
                        g.fillOval(chessX - SIZE / 2, chessY - SIZE / 2, SIZE, SIZE);
                        QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.WHITE);
                        chessinfor[step] = qizi;
                        boolean result = checkR(tempx, tempy);
                        if (result) {
                            resultWindow.showresult("白棋赢了");
                            state=0;
                        }
                    }
                    //下完一步棋step数值加1
                    step++;
                }
            }
        }
    }


    /**
     * 用向上或向下凑整的方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
     *
     * @param x 当前点击的坐标的x的值
     * @param y 当前点击的坐标的y的值
     *          将x和y转换到交点上给cheesX和cheesY赋值
     */

    public void transformLocation(int x, int y) {
        int tempx = x % SIZE;
        int tempy = y % SIZE;
        if (tempx < (SIZE / 2)) {
            chessX = x - tempx;
        } else {
            chessX = x + (SIZE - tempx);
        }
        if (tempy < (SIZE / 2)) {
            chessY = y - tempy;
        } else {
            chessY = y + (SIZE - tempy);
        }
    }

    /**
     * 每当落下一颗棋子，去判断当前棋子在四个方向是不是有五个连起来的棋子，如果有则返回true，否则返回false
     *
     * @param tempx 当前棋子落点位置在棋子位置二维矩阵中的对应坐标的x值
     * @param tempy 当前棋子落点位置在棋子位置二维矩阵中的对应坐标的y值
     * @return 返回的结果，如果满足获胜条件则返回true
     */
    public boolean checkR(int tempx, int tempy) {

        return CheckResult.checkRow(tempx, tempy, chessLocationArray) || CheckResult.checkColum(tempx, tempy, chessLocationArray) || CheckResult.checkleftbevel(tempx, tempy, chessLocationArray) || CheckResult.checkrightbevel(tempx, tempy, chessLocationArray);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
