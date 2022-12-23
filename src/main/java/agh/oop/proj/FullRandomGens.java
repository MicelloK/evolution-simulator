package agh.oop.proj;

import java.util.Random;

public class FullRandomGens implements IGenome {
    private final Random randomMachine = new Random();

    @Override
    public int[] genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(N + 1);
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N);
            genotype[whichGenome] = randomMachine.nextInt(8);
        }
        return genotype;
    }
}
