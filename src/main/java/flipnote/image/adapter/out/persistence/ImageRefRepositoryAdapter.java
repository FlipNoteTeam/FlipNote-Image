package flipnote.image.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import flipnote.image.application.port.out.ImageRefPort;

import flipnote.image.infrastructure.persistence.jpa.ImageRefRepository;
import flipnote.image.infrastructure.persistence.jpa.ImageRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRefRepositoryAdapter implements ImageRefPort {

    private final ImageRefRepository imageRefRepository;
    private final ImageRepository imageRepository;

    @Override
    public ImageRefRow save(Long imageId) {
        return null;
    }
}
