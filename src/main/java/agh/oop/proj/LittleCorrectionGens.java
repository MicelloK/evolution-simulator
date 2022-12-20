package agh.oop.proj;

import java.util.Random;

public class LittleCorrectionGens implements IGenome {
    private final int[] correct = {-1, 1};
    private final Random randomMachine = new Random();

    @Override
    public int[] genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(N + 1); // losuje ile zmieniam
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N); // losuje który zmieniam
            genotype[whichGenome] = genotype[whichGenome] + correct[randomMachine.nextInt(correct.length)]; // zmieniam wylsowany
        }
        return genotype;
    }
}
