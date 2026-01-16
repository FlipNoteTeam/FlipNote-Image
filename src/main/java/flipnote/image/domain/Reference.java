package flipnote.image.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reference {
	@Enumerated(EnumType.STRING)
	@Column(name = "reference_type", nullable = false)
	private ReferenceType type;

	@Column(name = "reference_id", nullable = false)
	private Long id;

	private Reference(ReferenceType type, Long id) {
		if(type == null || id == null) {
			throw new IllegalArgumentException("parameter is required");
		}

		this.type = type;
		this.id = id;
	}
}
