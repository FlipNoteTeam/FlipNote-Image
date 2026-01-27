package flipnote.image.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import flipnote.image.application.port.out.ImageRefPort;

import flipnote.image.domain.model.image.Image;
import flipnote.image.domain.model.reference.ImageRef;
import flipnote.image.domain.model.reference.Reference;
import flipnote.image.domain.model.reference.ReferenceType;
import flipnote.image.infrastructure.persistence.jpa.ImageRefRepository;
import flipnote.image.infrastructure.persistence.jpa.ImageRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRefRepositoryAdapter implements ImageRefPort {

    private final ImageRefRepository imageRefRepository;
    private final ImageRepository imageRepository;

    /**
     * 참조한 타입과 아이디를 통해 ref 저장
     * @param imageId
     * @return
     */
    @Override
    public ImageRefRow save(Long imageId) {

        Image image = imageRepository.findById(imageId).orElseThrow(
            () -> new IllegalArgumentException("image is blank")
        );

        ImageRef imageRef = imageRefRepository.save(ImageRef.createImageRef(image));

        return new ImageRefRow(imageRef.getId(), image.getId());
    }

    /**
     * 이미지 참조 활성화 참조되는 타입과 아이디를 업데이트
     * @param imageRefId
     * @param referenceType
     * @param referenceId
     */
    @Override
    public void activate(Long imageRefId, ReferenceType referenceType, Long referenceId) {
        ImageRef imageRef = imageRefRepository.findById(imageRefId).orElseThrow(
            () -> new IllegalArgumentException("ImageRef is Blank")
        );

        imageRef.getReference().activate(referenceType, referenceId);

        imageRefRepository.save(imageRef);

    }

    @Override
    public Long getImageIdByRefId(Long imageRefId) {
        return imageRefRepository.findImageIdByImageRefId(imageRefId);
    }
}
