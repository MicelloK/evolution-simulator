package agh.oop.proj;

public class World {
    public static void main(String[] args) {
        String[] config = {"10", "6", "4", "4", "1", "1", "4", "5", "1", "1", "2", "3", "predestination", "correction", "earth", "equators"};

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
