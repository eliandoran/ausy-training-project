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
}