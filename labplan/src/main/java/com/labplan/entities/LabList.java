package com.labplan.entities;

import java.time.temporal.ChronoUnit;
import java.util.*;

public class LabList extends Entity<Integer> {
	private Patient patient;
	private Integer patientId;
	private Date creationDate;
	private List<LabResult> resultsList;

	public LabList() {
		resultsList = new ArrayList<LabResult>();
	}

	public LabList(Integer id, Patient patient, Date creationDate) {
		this();
		this.id = id;
		this.patient = patient;
		this.creationDate = creationDate;
	}

	public LabList(Patient patient, Date creationDate) {
		this(null, patient, creationDate);
	}
	
	public LabList(Integer patientId) {
		this.patientId = patientId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		this.patientId = (patient != null ? patient.getId() : null);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = Date.from(creationDate.toInstant().truncatedTo(ChronoUnit.SECONDS));
	}

	public List<LabResult> getResultsList() {
		return resultsList;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabList))
			return false;
		
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + patientId.hashCode();
		hash = 31 * hash + getCreationDate().hashCode();
		return hash;
	}
}
