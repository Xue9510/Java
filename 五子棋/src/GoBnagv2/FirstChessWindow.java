package GoBnagv2;

import javax.swing.*;
import java.awt.*;

public class FirstChessWindow extends JFrame{

    private final FirstChessWindowListener gl=new FirstChessWindowListener() ;
    private final String[] butname = { "黑棋", "白棋"};
    public void initFirstChessWindow()  {
        gl.setFirstui(this);
        JFrame firstchess=new JFrame();//新的窗体
        firstchess.setTitle("选择先手使用什么颜色的棋子");//窗体title
        firstchess.setVisible(true);
        FlowLayout flow=new FlowLayout();
        firstchess.setLayout(flow);
        firstchess.setLocationRelativeTo(null);
        firstchess.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        firstchess.setSize(300, 150);

        for (String s : butname) {
            JButton but = new JButton();
            but.setText(s);
            but.setPreferredSize(new Dimension(80, 80));
            but.addActionListener(gl);
            firstchess.add(but);
        }
        this.addMouseListener(gl);

}}
