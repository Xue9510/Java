package GoBangv1;

import java.awt.*;

/**
 * 设定一个棋子类，用来存放棋子的信息
 */
public class QIZI {
    /**
     * GoBnagv2.QIZI 实体构造器，传入时需要三个参数x,y,c并设置给QIZI
     * @param x 当前棋子传入的坐标的x值
     * @param y 当前棋子传入的坐标的y值
     * @param c 传入的坐标的颜色信息，当前棋子是白棋还是黑棋
     */
    public QIZI(int x,int y,Color c){
        this.x=x;
        this.y=y;
        this.c=c;
    }
    public int x;
    public int y;
    public Color c;
}
