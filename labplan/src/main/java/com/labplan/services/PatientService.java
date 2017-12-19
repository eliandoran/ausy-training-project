package com.labplan.services;

import java.util.List;

import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationException;
import com.labplan.helpers.NumericUtils;
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
		Validator validator = new Validator();
		
		validator.assertNotNull("First name", firstName);
		validator.assertNotNull("Last name", lastName);
		
		validator.assertStringLength("First name", firstName.trim(), 1, 75);
		validator.assertStringLength("Last name", lastName.trim(), 1, 75);
		
		validator.assertStringIsAlphabetic("First name", firstName.trim(), true);
		validator.assertStringIsAlphabetic("Last name", lastName.trim(), true);
		
		validator.assertStringIsInteger("Age", age);
		validator.assertStringIsInteger("Weight", weight);
		validator.assertStringIsInteger("Height", height);
		
		validator.validate();

		return new Patient(
				firstName.trim(), lastName.trim(),
				Integer.parseInt(age), Integer.parseInt(height), Integer.parseInt(weight));
	}
}
