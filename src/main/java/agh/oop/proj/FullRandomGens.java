package agh.oop.proj;

import java.util.Random;

public class FullRandomGens implements IGenome {
    private final Random randomMachine = new Random();
    private final int minimum;
    private final int maximum;

    public FullRandomGens(int maximum, int minimum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public void genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(minimum + maximum) - minimum;
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N);
            genotype[whichGenome] = randomMachine.nextInt(8);
        }
    }
}
