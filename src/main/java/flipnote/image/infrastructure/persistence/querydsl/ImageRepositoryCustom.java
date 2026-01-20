package flipnote.image.infrastructure.persistence.querydsl;

import java.util.List;
import java.util.Optional;

import flipnote.image.domain.model.image.Image;
import flipnote.image.domain.model.reference.Reference;

public interface ImageRepositoryCustom {
	/**
	 * 이미지 조회
	 * @param reference 이미지가 참조되는 곳의 타입과 아이디
	 * @return
	 */
	Optional<Image> findImageByImageRef_Reference(Reference reference);

	List<Image> findImageByImageRef_NotExist(Long lastId, int batchSize);
}
