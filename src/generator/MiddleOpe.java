package generator;

import java.util.Random;

public class MiddleOpe{
    private String res;
    private Random rand=new Random();
    private PrimaryOpe primaryOpe=new PrimaryOpe();

    //初中题目类型实在小学题目上加成。
    public String getRes(){
        String tmp=primaryOpe.getRes();
        tmp=tmp.substring(0,tmp.length()-1);
        int sum=0;
        String res="";
        while(!tmp.equals("")){
            int flag=0;
            //找到合适的位置  -》  数字 或者 ）
            for(int i=0;i<tmp.length();i++){
                if(((tmp.charAt(i)<='9')&&(tmp.charAt(i)>='0')) || tmp.charAt(i)==')'){
                    if(i==tmp.length()-1 && sum==0){
                        res+=tmp.substring(0,i+1)+addPoweer();
                        flag=i;
                        break;
                    }
                    else{
                        if(!((tmp.charAt(i+1)<='9')&&(tmp.charAt(i+1)>='0'))){
                            res+=tmp.substring(0,i+1)+addPoweer();
                            flag=i;
                            break;
                        }
                    }
                }
            }
            tmp=tmp.substring(flag+1,tmp.length());
        }
        return res+"=";
    }

    //添加幂指数
    public String addPoweer(){
        String add;
        int dice=rand.nextInt(2);
        if(dice==0){
            add="^0.5";
        }else{
            add="^2";
        }
        return add;
    }


}
