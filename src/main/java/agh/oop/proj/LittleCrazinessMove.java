package agh.oop.proj;

import java.util.Random;

public class LittleCrazinessMove implements IMove {
    private final Random random = new Random();

    @Override
    public void moving(Animal animal) {
        updateActiveGen(animal);
        animal.changerPosition();
    }

    private void updateActiveGen(Animal animal) {
        if (random.nextInt(100) >= 80) {
            animal.setActiveGenomeIdx(random.nextInt(animal.getGenotype().length));
        } else {
            animal.setActiveGenomeIdx((animal.getActiveGenomeIdx() + 1) % animal.getGenotype().length);
        }
    }
}
