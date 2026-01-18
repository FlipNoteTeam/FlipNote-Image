package flipnote.image.domain.model.reference;

import flipnote.image.domain.model.BaseEntity;
import flipnote.image.domain.model.image.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "image_reference",
	uniqueConstraints = @UniqueConstraint(
		name = "uk_image_ref",
		columnNames = {"reference_type", "reference_id"}
	)
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageRef extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Reference reference;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id", nullable = false)
	private Image image;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ImageRefStatus status;

	@Version
	private Long version;

	private ImageRef(Reference reference, Image image) {
		this.reference = reference;
		this.image = image;
	}

	/**
	 * 새로운 이미지 참조 생성
	 * @param reference
	 * @param image
	 * @return
	 */
	public static ImageRef attach(Reference reference, Image image) {
		return new ImageRef(reference, image);
	}

	/* 도메인 규칙 */

	/**
	 * 이미지 교체
	 * @param replaceImage
	 */
	public void replaceImage(Image replaceImage) {
		if(replaceImage == null) {
			throw new IllegalArgumentException("이미지 필요");
		}

		if(this.status != ImageRefStatus.USING) {
			throw new IllegalArgumentException("상태 변경 x");
		}

		this.image = replaceImage;
	}

	/**
	 * 삭제
	 */
	public void deactivate() {
		this.status = ImageRefStatus.DELETED;
	}

	/**
	 * 특정 대상의 것인지 확인
	 * @param type 이미지 참조된 종류
	 * @param referenceId 이미지 참조 아이디
	 * @return
	 */
	public boolean isFor(ReferenceType type, Long referenceId) {
		return this.reference.getType() == type && this.reference.getId().equals(referenceId);
	}

}
