package com.labplan.services;

import java.util.LinkedList;
import java.util.List;

import com.labplan.api.exceptions.EntityNotFoundException;
import com.labplan.entities.Patient;
import com.labplan.persistence.generic.PatientDao;

public class PatientService extends Service<Integer, Patient, PatientDao> {
	public PatientService(PatientDao dao) {
		super(dao);
	}

	@Override
	public List<Patient> getPage(int page, int entriesPerPage) {
		if (page == 0)
			return new LinkedList<Patient>();

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
		validator.validate(); // Force validation right now in order to assure first name & last name are not
								// null

		validator.assertStringLength("First name", firstName.trim(), 1, 75);
		validator.assertStringLength("Last name", lastName.trim(), 1, 75);

		String nameExceptions = "-";
		validator.assertStringIsAlphabetic("First name", firstName.trim(), true, nameExceptions);
		validator.assertStringIsAlphabetic("Last name", lastName.trim(), true, nameExceptions);

		validator.assertStringIsInteger("Age", age);
		validator.assertStringIsInteger("Weight", weight);
		validator.assertStringIsInteger("Height", height);

		validator.assertIntegerInRange("Age", age, 0, 200);
		validator.assertIntegerInRange("Weight", weight, 0, 200);
		validator.assertIntegerInRange("Height", height, 0, 250);

		validator.validate(); // Will throw ValidationException if any assertions failed.

		return new Patient(firstName.trim(), lastName.trim(), Integer.parseInt(age), Integer.parseInt(height),
				Integer.parseInt(weight));
	}

	@Override
	public Patient get(Integer key) throws EntityNotFoundException {
		Patient patient = dao.read(key);
		
		if (patient == null)
			throw new EntityNotFoundException();
		
		return patient;
	}
}
