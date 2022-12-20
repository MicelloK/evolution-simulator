package agh.oop.proj;

import java.util.Random;

//mutacja zmienia gen na dowolny inny gen
public class FullRandomGens implements IGenome {
    private final Random randomMachine = new Random();

    @Override
    public int[] genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(N + 1); //losuje ile zmieniam
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N); //losuje który zmieniam
            genotype[whichGenome] = randomMachine.nextInt(8); //zmieniam wylsowany
        }
        return genotype;
    }
}
