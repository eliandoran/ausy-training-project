package com.labplan.services;

import java.util.List;

import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationError;
import com.labplan.helpers.IntUtils;
import com.labplan.persistence.generic.PatientDao;

public class PatientService extends Service<Patient, PatientDao> {
	public PatientService(PatientDao dao) {
		super(dao);
	}

	@Override
	public List<Patient> getPage(int page, int entriesPerPage) {
		if (page < 1)
			throw new RuntimeException("`page` argument should be a positive number.");

		if (entriesPerPage < 1)
			throw new RuntimeException("`entriesPerPage` should be a positive number.");

		return dao.read(entriesPerPage, (page - 1) * entriesPerPage);
	}

	@Override
	public Integer getPageCount(int entriesPerPage) {
		return (int) Math.round(Math.ceil(((double) dao.getPatientsCount() / entriesPerPage)));
	}

	public Patient parse(String firstName, String lastName, String age, String weight, String height) {
		if (firstName == null || firstName.trim().length() == 0)
			throw new ValidationError("First name should not be empty.");

		if (lastName == null || lastName.trim().length() == 0)
			throw new ValidationError("Last name should not be empty.");

		Integer _age = IntUtils.tryParse(age);
		if (_age == null)
			throw new ValidationError("Age is not a number.");

		Integer _weight = IntUtils.tryParse(weight);
		if (_weight == null)
			throw new ValidationError("Weight is not a number.");

		Integer _height = IntUtils.tryParse(height);
		if (_height == null)
			throw new ValidationError("Height is not a number.");

		return new Patient(firstName.trim(), lastName.trim(), _age, _height, _weight);
	}
}
