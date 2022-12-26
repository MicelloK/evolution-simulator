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

    public Images(){
        try {
            this.grassImage = new javafx.scene.image.Image(new FileInputStream("src/main/resources/ziemia.png"));
            this.Image1 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/sowa.png"));
            this.Image2 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/zyrafa.png"));
            this.Image3 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/pies.png"));
            this.Image4 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/jez.png"));
            this.Image5 = new javafx.scene.image.Image(new FileInputStream("src/main/resources/gryzon.png"));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
