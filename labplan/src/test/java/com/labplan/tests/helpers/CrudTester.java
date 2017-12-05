package com.labplan.tests.helpers;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;
import com.labplan.entities.Entity;
import com.labplan.persistence.generic.CrudInterface;

/**
 * This is an abstract test suite which implements a set of predefined test
 * cases for testing the CRUD capabilities of a {@link CrudInterface}
 * implementation.
 * 
 * @author adoran
 *
 * @param <TKey>
 *            The key used to identify entities, usually same with the primary
 *            keys of a data source.
 * @param <TEntity>
 *            The type of {@link Entity} used to represent the data stored in
 *            the data source.
 * @param <TDao>
 *            A {@link CrudInterface} which represents the DAO for the data
 *            source.
 */
public abstract class CrudTester<TKey, TEntity extends Entity<TKey>, TDao extends CrudInterface<TEntity, TKey>> {
	private static final String MSG_ENTITY_NOT_FOUND = "Inserted entity not found";
	private static final String MSG_ENTITIES_NOT_IDENTICAL = "Dummy entity and retrieved entity are not identical.";
	private static final String MSG_DELETION_FAILED = "SQL deletion for single entity failed.";
	private static final String MSG_UPDATE_FAILED = "SQL update for single entity failed.";
	private static final String MSG_RETRIEVAL_FAILED = "SQL retrieval for single entity failed.";
	private static final String MSG_INSERTION_FAILED = "SQL insertion failed.";

	@Test
	public void testInsertion() {
		TEntity dummyTest = getRandomEntity();
		TEntity sameTest;
		TDao dao = getDao();

		// First, CREATE a randomly generated entity.
		TKey testId = dao.create(dummyTest);
		assertNotNull(MSG_INSERTION_FAILED, testId);
		dummyTest.setId(testId);

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

		TKey dummyEntityId1 = dao.create(dummyEntity1);
		TKey dummyEntityId2 = dao.create(dummyEntity2);

		assertNotNull(MSG_INSERTION_FAILED, dummyEntityId1);
		assertNotNull(MSG_INSERTION_FAILED, dummyEntityId2);

		dummyEntity1.setId(dummyEntityId1);
		dummyEntity2.setId(dummyEntityId2);

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
		TKey entityId = dao.create(dummyEntity);
		assertNotNull(MSG_INSERTION_FAILED, entityId);
		dummyEntity.setId(entityId);

		// UPDATE the last entity with another generated entity, keeping the ID.
		TEntity updatedEntity = getRandomEntity();
		updatedEntity.setId(entityId);
		assertTrue(MSG_UPDATE_FAILED, dao.update(updatedEntity));

		// READ back the updated entity to see if it matches the last generated entity.
		TEntity sameEntity = dao.read(entityId);
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
	 * Gets the {@link CrudInterface} DAO which can be used to access the data
	 * source.
	 * 
	 * @return The {@link CrudInterface} DAO which can be used to access the data
	 *         source.
	 */
	public abstract TDao getDao();
}
