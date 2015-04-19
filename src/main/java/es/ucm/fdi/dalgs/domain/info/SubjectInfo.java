package es.ucm.fdi.dalgs.domain.info;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import es.ucm.fdi.dalgs.domain.Copyable;

@Embeddable
public class SubjectInfo implements Serializable, Cloneable,
		Copyable<SubjectInfo> {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	@Basic(optional = false)
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 250)
	@Basic(optional = false)
	@Column(name = "description", length = 250, nullable = false)
	private String description;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 20)
	@Column(name = "code_subject", nullable = false, unique = true)
	private String code;
	

	@Size(min = 1, max = 250)
	@Basic(optional = false)
	@Column(name = "url_doc", length = 250, nullable = false)
	private String url_doc;
	

	@NotNull
	@Min(0)
	@Max(12)
	@Basic
	@Column(name = "credits", length = 250, nullable = false)
	private Integer credits;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	

	public String getUrl_doc() {
		return url_doc;
	}

	public void setUrl_doc(String url_doc) {
		this.url_doc = url_doc;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectInfo other = (SubjectInfo) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public SubjectInfo depth_copy() {
		SubjectInfo copy = this.shallow_copy();
		return copy;
	}

	@Override
	public SubjectInfo shallow_copy() {
		try {
			return (SubjectInfo) super.clone();

		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
