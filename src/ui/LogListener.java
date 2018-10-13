package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


/*
* 这是对登陆窗口的监听和相应，逻辑较为简单，只有几个事件
* */
public class LogListener implements ActionListener {
    private HashMap<String,String> nameList;
    private JComboBox<String> combo_gradeSel;
    private JTextField user;
    private JPasswordField passwordField;
    private JFrame frame;

    public LogListener(JTextField user,JPasswordField passwordField,JFrame frame){
        init();
        this.user=user;
        this.passwordField=passwordField;
        this.frame=frame;
    }

    public void init(){
        nameList=new HashMap<String,String>();
        nameList.put("张三1","小学");
        nameList.put("张三2","小学");
        nameList.put("张三3","小学");
        nameList.put("李四1","初中");
        nameList.put("李四2","初中");
        nameList.put("李四3","初中");
        nameList.put("王五1","高中");
        nameList.put("王五2","高中");
        nameList.put("王五3","高中");
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("登陆")){
            String tmp=new String(passwordField.getPassword());
            if(nameList.get(user.getText())!=null && tmp.equals("123")){
                JOptionPane.showMessageDialog(null,"登陆成功,准备生成"+nameList.get(user.getText())+"题目");
                ProcessWindow win=new ProcessWindow(nameList.get(user.getText()),user.getText());
                win.init();
                frame.dispose();
            }
            else if(nameList.get(user.getText())==null){
                JOptionPane.showMessageDialog(null,"账户不存在！");
            }
            else if(!tmp.equals("123")){
                JOptionPane.showMessageDialog(null,"密码错误！");
            }

        }
        else if(e.getActionCommand().equals("清空")){
            user.setText("");
            passwordField.setText("");
        }
    }
}
