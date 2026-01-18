package flipnote.image.infrastructure.persistence.querydsl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import flipnote.image.domain.model.reference.ImageRefStatus;
import flipnote.image.domain.model.reference.ImageRef;

public interface ImageRefQueryRepository {
	/**
	 * 특정 시간내에 연결이 안된 참조는 삭제
	 * @param status 이미지 참조 상태
	 * @param cutOffTime 마감시간
	 * @param lastId 커서 기반 마지막 id
	 * @param pageable 커서 기반
	 * @return
	 */
	List<ImageRef> findByStatusAndCreatedAtLessThanAndIdLessThan(
		ImageRefStatus status,
		LocalDateTime cutOffTime,
		Long lastId,
		Pageable pageable
	);
}
