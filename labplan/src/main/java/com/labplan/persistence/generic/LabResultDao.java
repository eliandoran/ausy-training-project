package com.labplan.persistence.generic;

import com.labplan.entities.CompositeKeyPair;
import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.LazyLoadedEntity;

public interface LabResultDao
	extends CrudInterface<LabResult, CompositeKeyPair<
										LazyLoadedEntity<Integer, LabTest>,
										LazyLoadedEntity<Integer, LabList>>>
{
	
}
