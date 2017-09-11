package com.jackCat13.entities;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class MusicEntryTest {

	@Test
	public void test() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MPPU");
        EntityManager theManager = factory.createEntityManager();
        theManager.getTransaction().begin();

        MusicEntry musicEntry = new MusicEntry();
        musicEntry.setMusicPath("pathTest");
        theManager.persist(musicEntry);

        MusicEntry musicRetrieved = (MusicEntry)theManager.find(MusicEntry.class, "pathTest");

        assertEquals("pathTest", musicRetrieved.getMusicPath()); 
        theManager.close();
	}

}
