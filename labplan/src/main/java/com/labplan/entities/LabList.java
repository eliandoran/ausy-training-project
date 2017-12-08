package com.labplan.entities;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.labplan.entities.generic.Entity;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * Represents a batch of {@link LabResult}s for a given {@link Patient}.
 * @author adoran
 *
 */
public class LabList extends Entity<Integer> {
	private LazyLoadedEntity<Integer, Patient> patient;
	private Date creationDate;
	private List<LabResult> resultsList;

	public LabList() {
		
	}

	public LabList(Integer id, LazyLoadedEntity<Integer, Patient> patient, Date creationDate) {
		this();
		this.id = id;
		this.patient = patient;
		this.creationDate = creationDate;
	}

	public LabList(LazyLoadedEntity<Integer, Patient> patient, Date creationDate) {
		this(null, patient, creationDate);
	}

	public LazyLoadedEntity<Integer, Patient> getPatient() {
		return patient;
	}

	public void setPatient(LazyLoadedEntity<Integer, Patient> patient) {
		this.patient = patient;
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
	
	public void setLabResultsList(List<LabResult> list) {
		resultsList = list;
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
		hash = 31 * hash + id.hashCode();
		hash = 31 * hash + patient.hashCode();
		hash = 31 * hash + getCreationDate().hashCode();
		return hash;
	}
}
