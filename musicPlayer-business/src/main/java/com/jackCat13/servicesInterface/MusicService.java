package com.jackCat13.servicesInterface;

import java.util.List;

import com.jackCat13.entities.MusicEntry;

public interface MusicService {

	public List<MusicEntry> getAllMusics();
	
	public void addMusic(MusicEntry musicEntry);
}
