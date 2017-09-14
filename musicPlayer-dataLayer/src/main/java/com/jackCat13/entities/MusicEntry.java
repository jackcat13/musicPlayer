package com.jackCat13.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MusicEntry implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 506420543746955383L;

	@Column
	private String musicName;
	
	@Id
	private String musicPath;
	
	@Column
	private int fadeOutDuration;
	
	public MusicEntry() {
		
	}

	public MusicEntry(String musicName, String musicPath, int fadeOutDuration) {
		super();
		this.musicName = musicName;
		this.musicPath = musicPath;
		this.fadeOutDuration = fadeOutDuration;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public int getFadeOutDuration() {
		return fadeOutDuration;
	}

	public void setFadeOutDuration(int fadeOutDuration) {
		this.fadeOutDuration = fadeOutDuration;
	}
	
	
}
