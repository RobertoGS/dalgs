package es.ucm.fdi.dalgs.domain.info;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class ActivityInfo {

	@NotEmpty @NotNull @NotBlank
	@Size(min=1, max=20)
	@Column(name = "name")
	private String name;
	
	@NotEmpty @NotNull @NotBlank
	@Size(min=1, max=50)
	@Column(name = "description")
	private String description;
	
	@NotEmpty @NotNull @NotBlank
	@Size(min=1, max=20)
	@Column(name = "code_activity", nullable = false, unique = true)
	private String code;

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
	
	
}