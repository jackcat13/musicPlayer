package com.jackCat13.application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;

@SuppressWarnings("restriction")
public class MusicPlayerJFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setWidth(800);
		stage.setHeight(600);
		// met un titre dans la fenêtre
		stage.setTitle("Music player");

		// la racine du sceneGraph est le root
		Group root = new Group();
		Scene scene = new Scene(root);
		scene.setFill(Color.SKYBLUE);
		
		// ajout de la scène sur l'estrade
        stage.setScene(scene);
        // ouvrir le rideau
        stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
