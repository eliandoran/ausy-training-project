package com.labplan.services;

import java.util.List;

import com.labplan.entities.LabTest;
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
		Validator validator = new Validator();
		
		validator.assertNotNull("Name", name);
		validator.validate();	// Force validation right now in order to assure name is not null
		
		validator.assertNotEmpty("Name", name.trim());
		validator.assertStringIsFloat("Minimum value", valueMin);
		validator.assertStringIsFloat("Maximum value", valueMax);
		
		validator.validate();	// Will throw ValidationException if any assertions failed.
		
		return new LabTest(name.trim(), description.trim(),
				Float.parseFloat(valueMin),
				Float.parseFloat(valueMax));
	}
}
