package agh.oop.proj;

import java.util.Random;

//mutacja zmienia gen na dowolny inny gen
public class FullRandomGens implements IGenom{
    private final Random randomMachine = new Random();
    private final int numberOfDirection = 7;
    @Override
    public int[] genomMutation(int[] genotype) {
        int N = genotype.length;
        int howmany = randomMachine.nextInt(N+1);//losuje ile zmieniam
        for(int i = 0; i< howmany;i++){
            int whichGenom = randomMachine.nextInt(N);//losuje który zmieniam
            genotype[whichGenom] = randomMachine.nextInt(numberOfDirection+1);//zmieniam wylsowany
        }
        return genotype;
    }
}
