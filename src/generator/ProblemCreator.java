package generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class ProblemCreator {
    private String num = "";
    private Random random = new Random();

    public String getNum() {
        return num;
    }

    public void setNum(String temp) {
        num = temp;
    }

    public String operatorCreate() {
        String[] operator = {"+", "-", "×", "÷"};
        return operator[random.nextInt(4)];
    }

    //对于有意义括号生成的辅助函数
    public ArrayList<Integer> separateEstimate(Stack<Integer> stacktmp) {
        ArrayList<Integer> res = new ArrayList<>();
        int sum = 0;
        Stack<Integer> stack = (Stack<Integer>) stacktmp.clone();
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            for (int i = tmp; i > 0; i--) {
                if (i == tmp) {
                    sum++;
                    if (stack.isEmpty() && i == 1) res.add(sum);
                } else if (i < tmp) {
                    if (sum > 0) res.add(sum);
                    sum = 0;
                    if (i == 1) {
                        sum++;
                        if (stack.isEmpty()) res.add(sum);
                    } else res.add(1);
                }
            }
        }
        return res;
    }

    //关于有有意义的括号生成函数  逻辑内在很复杂
    public ProblemCreator problmeproduce(int pairs, int operators) {
        int[] maxPair=new int[operators];
        maxPair[0]=operators-1;
        if(operators>=2)    maxPair[1]=operators-1;
        for(int i=2;i<operators;i++) {
            maxPair[i]=maxPair[i-1]-1;
        }
        int index=0;
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        //对操作数 的考量
        while (operators > 0) {
            String num1 = "";
            operators--;
            boolean flag=true;
            if (operators > list.size() ) {
                int numOfLeftBracket;
                numOfLeftBracket = Math.abs(random.nextInt()) % (pairs + 1);   //剩余操作数和已使用括号 限制了 之后能使用的括号数
                if(numOfLeftBracket>0)  stack.push(numOfLeftBracket);
                ArrayList<Integer> tmp= separateEstimate(stack);
                String brackets = "";
                if(tmp.size()<=operators){
                    list=tmp;
                    for (int i = 0; i < numOfLeftBracket; i++) {
                        brackets += "(";
                        pairs--;                                           //生成左括号
                    }
                    num1 = brackets + String.valueOf(random.nextInt(100) + 1);
                    if(list.size()==operators) flag=false;
                }
                num1 = brackets + String.valueOf(random.nextInt(100) + 1);
            }
            if(flag && operators <= list.size()){
                int numOfRightBracket = 0;
                if(list.size()>0) numOfRightBracket = list.remove(0);
                String brackets = "";
                for (int i = 0; i < numOfRightBracket; i++) {               //右括号的补足
                    brackets += ")";
                }
                num1 = String.valueOf(random.nextInt(100) + 1) + brackets;
            }
            pairs=pairs < maxPair[index] ? pairs : maxPair[index];
            index++;
            this.setNum(this.getNum() + num1 + operatorCreate());
        }
        this.setNum(this.getNum() + String.valueOf(random.nextInt(100)+1) + "=");
        return this;
    }
}





