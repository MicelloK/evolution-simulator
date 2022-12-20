package agh.oop.proj;

import java.util.Random;

public class LittleCrazinessMove implements IMove {
    private final Random random = new Random();

    @Override
    public void moving(Animal animal) {
        updateActiveGen(animal);
        animal.changerPosition();
    }

    public void updateActiveGen(Animal animal) {
        // w 20% przypadków aktywujemy losowy gen
        if (random.nextInt(100) >= 80) {
            animal.setActiveGenome(random.nextInt(animal.getGenotype().length));
        } else {
            animal.setActiveGenome((animal.getActiveGenome() + 1) % animal.getGenotype().length);
        }
    }
}
