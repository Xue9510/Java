package GoBnagv2;
import javax.swing.*;
import java.awt.event.*;

public class TextResponse implements FocusListener {
    JTextField username;
    public void setText(JTextField name){
        this.username=name;
    }
    public void focusGained(FocusEvent e) {
        if (username.getText().equals("") |username.getText().equals("请输入账号")){
            username.setText("");
        }
    }
    public void focusLost(FocusEvent e) {
        if (username.getText().equals("")){
            username.setText("请输入账号");
        }

    }
}


