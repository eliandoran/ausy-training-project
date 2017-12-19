package com.labplan.services;

import java.util.List;

import com.labplan.entities.LabTest;
import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationError;
import com.labplan.helpers.IntUtils;
import com.labplan.persistence.generic.LabTestDao;

public class LabTestService extends Service<LabTest, LabTestDao> {
	public LabTestService(LabTestDao dao) {
		super(dao);
	}

	@Override
	public List<LabTest> getPage(int page, int entriesPerPage) {
		if (page < 1)
			throw new RuntimeException("`page` argument should be a positive number.");

		if (entriesPerPage < 1)
			throw new RuntimeException("`entriesPerPage` should be a positive number.");

		return dao.read(entriesPerPage, (page - 1) * entriesPerPage);
	}

	@Override
	public Integer getPageCount(int entriesPerPage) {
		return (int) Math.round(Math.ceil(((double) dao.getCount() / entriesPerPage)));
	}
	
	public LabTest parse(String name, String description, String valueMin, String valueMax) {
		ValidationError err = new ValidationError();
		
		if (name == null || name.trim().length() == 0)
			err.addField("Name", "should not be empty.");
		
		Float _valueMin = IntUtils.tryParseFloat(valueMin);
		if (_valueMin == null)
			err.addField("Minimum value", "is not a number.");
		
		Float _valueMax = IntUtils.tryParseFloat(valueMax);
		if (_valueMax == null)
			err.addField("Maximum value", "is not a number.");
		
		if (err.getFields().size() > 0)
			throw err;
		
		return new LabTest(name.trim(), description.trim(), _valueMin, _valueMax);
	}
}
