package agh.oop.proj.gui;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Images {
    public Image grassImage;
    public Image Image1;
    public Image Image2;
    public Image Image3;
    public Image Image4;
    public Image Image5;

    public Images() {
        try {
            this.grassImage = new javafx.scene.image.Image(new FileInputStream("src/main/resources/trawa.png"));
            this.Image1 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/pig.png"));
            this.Image2 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/malpa.png"));
            this.Image3 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/pies.png"));
            this.Image4 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/tygrys.png"));
            this.Image5 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/kot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
