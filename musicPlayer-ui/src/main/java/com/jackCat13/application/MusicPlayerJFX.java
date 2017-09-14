package com.jackCat13.application;

import java.io.File;
import java.util.List;

import com.jackCat13.UIBeans.MusicRow;
import com.jackCat13.servicesImplementation.MusicService;
import com.jackCat13.uiToBusinessTransformers.MusicRowBusinessToMusicRowUI;
import com.jackCat13.uiToBusinessTransformers.MusicRowUIToMusicRowBusiness;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class MusicPlayerJFX extends Application {

	private FileChooser fileChooser = new FileChooser();

	private TableView musicsTable;

	private final static ObservableList<MusicRow> musicData = FXCollections.observableArrayList();

	private Button addMusicButton;

	private Button playMusicButton;

	private Button nextMusicButton;

	private BorderPane componentLayout;

	private MediaPlayer player;

	private Timeline timeline;

	private List<MusicRow> musicListToPlay;

	private static final String DEFAULT_FADE_OUT_DURATION = "4000";

	private static final double MIN_COLUMN_WIDTH = 250;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		setUpCloseListener(stage);
		loadAllMusics();

		stage.setWidth(810);
		stage.setHeight(600);
		stage.setTitle("Music player");

		setUpMusicsTable();

		setUpButtons(stage);

		setUpLayouts();

		// Create scene
		Scene scene = new Scene(componentLayout, 800, 600);
		scene.setFill(Color.SKYBLUE);

		// ajout de la sc�ne sur l'estrade
		stage.setScene(scene);
		// ouvrir le rideau
		stage.show();
	}

	private void setUpCloseListener(final Stage stage) {
		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});
	}

	private void loadAllMusics() {
		MusicService musicService = new MusicService();
		List<MusicRow> musicRowList = MusicRowBusinessToMusicRowUI.tranformObjectList(musicService.getAllMusics());
		for (MusicRow musicRow : musicRowList) {
			musicData.add(musicRow);
		}
	}

	private void setUpMusicsTable() {
		// Create music table
		musicsTable = new TableView<MusicRow>();
		musicsTable.setEditable(true);

		// Create columns for musicTable
		TableColumn musicNameColumn = new TableColumn("Music name");
		musicNameColumn.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicName"));
		musicNameColumn.setMinWidth(MIN_COLUMN_WIDTH);
		TableColumn musicPathColumn = new TableColumn("Music path");
		musicPathColumn.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicPath"));
		musicPathColumn.setMinWidth(MIN_COLUMN_WIDTH);
		TableColumn musicFadeOutDurationColumn = new TableColumn("Fade out duration");
		musicFadeOutDurationColumn
				.setCellValueFactory(new PropertyValueFactory<MusicRow, String>("musicFadeOutDuration"));
		musicFadeOutDurationColumn.setMinWidth(MIN_COLUMN_WIDTH);
		// Add columns in musicTable
		musicsTable.getColumns().addAll(musicNameColumn, musicPathColumn, musicFadeOutDurationColumn);
		// Add data
		musicsTable.setItems(musicData);
	}

	private void setUpButtons(final Stage stage) {
		setUpAddMusicButton(stage);
		setUpPlayMusicButton();
		setUpNextMusicButton();
	}

	private void setUpAddMusicButton(final Stage stage) {
		addMusicButton = new Button("Add");
		addMusicButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					MusicRow musicRow = new MusicRow(file.getName(), file.getPath(), DEFAULT_FADE_OUT_DURATION);
					MusicService musicService = new MusicService();
					musicService.addMusic(MusicRowUIToMusicRowBusiness.transformObject(musicRow));
					musicData.add(musicRow);
				}
			}
		});
	}

	private void setUpPlayMusicButton() {
		playMusicButton = new Button("Play");
		playMusicButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MusicRow musicRow = (MusicRow) musicsTable.getSelectionModel().getSelectedItem();
				Media pick = new Media(new File(musicRow.getMusicPath()).toURI().toString());
				player = new MediaPlayer(pick);
				player.play();
				setUpMusicPlayerListener();
			}
		});
	}

	private void setUpNextMusicButton() {
		nextMusicButton = new Button("Next");
		nextMusicButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int index = musicsTable.getSelectionModel().getSelectedIndex();
				int duration = Integer.parseInt(musicData.get(index).getMusicFadeOutDuration());
				for (int i = 0; i < duration; i++) {
					player.setVolume(player.getVolume()-0.0004);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				player.stop();

				if (index < musicsTable.getItems().size()) {
					Media pick = new Media(new File(musicData.get(index + 1).getMusicPath()).toURI().toString());
					player = new MediaPlayer(pick);
					player.play();
					setUpMusicPlayerListener();
					musicsTable.requestFocus();
					musicsTable.getSelectionModel().select(index + 1);
					musicsTable.getFocusModel().focus(index + 1);
				}
			}
		});
	}

	private void setUpLayouts() {
		componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20, 20, 20, 20));
		componentLayout.setCenter(musicsTable);
		HBox buttons = new HBox();
		componentLayout.setBottom(buttons);
		buttons.getChildren().addAll(addMusicButton, playMusicButton, nextMusicButton);
	}

	private void setUpMusicPlayerListener() {
		player.setOnEndOfMedia(new Runnable() {
			public void run() {
				player.stop();
				int index = musicsTable.getSelectionModel().getSelectedIndex();
				if (index < musicsTable.getItems().size()) {
					Media pick = new Media(new File(musicData.get(index + 1).getMusicPath()).toURI().toString());
					player = new MediaPlayer(pick);
					player.play();
					setUpMusicPlayerListener();
					musicsTable.requestFocus();
					musicsTable.getSelectionModel().select(index + 1);
					musicsTable.getFocusModel().focus(index + 1);
				}
				return;
			}
		});
	}
}
