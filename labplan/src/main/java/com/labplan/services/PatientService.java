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
		ValidationException err = new ValidationException();
		
		Integer firstNameLengthLimit = 75;
		Integer lastNameLengthLimit = firstNameLengthLimit;
		
		if (firstName == null || firstName.trim().length() == 0)
			err.addField("First name", "should not be empty.");

		if (lastName == null || lastName.trim().length() == 0)
			err.addField("Last name", "should not be empty.");

		if (firstName.trim().length() > firstNameLengthLimit)
			err.addField("First name", "is longer than " + firstNameLengthLimit + " characters.");
		
		if (lastName.trim().length() > lastNameLengthLimit)
			err.addField("Last name", "is longer than " + lastNameLengthLimit + " characters.");
		
		Integer _age = NumericUtils.tryParseInteger(age);
		if (_age == null)
			err.addField("Age", "is not a number.");

		Integer _weight = NumericUtils.tryParseInteger(weight);
		if (_weight == null)
			err.addField("Weight", "is not a number.");

		Integer _height = NumericUtils.tryParseInteger(height);
		if (_height == null)
			err.addField("Height", "is not a number.");
		
		if (err.getFields().size() > 0)
			throw err;

		return new Patient(firstName.trim(), lastName.trim(), _age, _height, _weight);
	}
}
