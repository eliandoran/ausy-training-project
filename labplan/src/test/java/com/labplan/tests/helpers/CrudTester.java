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

/**
 * This is an abstract test suite which implements a set of predefined test cases for
 * testing the CRUD capabilities of a {@link CrudInterface} implementation.
 * @author adoran
 *
 * @param <TKey>	The key used to identify entities, usually same with the primary keys of a data source.
 * @param <TEntity>	The type of {@link Entity} used to represent the data stored in the data source. 
 * @param <TDao>	A {@link CrudInterface} which represents the DAO for the data source.
 */
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
	
	/**
	 * Generates a random {@link Entity} whose fields should (theoretically) be unique to the ones
	 * that are already present in the data source.
	 * @return
	 */
	public abstract TEntity getRandomEntity();
	
	/**
	 * Gets the {@link CrudInterface} DAO which can be used to access the data source.
	 * @return The {@link CrudInterface} DAO which can be used to access the data source.
	 */
	public abstract TDao getDao();
}
