package agh.oop.proj;

import java.util.Random;

public class LittleCorrection implements IGenom{
    private final int[] correct = {-1,1};
    private final Random randomMachine = new Random();
    @Override
    public int[] genomMutation(int[] genotype) {
        int N = genotype.length;
        int howmany = randomMachine.nextInt(N+1);//losuje ile zmieniam
        for(int i = 0; i < howmany;i++){
            int whichGenom = randomMachine.nextInt(N);//losuje który zmieniam
            genotype[whichGenom] =  genotype[whichGenom] + correct[randomMachine.nextInt(correct.length)];//zmieniam wylsowany
        }
        return genotype;
    }
}
