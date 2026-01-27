package flipnote.image.application.port.out;

import flipnote.image.domain.model.reference.ReferenceType;

public interface ImageRefPort {

    ImageRefRow save(Long imageId);

	//이미지 참조 활성화
	void activate(Long imageRefId, ReferenceType referenceType, Long referenceId);

	Long getImageIdByRefId(Long imageRefId);

	record ImageRefRow(Long id, Long imageId) {}
}
