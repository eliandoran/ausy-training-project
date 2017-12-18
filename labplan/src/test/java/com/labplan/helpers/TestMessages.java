package com.labplan.helpers;

import com.labplan.persistence.generic.Dao;

/**
 * Contains a set of constant string messages which can be used for displaying
 * unit testing errors related to common CRUD operations like the ones defined
 * in {@link DaoTester}.
 * 
 * @author Elian Doran
 *
 */
public final class TestMessages {
	/**
	 * Message to be displayed when a {@link Dao} READ of a previously inserted
	 * entity fails.
	 */
	public static final String MSG_ENTITY_NOT_FOUND = "Inserted entity not found";

	/**
	 * Message to be displayed when the generated entity and the one retrieved from
	 * the data source are not identical.
	 */
	public static final String MSG_ENTITIES_NOT_IDENTICAL = "Dummy entity and retrieved entity are not identical.";

	/**
	 * Message to be displayed when a {@link Dao} DELETE fails.
	 */
	public static final String MSG_DELETION_FAILED = "SQL deletion for single entity failed.";

	/**
	 * Message to be displayed when a {@link Dao} UPDATE fails.
	 */
	public static final String MSG_UPDATE_FAILED = "SQL update for single entity failed.";

	/**
	 * Message to be displayed when a {@link Dao} READ fails.
	 */
	public static final String MSG_RETRIEVAL_FAILED = "SQL retrieval for single entity failed.";

	/**
	 * Message to be displayed when a {@link Dao} INSERT fails.
	 */
	public static final String MSG_INSERTION_FAILED = "SQL insertion failed.";

	/**
	 * Message to be displayed when a {@link Dao} TRUNCATE operation fails.
	 */
	public static final String MSG_TRUNCATE_FAILED = "SQL truncate failed.";
}
