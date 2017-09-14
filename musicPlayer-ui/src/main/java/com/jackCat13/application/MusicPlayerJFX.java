package com.jackCat13.application;

import com.jackCat13.UIBeans.MusicRow;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;

@SuppressWarnings("restriction")
public class MusicPlayerJFX extends Application {

	private FileChooser fileChooser = new FileChooser();

	private TableView musicsTable;

	private final ObservableList<MusicRow> musicData =
			FXCollections.observableArrayList();

	private static final String DEFAULT_FADE_OUT_DURATION = "400";

	private static final double MIN_COLUMN_WIDTH = 250;

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setWidth(810);
		stage.setHeight(600);
		// met un titre dans la fen�tre
		stage.setTitle("Music player");

		//The BorderPane has the same areas laid out as the
		//BorderLayout layout manager
		BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20, 20, 20, 20));

		//create musics table
		musicsTable = new TableView();

		//Set music table editable
		musicsTable.setEditable(true);

		//Create columns for musicTable
		TableColumn musicNameColumn = new TableColumn("Music name");
		musicNameColumn.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicName"));
		musicNameColumn.setMinWidth(MIN_COLUMN_WIDTH);
		TableColumn musicPathColumn = new TableColumn("Music path");
		musicPathColumn.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicPath"));
		musicPathColumn.setMinWidth(MIN_COLUMN_WIDTH);
		TableColumn musicFadeOutDurationColumn = new TableColumn("Fade out duration");
		musicFadeOutDurationColumn.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicFadeOutDuration"));
		musicFadeOutDurationColumn.setMinWidth(MIN_COLUMN_WIDTH);
		//Add columns in musicTable
		musicsTable.getColumns().addAll(musicNameColumn, musicPathColumn, musicFadeOutDurationColumn);
		//Add data
		musicsTable.setItems(musicData);

		//The button uses an inner class to handle the button click event
		Button addMusicButton = new Button("Add");
		addMusicButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					musicData.add(new MusicRow(file.getName(), file.getPath(), DEFAULT_FADE_OUT_DURATION));
				}
			}
		});

		Button playMusicButton = new Button("Play");
		playMusicButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MusicRow musicRow = (MusicRow) musicsTable.getSelectionModel().getSelectedItem();
				int index = musicsTable.getSelectionModel().getSelectedIndex();
				Media music = new Media(new File(musicRow.getMusicPath()).toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(music);
				mediaPlayer.play();
			}
		});

		componentLayout.setCenter(musicsTable);
		HBox buttons = new HBox();
		componentLayout.setBottom(buttons);
		buttons.getChildren().addAll(addMusicButton, playMusicButton);

		// Create scene
		Scene scene = new Scene(componentLayout, 800, 600);
		scene.setFill(Color.SKYBLUE);
		
		// ajout de la sc�ne sur l'estrade
        stage.setScene(scene);
        // ouvrir le rideau
        stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
