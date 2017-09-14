package com.jackCat13.UIBeans;

import javafx.beans.property.SimpleStringProperty;

public class MusicRow {

    private final SimpleStringProperty musicName;
    private final SimpleStringProperty musicPath;
    private final SimpleStringProperty musicFadeOutDuration;

    public MusicRow(String musicName, String musicPath, String musicFadeOutDuration) {
        this.musicName = new SimpleStringProperty(musicName);
        this.musicPath = new SimpleStringProperty(musicPath);
        this.musicFadeOutDuration = new SimpleStringProperty(musicFadeOutDuration);
    }

    public String getMusicName() {
        return musicName.get();
    }

    public SimpleStringProperty musicNameProperty() {
        return musicName;
    }

    public String getMusicPath() {
        return musicPath.get();
    }

    public SimpleStringProperty musicPathProperty() {
        return musicPath;
    }

    public String getMusicFadeOutDuration() {
        return musicFadeOutDuration.get();
    }

    public SimpleStringProperty musicFadeOutDurationProperty() {
        return musicFadeOutDuration;
    }

    public void setMusicName(String musicName) {
        this.musicName.set(musicName);
    }

    public void setMusicPath(String musicPath) {
        this.musicPath.set(musicPath);
    }

    public void setMusicFadeOutDuration(String musicFadeOutDuration) {
        this.musicFadeOutDuration.set(musicFadeOutDuration);
    }
}
