package com.iacovelli.fakeamazon.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID> implements Identity<ID> {

	@Transient
	private String uuid = UUID.randomUUID().toString();

	@Version
	private Long version;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime updatedDate;

	public Long getVersion() {
		return version;
	}

	public BaseEntity setVersion(Long version) {
		this.version = version;
		return this;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public BaseEntity setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public BaseEntity setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BaseEntity<?> that = (BaseEntity<?>) o;

		if (getId() == null && that.getId() == null) {
			return uuid.equals(that.uuid);
		}

		return getId() != null ? getId().equals(that.getId()) : that.getId() == null;

	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : uuid.hashCode();
	}
}
