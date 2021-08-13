package GoBnagv2;

import java.awt.*;

public class HelpFunction implements GoabngConfig {
    /**
     * 用向上或向下凑整的方法将x,y坐标转化到横线与纵线的交点上，给cheesX和cheesY赋值
     *
     * @param x 当前点击的坐标的x的值
     *          将x和y转换到交点上给cheesX和cheesY赋值
     */
    public static int transformLocationX(int x) {
        int tempx = x % SIZE;

        if (tempx < (SIZE / 2)) {
            return (x - tempx);
        } else {
            return ( x + (SIZE - tempx));
        }

    }
    public static int transformLocationY(int y) {
        int tempy = y % SIZE;
        if (tempy < (SIZE / 2)) {
            return (y - tempy);
        } else {
            return ( y + (SIZE - tempy));
        }

    }
    public static void showblackChexxstep(Graphics g, int step) {
        g.setColor(Color.white);
        g.fillRect(1000, 80, 100, 50);
        g.setColor(Color.black);
        g.drawString("黑棋下了：" + (step / 2 + 1) + "步。", 1000, 100);

    }
    public static void showwhiteChexxstep(Graphics g, int step) {
        g.setColor(Color.white);
        g.fillRect(1000, 180, 100, 50);
        g.setColor(Color.black);
        g.drawString("白棋下了：" + (step / 2 + 1) + "步。", 1000, 200);

    }

}
