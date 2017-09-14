package com.jackCat13.servicesImplementation;

import java.util.List;

import javax.persistence.Entity;

import com.jackCat13.dao.MusicRowDao;
import com.jackCat13.entities.MusicEntry;

public class MusicService implements com.jackCat13.servicesInterface.MusicService {
	
	private MusicRowDao musicRowDao;
	
	public MusicService() {
		musicRowDao = new MusicRowDao();
	}

	public List<MusicEntry> getAllMusics() {
		return (List<MusicEntry>) musicRowDao.getAll();
	}

	public void addMusic(MusicEntry musicEntry) {
		musicRowDao.save(musicEntry);
	}

}
