package generator;

import java.util.Random;

public class PrimaryOpe{

    private ProblemCreator creator;

    public String getRes(){
        creator=new ProblemCreator();
        Random rand=new Random();
        int operators=rand.nextInt(4)+1;
        int pairs=rand.nextInt(operators);
        return creator.problmeproduce(pairs,operators).getNum();
    }

}
