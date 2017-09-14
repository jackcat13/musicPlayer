package com.jackCat13.dao;

import java.util.List;

import javax.persistence.Entity;

import com.jackCat13.entities.MusicEntry;

public class MusicRowDao extends MusicPlayerGenericDao {

	public MusicRowDao() {
		super(MusicEntry.class);
	}
	
}
