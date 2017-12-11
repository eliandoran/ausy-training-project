package com.labplan.helpers;

import static com.labplan.helpers.TestMessages.MSG_DELETION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_ENTITIES_NOT_IDENTICAL;
import static com.labplan.helpers.TestMessages.MSG_ENTITY_NOT_FOUND;
import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_RETRIEVAL_FAILED;
import static com.labplan.helpers.TestMessages.MSG_UPDATE_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.Dao;

/**
 * This is an abstract test suite which implements a set of predefined test
 * cases for testing the CRUD capabilities of a {@link Dao} implementation.
 * 
 * @author Elian Doran
 *
 * @param <TKey>
 *            The key used to identify entities, usually same with the primary
 *            keys of a data source.
 * @param <TEntity>
 *            The type of {@link Entity} used to represent the data stored in
 *            the data source.
 * @param <TDao>
 *            A {@link Dao} which represents the DAO for the data source.
 */
public abstract class DaoTester<TKey, TEntity extends Entity<TKey>, TDao extends Dao<TEntity, TKey>> {
	@Test
	public void testInsertion() {
		TEntity dummyTest = getRandomEntity();
		TEntity sameTest;
		TDao dao = getDao();

		// First, CREATE a randomly generated entity.
		dummyTest.setId(dao.create(dummyTest));
		assertNotNull(MSG_INSERTION_FAILED, dummyTest.getId());

		// READ it back and check whether it is identical to the first one.
		sameTest = dao.read(dummyTest.getId());
		assertNotNull(MSG_RETRIEVAL_FAILED, sameTest);
		assertEquals(MSG_ENTITIES_NOT_IDENTICAL, dummyTest, sameTest);

		// DELETE it afterwards.
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyTest));
	}

	@Test
	public void testGetAll() {
		TDao dao = getDao();

		// CREATE two random entities
		TEntity dummyEntity1 = getRandomEntity();
		TEntity dummyEntity2 = getRandomEntity();

		dummyEntity1.setId(dao.create(dummyEntity1));
		dummyEntity2.setId(dao.create(dummyEntity2));

		assertNotNull(MSG_INSERTION_FAILED, dummyEntity1.getId());
		assertNotNull(MSG_INSERTION_FAILED, dummyEntity2.getId());

		// READ all the entities and check whether the two generated ones can be found.
		Set<TEntity> entities = dao.readAll();
		assertTrue(MSG_ENTITY_NOT_FOUND, entities.contains(dummyEntity1));
		assertTrue(MSG_ENTITY_NOT_FOUND, entities.contains(dummyEntity2));

		// DELETE the generated entities.
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyEntity1));
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyEntity2));
	}

	@Test
	public void testUpdate() {
		TDao dao = getDao();

		// CREATE a random entity.
		TEntity dummyEntity = getRandomEntity();
		dummyEntity.setId(dao.create(dummyEntity));
		assertNotNull(MSG_INSERTION_FAILED, dummyEntity.getId());

		// UPDATE the last entity with another generated entity, keeping the ID.
		TEntity updatedEntity = getRandomEntity();
		updatedEntity.setId(dummyEntity.getId());
		assertTrue(MSG_UPDATE_FAILED, dao.update(updatedEntity));

		// READ back the updated entity to see if it matches the last generated entity.
		TEntity sameEntity = dao.read(dummyEntity.getId());
		assertEquals(updatedEntity, sameEntity);
		assertNotEquals(dummyEntity, sameEntity);

		// DELETE the entity.
		assertTrue(MSG_DELETION_FAILED, dao.delete(updatedEntity));
	}

	/**
	 * Generates a random {@link Entity} whose fields should (theoretically) be
	 * unique to the ones that are already present in the data source.
	 * 
	 * @return
	 */
	public abstract TEntity getRandomEntity();

	/**
	 * Gets the {@link Dao} DAO which can be used to access the data source.
	 * 
	 * @return The {@link Dao} DAO which can be used to access the data source.
	 */
	public abstract TDao getDao();
}
