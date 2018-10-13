package generator;

import java.util.Random;

public class HighOpe{
    private String res;
    private Random rand=new Random();
    private MiddleOpe midOpe=new MiddleOpe();

    //对于tan cos 和 sin 的插入位置函数合法性判定
    public boolean judge(char a){
        if(a=='+'|| a=='-'||a=='×'||a=='÷'|| a=='(')
            return true;
        return false;
    }

    //同理，高中题目建立在初中题目上生成
    public String getRes(){
        String tmp=midOpe.getRes();
        tmp=tmp.substring(0,tmp.length()-1);
        int sum=0;
        String res="";
        while(!tmp.equals("")){
            int flag=0;
            for(int i=tmp.length()-1;i>=0;i--){
                //合适的位置  -》  数字 和 （
                if(((tmp.charAt(i)<='9')&&(tmp.charAt(i)>'0')) || tmp.charAt(i)=='('){
                    if(i==0 && sum==0){
                        res=addPoweer()+tmp.substring(i,tmp.length())+res;
                        flag=i;
                        break;
                    }
                    else{
                        if(i>=1 && judge(tmp.charAt(i-1))){
                            int dice=rand.nextInt(2);
                            if(dice==0) continue;
                            else{
                                res=addPoweer()+tmp.substring(i,tmp.length())+res;
                                flag=i;
                                break;
                            }
                        }

                    }
                }
            }
            tmp=tmp.substring(0,flag);
        }
        return res+"=";
    }

    //添加符号
    public String addPoweer(){
        String add;
        int dice=rand.nextInt(3);
        if(dice==0) add="sin";
        else if(dice==1)    add="cos";
        else    add="tan";
        return add;
    }

}
