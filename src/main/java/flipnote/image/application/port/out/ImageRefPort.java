package flipnote.image.application.port.out;

import flipnote.image.domain.model.reference.Reference;
import flipnote.image.domain.model.reference.ReferenceType;

public interface ImageRefPort {
    // boolean existsByImageId(long imageId);
    // confirm 시점에 ref 생성/연결이 필요하면 메서드 추가

    ImageRefRow save(Long imageId);

    record ImageRefRow(Long id, Long imageId) {}
}
