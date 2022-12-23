package agh.oop.proj;

import java.util.Random;

public class LittleCorrectionGens implements IGenome {
    private final int[] correct = {-1, 1};
    private final Random randomMachine = new Random();

    @Override
    public int[] genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(N + 1);
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N);
            genotype[whichGenome] = genotype[whichGenome] + correct[randomMachine.nextInt(correct.length)];
        }
        return genotype;
    }
}
