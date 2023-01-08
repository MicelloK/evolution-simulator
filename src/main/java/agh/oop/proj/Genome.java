package agh.oop.proj;

import java.util.Arrays;
import java.util.Random;

public class Genome {
    private final int[] animalGenotype;
    private final Random random = new Random();
    private int activeGenomeIdx;

    public Genome(Settings settings) {
        int genomeLength = settings.getGenLength();
        animalGenotype = new int[genomeLength];
        for (int i = 0; i < genomeLength; i++) {
            animalGenotype[i] = random.nextInt(7) + 1;
        }
        activeGenomeIdx = random.nextInt(genomeLength);
    }

    public Genome(Animal parentOne, Animal parentTwo, Settings settings) {
        int side = (random.nextInt() % 2); //0 - left, 1 - right

        Animal moreDominant = parentTwo;
        Animal lessDominant = parentOne;

        if (parentOne.getEnergy() > parentTwo.getEnergy()) {
            moreDominant = parentOne;
            lessDominant = parentTwo;
        }

        int cut = moreDominant.getEnergy() / (moreDominant.getEnergy() + lessDominant.getEnergy()) * moreDominant.getGenotype().length;

        int[] firstPiece, secondPiece;
        int n = moreDominant.getGenotype().length;

        if (side == 0) {
            firstPiece = Arrays.copyOfRange(moreDominant.getGenotype(), 0, cut);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), cut, n);
        } else {
            firstPiece = Arrays.copyOfRange(moreDominant.getGenotype(), n - cut, n);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), 0, n - cut);
        }

        int[] res = Arrays.copyOf(secondPiece, n);
        System.arraycopy(firstPiece, 0, res, secondPiece.length, firstPiece.length);
        settings.getMutationVariant().genomeMutation(res);
        animalGenotype = res;
    }

    public int[] getAnimalGenotype() {
        return animalGenotype;
    }

    public int getActiveGenomeAnimal() {
        return animalGenotype[activeGenomeIdx];
    }

    public void setActiveGenome(int activeGenome) {
        activeGenomeIdx = activeGenome;
    }

    public int getActiveGenomeIdx() {
        return activeGenomeIdx;
    }
}
