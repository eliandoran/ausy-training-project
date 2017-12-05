package com.labplan.persistence.generic;

import com.labplan.entities.LabTest;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create, Read,
 * Update, Delete) functionality for the {@link LabTest} entity.
 * @author adoran
 *
 */
public interface LabTestDao extends CrudInterface<LabTest, Integer> {

}
