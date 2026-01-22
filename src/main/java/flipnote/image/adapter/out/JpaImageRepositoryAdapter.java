package flipnote.image.adapter.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import flipnote.image.application.port.out.ImageRepositoryPort;
import flipnote.image.infrastructure.persistence.jpa.ImageRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaImageRepositoryAdapter implements ImageRepositoryPort {

    private final ImageRepository imageRepository; // Spring Data repo
    private final JPAQueryFactory queryFactory;

    @Override
    public long saveNewImage(String s3Key, String originalFileName) {
        return 0;
    }

    @Override
    public Optional<ImageRow> findById(long imageId) {
        return Optional.empty();
    }

    @Override
    public List<ImageIdKey> findOrphanCandidates(Long cursor, int batchSize) {
        return null;
    }

    @Override
    public void hardDeleteById(long imageId) {

    }
}
