package agh.oop.proj;

public class World {
    public static void main(String[] args) {
        String[] config = {"20", "10", "4", "1", "1", "5", "10", "5", "1", "1", "2", "3", "craziness", "random", "earth", "corpses"};

        try {
            Settings settings = new Settings(config);
            SimulationEngine engine = new SimulationEngine(settings);
            engine.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
