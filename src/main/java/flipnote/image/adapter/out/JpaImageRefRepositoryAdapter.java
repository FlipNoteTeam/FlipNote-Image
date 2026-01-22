package flipnote.image.adapter.out;

import org.springframework.stereotype.Repository;

import flipnote.image.application.port.out.ImageRefRepositoryPort;

import flipnote.image.infrastructure.persistence.jpa.ImageRefRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaImageRefRepositoryAdapter implements ImageRefRepositoryPort {

    private final ImageRefRepository imageRefRepository;

    @Override
    public boolean existsByImageId(long imageId) {
        return false;
    }
}
