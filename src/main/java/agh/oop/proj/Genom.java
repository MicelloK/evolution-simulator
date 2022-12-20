package agh.oop.proj;

import java.util.Arrays;
import java.util.Random;

public class Genom{
    private final int[] animalGenotyp;

    Random random = new Random();

    private int activeGenom;

    private final Settings settings;
    // Constructors
    public Genom(Settings settings) {
        this.settings = settings;
        int genomLength = settings.getGenLength();
        this.animalGenotyp = new int[genomLength];
        for (int i = 0; i < genomLength; i++) {
            animalGenotyp[i] = random.nextInt(7) + 1;
        }
        this.activeGenom = random.nextInt(genomLength);
    }

    public Genom(Animal parentOne, Animal parentTwo, Settings settings) {
        this.settings = settings;
        int bornEnergy = settings.getReproductionEnergy();
        int whichSide = (random.nextInt() % 2); //0 - left, 1 - right

        Animal dominant = parentTwo;
        Animal lessDominant = parentOne;
        // wybranie które ma więcej energi
        if (parentOne.getEnergy() > parentTwo.getEnergy()) {
            dominant = parentOne;
            lessDominant = parentTwo;
        }

        // znalezienie ilość genów jednego rodzica i drugiego rodzica
        int cut = dominant.getEnergy()/ (dominant.getEnergy() + lessDominant.getEnergy()) * dominant.getGenotype().length;

        // dwie połówki genów
        int[] firstPiece, secondPiece;
        int n = dominant.getGenotype().length;//długość genotypu
        // wybieramy i łączymy genomy
        if (whichSide == 0) {
            firstPiece = Arrays.copyOfRange(dominant.getGenotype(), 0, cut);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), cut, n);
        } else {
            firstPiece = Arrays.copyOfRange(dominant.getGenotype(), n - cut, n);
            secondPiece = Arrays.copyOfRange(lessDominant.getGenotype(), 0, n - cut);
        }

        // połączenie i mutacja
        int[] res = Arrays.copyOf(secondPiece, n);
        System.arraycopy(firstPiece, 0, res, secondPiece.length, firstPiece.length);
        settings.getMutationVariant().genomMutation(res);
        this.animalGenotyp = res;
    }

    // Getters
    public int[] getAnimalGenotyp() {
        return this.animalGenotyp;
    }

    public int getActiveGenomAnimal(){
        return this.activeGenom;
    }

    public int setActiveGenom(int activeGenom){
        return this.activeGenom = activeGenom;
    }
    // toString
    @Override
    public String toString() {
        return Arrays.toString(this.animalGenotyp);
    }
}
