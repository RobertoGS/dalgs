package es.ucm.fdi.dalgs.domain.info;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class ExternalActivityInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 100)
	@Column(name = "name")
	private String name;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1000)
	@Column(name = "description")
	private String description;

	@NotEmpty
	@NotNull
	@NotBlank
	@Size(min = 1, max = 20)
	@Column(name = "code_activity", nullable = false)
	private String code;

//	@Size(min = 1, max = 250)
//	@Basic(optional = true)
//	@Column(name = "url", length = 250, nullable = true)
//	private String url;
	
	
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
	
	

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((code == null) ? 0 : code.hashCode());
//		return result;
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ActivityInfo other = (ActivityInfo) obj;
//		if (code == null) {
//			if (other.code != null)
//				return false;
//		} else if (!code.equals(other.code))
//			return false;
//		return true;
//	}
}
