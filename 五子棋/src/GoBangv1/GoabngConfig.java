package GoBangv1;

/**
 * GobangConfig 接口，设置了棋盘初始位置，棋盘行距列间距，行列值，棋子大小等信息
 *
 * */



public interface GoabngConfig {
    /**
     * 棋盘行间距与列间距， 也是旗子的直径
     */
    int SIZE=50;
    /**
     * 棋盘左上角坐标的x值
     */
    int X=100;
    /**
     * 棋盘左上角坐标的y值
     */
    int Y=100;
    /**
     * 棋盘的行列值
     */
    int ROW=15;
    int COLUM=15;


}
