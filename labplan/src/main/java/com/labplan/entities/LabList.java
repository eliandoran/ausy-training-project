package com.labplan.entities;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import com.labplan.entities.generic.Entity;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * Represents a batch of {@link LabResult}s for a given {@link Patient}.
 * @author Elian Doran
 *
 */
public class LabList extends Entity<Integer> {
	private LazyLoadedEntity<Integer, Patient> patient;
	private Date creationDate;
	private List<LabResult> results;

	/**
	 * Creates a new instance of {@link LabList}.
	 */
	public LabList() {
		
	}

	/**
	 * Creates a new instance of {@link LabList} with predefined information.
	 * @param id			The numerical ID of the patient, also known as the entity's key.
	 * @param patient		A {@link LazyLoadedEntity} that contains a {@link Patient}.
	 * @param creationDate	The creation date of the {@link LabList}.
	 */
	public LabList(Integer id, LazyLoadedEntity<Integer, Patient> patient, Date creationDate) {
		this();
		this.id = id;
		this.patient = patient;
		this.creationDate = creationDate;
	}

	/**
	 * Creates a new instance of {@link LabList} with predefined information.
	 * @param patient		A {@link LazyLoadedEntity} that contains a {@link Patient}.
	 * @param creationDate	The creation date of the {@link LabList}.
	 */
	public LabList(LazyLoadedEntity<Integer, Patient> patient, Date creationDate) {
		this(null, patient, creationDate);
	}

	/**
	 * Obtains the {@link Patient} whose {@link LabList} belongs to. The entity is wrapped in a {@link LazyLoadedEntity}.
	 * @return A {@link Patient} wrapped in a {@link LazyLoadedEntity}.
	 */
	public LazyLoadedEntity<Integer, Patient> getPatient() {
		return patient;
	}

	/**
	 * Sets the {@link Patient} whose {@link LabList} belongs to. The entity is wrapped in a {@link LazyLoadedEntity}.
	 * @param patient	A {@link Patient} wrapped in a {@link LazyLoadedEntity}.
	 */
	public void setPatient(LazyLoadedEntity<Integer, Patient> patient) {
		this.patient = patient;
	}

	/**
	 * Gets the creation date of the {@link LabList}.
	 * @return The creation date of the {@link LabList}.
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * Sets the creation date of the {@link LabList}.
	 * @param creationDate The creation date of the {@link LabList}.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = Date.from(creationDate.toInstant().truncatedTo(ChronoUnit.SECONDS));
	}

	/**
	 * Obtains a {@link List} of all the {@link LabResult}s belonging to this {@link LabList}.
	 * @return A {@link List} of all the {@link LabResult}s belonging to this {@link LabList}.
	 */
	public List<LabResult> getResults() {
		return results;
	}
	
	/**
	 * Sets the {@link List} of all the {@link LabResult}s belonging to this {@link LabList}.
	 * @param list The {@link List} of all the {@link LabResult}s belonging to this {@link LabList}
	 */
	public void setResults(List<LabResult> list) {
		results = list;
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
	
	/**
	 * Creates a field-by-field copy of this {@link LabList}. The two {@link LabList}s will use a shared reference to {@code getResults}.
	 * @return A shallow copy of this {@link LabList}.
	 */
	public LabList shallowCopy() {
		LabList copy = new LabList();
		copy.setId(getId());
		copy.setCreationDate(getCreationDate());
		copy.setPatient(getPatient());
		copy.setResults(getResults());
		return copy;
	}
}
