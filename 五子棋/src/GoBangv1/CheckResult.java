package GoBangv1;

/**
 * 每当下完一步棋以后都来判断是不是有人获胜了
 * 检查横向，纵向，两个斜向是不是有连续的五个一样的棋子
 * 通过传入的棋子location状态array来判断，如果值为-1是白棋，值为1是黑旗，值为0魏没有棋子
 * x对应的是矩阵的行值，y对应的是矩阵的列值
 */
public class CheckResult {

    /**
     * 判断当前坐标传入竖列有没有5个连在一起的，因为循环内算上了当前的这个棋子，所以count从0开始（i =x，算上当前的棋子）
     * @param x 传入的坐标的x
     * @param y 传入的坐标的y
     * @param array 传入的判断矩阵，判断是否有5个一样的相连
     * @return return 如果有5子相连则返回true
     *
     */

    public static boolean checkColum(int x, int y, int[][] array) {
        int count = 0;
        for (int i = x+1; i < array.length; i++) {
            if (array[x][y] == array[i][y]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = x; i>=0; i--) {
            if (array[x][y] == array[i][y]) {
                count++;
            } else {
                break;
            }
        }
//        System.out.println(count);
        return count >= 5;
    }
    /**
     * 判断当前坐标传入横行有没有5个连在一起的，因为循环内算上了当前的这个棋子，所以count从0开始（i =y，算上当前的棋子）
     * @param x 传入的坐标的x
     * @param y 传入的坐标的y
     * @param array 传入的判断矩阵，判断是否有5个一样的相连
     * @return return 如果有5子相连则返回true
     */
    public static boolean checkRow(int x, int y, int[][] array) {
        int count = 0;
        for (int i = y+1; i < array[0].length; i++) {
            if (array[x][y] == array[x][i]) {
                count++;
            } else {
                break;
            }
        }
        for (int i = y; i>=0; i--) {
            if (array[x][y] == array[x][i]) {
                count++;
            } else {
                break;
            }
        }
//        System.out.println(count);
        return count >= 5;
    }

    /**
     * 判断从左下角到右上角斜着有没有5个连在一起的，因为循环内没有算上当前的这个棋子，所以count从1开始（没有temp=0这个条件）
     * @param x 传入的坐标的x
     * @param y 传入的坐标的y
     * @param array 传入的判断矩阵，判断是否有5个一样的相连
     * @return return 如果有5子相连则返回true
     */
    public static boolean checkleftbevel(int x, int y, int[][] array) {
        int count = 1;
        int temp=1;
        while((x-temp)>=0 && (y+temp)< array[0].length){
            if (array[x][y] == array[x-temp][y+temp]) {
                count++;
                temp++;
            } else {
                break;
            }
        }

        while((x+temp)< array.length && (y-temp)>=0){
            if (array[x][y] == array[x+temp][y-temp]) {
                count++;
                temp++;
            } else {
                break;
            }
        }
//        System.out.println(count);
        return count >= 5;
    }
    /**
     * 判断从左上角到右下角斜着有没有5个连在一起的，因为循环内没有算上当前的这个棋子，所以count从1开始（没有temp=0这个条件）
     * @param x 传入的坐标的x
     * @param y 传入的坐标的y
     * @param array 传入的判断矩阵，判断是否有5个一样的相连
     * @return return 如果有5子相连则返回true
     */
    public static boolean checkrightbevel(int x, int y, int[][] array) {
        int count = 1;
        int temp=1;
        while((x+temp)< array.length && (y+temp)< array[0].length){
            if (array[x][y] == array[x+temp][y+temp]) {
                count++;
                temp++;
            } else {
                break;
            }
        }
        while((x-temp)>=0 && (y-temp)>=0){
            if (array[x][y] == array[x-temp][y-temp]) {
                count++;
                temp++;
            } else {
                break;
            }
        }
//        System.out.println(count);
        return count >= 5;
    }
}
