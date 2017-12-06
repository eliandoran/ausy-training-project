package com.labplan.persistence.generic;

import com.labplan.entities.LabList;
import com.labplan.entities.LabTest;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link LabList} entity.
 * 
 * @author adoran
 *
 */
public interface GenericLabListDao extends CrudInterface<LabList, Integer> {

}
