package ui;

import generator.HighOpe;
import generator.MiddleOpe;
import generator.PrimaryOpe;
import generator.ProblemCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/*
* 事件监听类，负责对生成题目窗口的鼠标键盘等事件的监听和响应
* */
public class WinListener implements ActionListener {
    private JFrame frame;
    private JButton loginButton;
    private JButton switchButton;
    private JLabel label_state;
    private JTextField user;
    private ProblemCreator creator;
    private String username;

    public WinListener(JButton loginButton,JButton switchButton,JLabel label_state,JTextField user,String username,JFrame frame){
        this.frame=frame;
        this.loginButton=loginButton;
        this.switchButton=switchButton;
        this.label_state=label_state;
        this.user=user;
        this.username=username;
    }


    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("生成题目")){
        	int problemNum=Integer.parseInt(user.getText());

            //题目数量输入判定
        	if(problemNum>30 || problemNum<10) {
                JOptionPane.showMessageDialog(null,"请输入10-30间的题目数量");
                user.setText("");
        	}else{
        	     Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
                String selstate=label_state.getText();
                String dirPath=this.username+"\\";

                //创建文件与输入题目内容
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(("problems\\"+dirPath+dateFormat.format(date)+".txt")), true))){
                    Random rand=new Random();
                    for(int i=0;i<problemNum;i++){

                        //根据对应的类型生成对应题目
                        if(selstate.equals("小学")){
                            PrimaryOpe pri=new PrimaryOpe();
                            writer.write((i+1)+"  :   "+pri.getRes()+"\r\n");
                            writer.write("\r\n");
                        }else if(selstate.equals("初中")){
                            MiddleOpe mid=new MiddleOpe();
                            writer.write((i+1)+"  :   "+mid.getRes()+"\r\n");
                            writer.write("\r\n");
                        }
                        else if(selstate.equals("高中")){
                            HighOpe high=new HighOpe();
                            writer.write((i+1)+"  :   "+high.getRes()+"\r\n");
                            writer.write("\r\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null,"题目生成成功 ^ ^");
                }catch(Exception m){
                    JOptionPane.showMessageDialog(null,"程序崩溃，请尝试重新启动! ^ ^");
                }
            }
        }

        //账号切换按钮
        else if(e.getActionCommand().equals("切换账号")){
            String[] options={"确定","取消"};
            if(JOptionPane.showOptionDialog(null, "确定要切换账号吗？",
                    "注销",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])==0){
                LogIn logIn=new LogIn();
                logIn.init();
                frame.dispose();
            }
        }
        //下拉框响应
        else if(e.getSource() instanceof JComboBox){
            JComboBox<String> cbItem = (JComboBox<String>) e.getSource();// 获取事件源对象
            label_state.setText(cbItem.getSelectedItem().toString());
        }
    }
}
