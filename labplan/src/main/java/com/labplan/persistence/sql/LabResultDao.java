package com.labplan.persistence.sql;

import java.util.Set;

import com.labplan.entities.CompositeKeyPair;
import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;

public class LabResultDao implements com.labplan.persistence.generic.LabResultDao {
	@Override
	public LabResult read(CompositeKeyPair<LabTest, LabList> key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<LabResult> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositeKeyPair<LabTest, LabList> create(LabResult entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(LabResult entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(LabResult entity) {
		// TODO Auto-generated method stub
		return false;
	}
}
