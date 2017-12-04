package com.labplan.entities;

import java.util.*;

public class LabList extends Entity<Integer> {
	private Integer patientId;
	private Date creationDate;
	private List<LabResult> resultsList;
	
	public LabList() {
		resultsList = new ArrayList<LabResult>();
	}
	
	public LabList(Integer id, Integer patientId, Date creationDate) {
		this();
		this.id = id;
		this.patientId = patientId;
		this.creationDate = creationDate;
	}
	
	public LabList(Integer patientId, Date creationDate) {
		this(null, patientId, creationDate);
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public List<LabResult> getResultsList() {
		return resultsList;
	}
}
