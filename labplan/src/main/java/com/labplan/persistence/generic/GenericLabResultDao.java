package com.labplan.persistence.generic;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.CompositeKeyPair;
import com.labplan.entities.generic.LazyLoadedEntity;

public interface GenericLabResultDao
	extends CrudInterface<LabResult, CompositeKeyPair<
										LazyLoadedEntity<Integer, LabTest>,
										LazyLoadedEntity<Integer, LabList>>>
{
	
}
