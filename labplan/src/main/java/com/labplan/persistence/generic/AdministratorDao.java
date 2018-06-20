package com.labplan.persistence.generic;

import com.labplan.entities.Administrator;

public interface AdministratorDao extends Dao<Administrator, Integer> {
	/**
	 * Provided with the credentials of an administrator, returns whether they are correct and can be used to log in the user.
	 * 
	 * @param userName The user name to authenticate.
	 * @param password The password of the user.
	 * @return {@code true} if the authentication credentials are correct, {@code false} otherwise.
	 */
	boolean validateAuthentication(String userName, String password);
}
