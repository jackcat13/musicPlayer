package com.jackCat13.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class MusicPlayerGenericDao {

	private static final String PERSISTENCE_UNIT_NAME = "MPPU";

	private EntityManagerFactory musicPlayerEntityManagerFactory;
	
	private EntityManager musicPlayerEntityManager;
	
	private Class musicPlayerEntityClass;
	
	public MusicPlayerGenericDao(Class entityClass) {
		musicPlayerEntityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		musicPlayerEntityManager = musicPlayerEntityManagerFactory.createEntityManager();
		musicPlayerEntityClass = entityClass;
	}
	
	public List<? extends Entity> getAll() {
		CriteriaBuilder builder = musicPlayerEntityManager.getCriteriaBuilder();
	    CriteriaQuery<? extends Entity> query = (CriteriaQuery<? extends Entity>) builder.createQuery();
	    Root<? extends Entity> variableRoot = query.from(musicPlayerEntityClass);
	    query.multiselect(variableRoot);
	    return musicPlayerEntityManager.createQuery(query).getResultList();
	}

	public void save(Object entity) {
		musicPlayerEntityManager.getTransaction().begin();
		musicPlayerEntityManager.persist(entity);
		musicPlayerEntityManager.getTransaction().commit();
	}
}
