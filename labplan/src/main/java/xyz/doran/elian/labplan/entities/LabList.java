package xyz.doran.elian.labplan.entities;

import java.util.Date;

public class LabList {
	private Integer id;
	private Integer patientId;
	private Date creationDate;
	
	
	public LabList() {
		
	}
	
	public LabList(Integer id, Integer patientId, Date creationDate) {
		this.id = id;
		this.patientId = patientId;
		this.creationDate = creationDate;
	}
	
	public LabList(Integer patientId, Date creationDate) {
		this(null, patientId, creationDate);
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
