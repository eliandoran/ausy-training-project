package com.labplan.persistence.generic;

import com.labplan.entities.CompositeKeyPair;
import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;

public interface LabResultDao extends CrudInterface<LabResult, CompositeKeyPair<LabTest, LabList>> {

}
