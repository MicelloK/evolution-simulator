package agh.oop.proj;

import java.util.Random;

public class LittleCrazinessMove implements IMove{

    private final Random random = new Random();

    @Override
    public void moving(Animal animal) {
        updateActiveGen(animal);
        changerposition(animal);
    }

    public void updateActiveGen(Animal animal) {
        // w 20% przypadków aktywujemy losowy gen
        if (random.nextInt(100) >= 80) {
            animal.setActiveGenom(random.nextInt(animal.getGenotype().length));
        }else{
            animal.setActiveGenom((animal.getActiveGenom() + 1) % animal.getGenotype().length);
        }
    }

    private void changerposition(Animal animal) {
        int numberdirection = animal.getGenotype()[animal.getActiveGenom()];
        for(int i = 0;i <= numberdirection;i++){
            animal.setOrientation(animal.getOrientation().next());
        }
        Vector2d oldPosition = animal.getPosition();
        animal.setPosition(oldPosition.add(animal.getOrientation().toUnitVector()));
        animal.positionChanged(oldPosition,animal.getPosition());
    }
}
