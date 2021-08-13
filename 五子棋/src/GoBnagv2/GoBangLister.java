package GoBnagv2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.util.Arrays;

/**
 * GoBnagv2.GoBangLister 鼠标监听器类，在棋盘界面监听鼠标的移动和监听
 * 属性：1. chessX，chessY 当前下棋落点的坐标，已经转换为只能在交点处的坐标格式
 * 2. Graphics g 绘制棋子的组件，画笔
 * 3. step 用来判断下一步是白旗还是黑旗，如果是偶数画黑色棋子，如果是奇数画白色棋子
 * 4. chessLocationArray 一个二维数组用来存放记录棋子位置信息，初始默认值为0，下白旗改为-1，黑旗改为1，用于后面判断输赢
 * 5. chessinfor 一个QIZI类型的数组，用来存放已经落下的棋子的坐标和颜色信息
 * 6. UIwindow 一个GongbangUI，既当前所在的UI界面的实体，通过setUIwindow来设置，用于后面调用paint方法重绘界面
 * 7. state 设定一个当前界面的state，默认为0，既不可以在棋盘上画棋子，如果state=1，则可以在棋盘上绘画棋子
 * <p>
 * 方法：1. setGraphics(Graphics g) ：给g赋值，给g一个画笔对象
 * 2. setUIwindow(GoBnagv2.GongbangUI UIwindow)，获取当前界面的对象
 * 3. getchessinfor 返回当前棋子的information，传给paint方法用于刷新时重绘
 * 4.transformLocation 用向上或向下凑整的方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
 * 5.checkR 每当落下一颗棋子，去判断当前棋子在四个方向是不是有五个连起来的棋子，如果有则返回true，否则返回false
 * <p>
 * 重写的方法：mousePress，actionPerformed
 * 1.actionPerformed 用于判断所选功能并执行对应的逻辑
 * 2.mousePress：下棋的时候在范围内点击界面会绘制棋子
 */

public class GoBangLister implements MouseListener, MouseMotionListener, GoabngConfig, ActionListener {
    int chessX, chessY;
    Graphics g;
    private int step = 0;
    private final int[][] chessLocationArray = new int[ROW + 1][COLUM + 1];
    private final int[][] savechessLocationArray = new int[ROW + 1][COLUM + 1];
    private int savestep;
    public int firstcolor;
    private final QIZI[] chessinfor = new QIZI[(ROW + 1) * (COLUM + 1) + 1];
    private final QIZI[] savechessinfor = new QIZI[(ROW + 1) * (COLUM + 1) + 1];
    private GongbangUI gongbangUI;
    int state = 0;
    private StartUI startUI;


    public StartUI getStartUI() {
        return startUI;
    }

    public void setStartUI(StartUI startUI) {
        this.startUI = startUI;
    }

    /**
     * setGraphics(Graphics g) ：给g赋值，给g一个画笔对象
     *
     * @param g 画笔Graphics
     */
    public void setGraphics(Graphics g) {
        this.g = g;
    }

    public void setFirstcolor(int firstcolor) {
        this.firstcolor = firstcolor;
    }

    /**
     * 此方法用于别的类 传过来UIwindow，在同一window下操作
     */
    public void setUIwindow(GongbangUI UIwindow) {
        this.gongbangUI = UIwindow;
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
     *
     * @param e 事件e
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        String operation = e.getActionCommand();

        // 如果点击双人对战，将state状态变为1，此时可以在棋盘上绘制
        if (operation.equals("开始游戏")) {
            state = 1;
        }// 如果点击重新开始，将state状态变为1，将棋子infor数组和棋子location矩阵重置，将step重置为0
        else if (operation.equals("重新开始")) {
            state = 1;
            System.out.println("重新开始");
            //重置矩阵
            for (int i = 0; i < chessLocationArray.length; i++) {
                for (int j = 0; j < chessLocationArray[0].length; j++) {
                    chessLocationArray[i][j] = 0;
                }
            }
            // 重置chessinfor数组
            Arrays.fill(chessinfor, null);
            step = 0;
            g.setColor(Color.BLACK);
            gongbangUI.paint(g);

        } // 如果点击悔棋，判断chessinfor数组，找到最后一个既是上一步走的棋子，将上一步棋子的坐标找出来，还原在数组中并重置为0，将上一步的棋子坐标和颜色信息清空
        else if (operation.equals("悔棋")) {
            if (state == 1) {
                // 如果棋盘上没有棋子，不能进行悔棋
                if (chessinfor[0] == null) {
                    resultWindow.showresult("没有棋子不能悔棋！");
                } else {
                    int i = 0;
                    //通过while循环找到最后一步棋的棋子信息
                    while (i < chessinfor.length && chessinfor[i] != null) {
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
                    gongbangUI.paint(g);
                    System.out.println(step);
                    if (firstcolor == 0) {

                        if (step % 2 == 0) {
                            HelpFunction.showblackChexxstep(g, step-2);
                            HelpFunction.showwhiteChexxstep(g, step-2);

                        } else {
                            HelpFunction.showblackChexxstep(g, step);
                            HelpFunction.showwhiteChexxstep(g, step-2);
                        }
                    } else {
                        if (step % 2 == 0) {
                            HelpFunction.showblackChexxstep(g, step-2);
                            HelpFunction.showwhiteChexxstep(g, step-2);
                        } else {
                            HelpFunction.showblackChexxstep(g, step-2);
                            HelpFunction.showwhiteChexxstep(g, step);

                        }
                    }

                }
            }
        } else if (operation.equals("返回主菜单")) {
            StartUI startui = new StartUI();
            startui.initStratUI();
            gongbangUI.setVisible(false);
        } else if (operation.equals("存档")) {

            System.arraycopy(chessinfor, 0, savechessinfor, 0, (ROW + 1) * (COLUM + 1) + 1);
            for (int i = 0; i < chessLocationArray.length; i++) {
                System.arraycopy(chessLocationArray[i], 0, savechessLocationArray[i], 0, chessLocationArray[i].length);
            }
            savestep = step;

        } else if (operation.equals("读档")) {
            state = 1;
            if (savechessinfor == null) {
                resultWindow.showresult("没有存档！");
            } else {
                for (int i = 0; i < savechessLocationArray.length; i++) {
                    System.arraycopy(savechessLocationArray[i], 0, chessLocationArray[i], 0, chessLocationArray[i].length);
                }
                step = savestep;
                System.arraycopy(savechessinfor, 0, chessinfor, 0, (ROW + 1) * (COLUM + 1) + 1);
                g.setColor(Color.BLACK);
                gongbangUI.paint(g);
                if (firstcolor == 0) {

                    if (step % 2 == 0) {
                        HelpFunction.showblackChexxstep(g, step-2);
                        HelpFunction.showwhiteChexxstep(g, step-2);

                    } else {
                        HelpFunction.showblackChexxstep(g, step);
                        HelpFunction.showwhiteChexxstep(g, step-2);
                    }
                } else {
                    if (step % 2 == 0) {
                        HelpFunction.showblackChexxstep(g, step-2);
                        HelpFunction.showwhiteChexxstep(g, step-2);
                    } else {
                        HelpFunction.showblackChexxstep(g, step-2);
                        HelpFunction.showwhiteChexxstep(g, step);

                    }
                }
            }
        }

    }

    /**
     * mousePress 方法重写
     * 每当点击时，绘制一个棋子
     * tempx和tempy为当前点击坐标对SIZE取余数，如果余数<(SIZE/2),则向下取SIZE得倍数，如果余数大于SIZE/2,则向上找SIZE的倍数
     * 属性：1. x：当点点击坐标的x值
     * 2. y: 当前点击坐标的y值
     * 3. tempx 将y转换为与数组对应的行值的i （注意点击的时候x和y与二维数组中的行列是相反的，既x对应的是二维数组中的列数，y对应的是二维数组中的行数）
     * 4. tempy 将x转换为与数组对应的列值的j
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x > X && x < X + SIZE * ROW && y > Y && y < Y + SIZE * COLUM) {
            int tempx, tempy;
            // 用transformLocation方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
            chessX = HelpFunction.transformLocationX(x);
            chessY = HelpFunction.transformLocationY(y);
            tempx = chessY / SIZE - 2;
            tempy = chessX / SIZE - 2;

//        System.out.println(tempx+","+tempy);
            //判断是不是在边界内，state是不是1，当前落子是不是已经有棋子了
            if (state == 1) {

                // 判断棋子落点没有棋子
                if (chessLocationArray[tempx][tempy] == 0) {
                    // 用step来判断是黑棋还是白棋
                    if (firstcolor == 0) {
                        if (step % 2 == 0) {

                            //将棋子放入棋子数组中然后绘画棋子
                            QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.BLACK);
                            qizi.drawChess(g);
                            HelpFunction.showblackChexxstep(g, step);
                            chessinfor[step] = qizi;
                            // 将棋子状态的二维数组中的值由0变为1，代表当前为黑旗
                            chessLocationArray[tempx][tempy] = 1;
                            //每下完一步棋判断是不是赢了
                            boolean result = CheckResult.checkR(tempx, tempy, chessLocationArray);
                            //如果赢了返回结果
                            if (result) {
                                resultWindow.showresult("黑棋赢了");
                                state = 0;
                            }
                            //白色棋子同上
                        } else {
                            chessLocationArray[tempx][tempy] = -1;
                            QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.WHITE);
                            qizi.drawChess(g);
                            chessinfor[step] = qizi;
                            HelpFunction.showwhiteChexxstep(g, step);
                            boolean result = CheckResult.checkR(tempx, tempy, chessLocationArray);
                            if (result) {
                                resultWindow.showresult("白棋赢了");
                                state = 0;
                            }
                        }
                    } else {
                        if (step % 2 == 1) {

                            //将棋子放入棋子数组中然后绘画棋子
                            QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.BLACK);
                            qizi.drawChess(g);
                            HelpFunction.showblackChexxstep(g, step);
                            chessinfor[step] = qizi;
                            // 将棋子状态的二维数组中的值由0变为1，代表当前为黑旗
                            chessLocationArray[tempx][tempy] = 1;
                            //每下完一步棋判断是不是赢了
                            boolean result = CheckResult.checkR(tempx, tempy, chessLocationArray);
                            //如果赢了返回结果
                            if (result) {
                                resultWindow.showresult("黑棋赢了");
                                state = 0;
                            }
                            //白色棋子同上
                        } else {
                            chessLocationArray[tempx][tempy] = -1;
                            QIZI qizi = new QIZI(chessX - SIZE / 2, chessY - SIZE / 2, Color.WHITE);
                            qizi.drawChess(g);
                            chessinfor[step] = qizi;
                            HelpFunction.showwhiteChexxstep(g, step);
                            boolean result = CheckResult.checkR(tempx, tempy, chessLocationArray);
                            if (result) {
                                resultWindow.showresult("白棋赢了");
                                state = 0;
                            }
                        }
                    }
                    //下完一步棋step数值加1
                    step++;
                    System.out.println(step);
                }
            }
        }
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
