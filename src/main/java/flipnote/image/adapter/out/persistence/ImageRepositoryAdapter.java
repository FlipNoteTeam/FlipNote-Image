package flipnote.image.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import flipnote.image.application.port.out.ImagePort;
import flipnote.image.domain.model.image.Image;
import flipnote.image.infrastructure.persistence.jpa.ImageRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryAdapter implements ImagePort {

    private final ImageRepository imageRepository; // Spring Data repo
    private final JPAQueryFactory queryFactory;

    /**
     * hash로 이미지 찾기
     * @param hash
     * @return
     */
    @Override
    public Optional<ImageRow> findByHash(String hash) {
        return imageRepository.findByHash(hash)
            .map(image -> new ImageRow(image.getId(), image.getHash(), image.getS3Key()));
    }

    /**
     * presignedUrl 생성시 임시 이미지 저장
     * @param newImage
     * @return
     */
    @Override
    public ImageRow save(newImage newImage) {

        Image image = imageRepository.save(Image.createBeforeSave(newImage.hash(), newImage.s3Key()));

        return new ImageRow(image.getId(), image.getHash(), image.getS3Key());
    }
}
