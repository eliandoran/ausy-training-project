package com.labplan.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.Patient;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.helpers.Pair;
import com.labplan.persistence.generic.LabListDao;
import com.labplan.persistence.generic.LabTestDao;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.persistence.sql.SqlPatientDao;

public class LabListService extends Service<LabList, LabListDao> {

	public LabListService(LabListDao dao) {
		super(dao);
	}

	@Override
	public List<LabList> getPage(int page, int entriesPerPage) {
		if (page == 0)
			return new LinkedList<LabList>();
		
		if (page < 1)
			throw new RuntimeException("`page` argument should be a positive number.");

		if (entriesPerPage < 1)
			throw new RuntimeException("`entriesPerPage` should be a positive number.");

		return dao.read(entriesPerPage, (page - 1) * entriesPerPage);
	}

	public List<LabList> getPage(Patient patient, int page, int entriesPerPage) {
		if (page == 0)
			return new LinkedList<LabList>();
		
		if (page < 1)
			throw new RuntimeException("`page` argument should be a positive number.");

		if (entriesPerPage < 1)
			throw new RuntimeException("`entriesPerPage` should be a positive number.");

		return dao.read(patient, entriesPerPage, (page - 1) * entriesPerPage);
	}
	
	@Override
	public Integer getPageCount(int entriesPerPage) {
		return (int) Math.round(Math.ceil(((double) dao.getCount() / entriesPerPage)));
	}
	
	public Integer getPageCount(Patient patient, int entriesPerPage) {
		return (int) Math.round(Math.ceil(((double) dao.getCount(patient) / entriesPerPage)));
	}

	public LabList parse(String patientId, String data) {
		Validator validator = new Validator();
		
		validator.assertStringIsInteger("Patient (ID)", patientId);
		validator.validate();
		
		PatientDao patientDao = new SqlPatientDao();
		Patient patient = patientDao.read(Integer.parseInt(patientId));
		
		validator.assertNotNull("Patient", patient);
		validator.validate();
		
		LabList list = new LabList(new LazyLoadedEntity<Integer, Patient>(patient), new Date());

		if (data != null) {
			JSONObject parsedData = new JSONObject(data);
			LabTestDao testDao = new SqlLabTestDao();
			List<LabResult> results = new LinkedList<>();
			
			Integer index = 1;
			for (String key : parsedData.keySet()) {
				String fieldName = "Lab Result #" + index.toString();
				String testId = key;
				String value = parsedData.getString(key);
				
				if (!validator.assertStringIsFloat(fieldName + " value", value))
					continue;
				
				if (validator.assertStringIsInteger(fieldName + " type", testId)) {
					LabTest test = testDao.read(Integer.parseInt(testId));
					validator.assertNotNull(fieldName + " type", test);
					
					LabResult currentResult = new LabResult();
					currentResult.setId(new LazyLoadedEntity<Integer, LabTest>(test));
					currentResult.setValue(Float.parseFloat(value));
					results.add(currentResult);
				}
				
				index++;
			}
				
			list.setResults(results);
			validator.validate();
		}
		
		return list;
	}
}
