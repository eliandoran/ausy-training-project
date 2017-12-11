package com.labplan.persistence.generic;

import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.Patient;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link LabList} entity.
 * 
 * @author Elian Doran
 *
 */
public interface GenericLabListDao extends CrudInterface<LabList, Integer> {
	LabList read(Integer key, boolean loadResults);
	
	Set<LabList> readAllByPatient(Patient patient);
}
