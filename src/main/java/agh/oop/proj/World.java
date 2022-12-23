package agh.oop.proj;

public class World {
    public static void main(String[] args) {
        String[] config = {"3", "3", "4", "4", "3", "1", "5", "2", "1", "1", "2", "3", "predestination", "correction", "earth", "corpses"};

//        AbstractWorldMap map = new CorpsesMap(3, 3, new EarthMoveAllowed(), 4);
//        System.out.println(map.newPosition(new Vector2d(0, 0) ,new Vector2d(-1, -1)));
        try {
            Settings settings = new Settings(config);
            SimulationEngine engine = new SimulationEngine(settings);
            engine.run();
        } catch (Exception e) {
            System.out.println("wrong config");
            System.out.println(e.getMessage());
        }
    }
}
