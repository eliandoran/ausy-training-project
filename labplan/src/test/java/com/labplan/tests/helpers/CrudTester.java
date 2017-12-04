package com.labplan.tests.helpers;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Entity;
import com.labplan.entities.LabTest;
import com.labplan.persistence.generic.CrudInterface;
import com.labplan.persistence.sql.LabTestDao;

public abstract class CrudTester <TKey,
									TEntity extends Entity<TKey>,
									TDao extends CrudInterface<TEntity, TKey>> {
	@Test
	public void testInsertion() {
		TEntity dummyTest = getRandomEntity();
		TEntity sameTest;
		TDao dao = getDao();
		
		TKey testId = dao.create(dummyTest);
		assertNotNull("SQL insertion for single entity failed.", testId);
		dummyTest.setId(testId);
		
		sameTest = dao.read(dummyTest.getId());
		assertNotNull("SQL retrieval for single entity failed.", sameTest);
		assertEquals("Dummy entity and retrieved entity are not identical.", dummyTest, sameTest);
		assertTrue("SQL deletion for single entity failed.", dao.delete(dummyTest));
	}
	
	@Test
	public void testGetAll() {
		TDao dao = getDao();
		
		TEntity dummyEntity1 = getRandomEntity();
		TEntity dummyEntity2 = getRandomEntity();
		
		TKey dummyEntityId1 = dao.create(dummyEntity1);
		TKey dummyEntityId2 = dao.create(dummyEntity2);
		
		assertNotNull("SQL insertion for single entity failed.", dummyEntityId1);
		assertNotNull("SQL insertion for single entity failed.", dummyEntityId2);
		
		dummyEntity1.setId(dummyEntityId1);
		dummyEntity2.setId(dummyEntityId2);
		
		Set<TEntity> entities = dao.readAll();
		assertTrue("Inserted entity #1 not found in GetAll()", entities.contains(dummyEntity1));
		assertTrue("Inserted entity #2 not found in GetAll()", entities.contains(dummyEntity2));
		
		assertTrue("SQL deletion for single entity failed.", dao.delete(dummyEntity1));
		assertTrue("SQL deletion for single entity failed.", dao.delete(dummyEntity2));
	}
	
	@Test
	public void testUpdate() {
		TDao dao = getDao();
		
		TEntity dummyEntity = getRandomEntity();
		TKey entityId = dao.create(dummyEntity);
		assertNotNull("SQL insertion for single entity failed.", entityId);
		dummyEntity.setId(entityId);
		
		TEntity updatedEntity = getRandomEntity();
		updatedEntity.setId(entityId);
		assertTrue("SQL update for single entity failed.", dao.update(updatedEntity));
		
		TEntity sameEntity = dao.read(entityId);
		assertEquals(updatedEntity, sameEntity);
		assertNotEquals(dummyEntity, sameEntity);
	}
	
	public abstract TEntity getRandomEntity();
	
	public abstract TDao getDao();
}
