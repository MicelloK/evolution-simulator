package agh.oop.proj;

import java.util.Random;

public class LittleCorrectionGens implements IGenome {
    private final int[] CORRECT = {-1, 1};
    private final Random randomMachine = new Random();

    @Override
    public void genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(N + 1);
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N);
            genotype[whichGenome] = genotype[whichGenome] + CORRECT[randomMachine.nextInt(CORRECT.length)];

            if (genotype[whichGenome] < 0) {
                genotype[whichGenome] = 7;
            }
            if (genotype[whichGenome] > 7) {
                genotype[whichGenome] = 0;
            }
        }
    }
}
