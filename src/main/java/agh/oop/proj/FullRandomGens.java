package agh.oop.proj;

import java.util.Random;

public class FullRandomGens implements IGenome {
    private final Random randomMachine = new Random();

    private int maksimum;

    private int minimium;

    public FullRandomGens(int maksimum, int minimium) {
        this.maksimum = maksimum;
        this.minimium = minimium;
    }

    @Override
    public int[] genomeMutation(int[] genotype) {
        int N = genotype.length;
        int howMany = randomMachine.nextInt(maksimum+minimium)-minimium;
        for (int i = 0; i < howMany; i++) {
            int whichGenome = randomMachine.nextInt(N);
            genotype[whichGenome] = randomMachine.nextInt(8);
        }
        return genotype;
    }
}
