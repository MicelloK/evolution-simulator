package agh.oop.proj;

import java.util.Random;

public class LittleCrazinessMove implements IMove{

    private Random random = new Random();

    @Override
    public void moving(Animal animal) {
        updateActiveGen(animal);
        int numberdirection = animal.getGenotype()[animal.activeGenom];
        for(int i = 0;i <= numberdirection;i++){
            animal.setOrientation(animal.getOrientation().next());
        }
        Vector2d oldPosition = animal.getPosition();
        animal.setPosition(oldPosition.add(animal.getOrientation().toUnitVector()));
    }

    public void updateActiveGen(Animal animal) {
        // w 20% przypadków aktywujemy losowy gen
        if (random.nextInt(100) >= 80) {
            animal.activeGenom = random.nextInt(animal.getGenotype().length);
        }else{
            animal.activeGenom = (animal.activeGenom + 1) % animal.getGenotype().length;
        }
    }
}
