package flipnote.image.application.port.out;

import java.util.Optional;

import flipnote.image.domain.model.reference.ReferenceType;

public interface ImagePort {

    Optional<ImageRow> findByHash(String hash);
    Optional<ImageRow> findByReference(ReferenceType referenceType, Long referenceId);
    ImageRow save(newImage newImage);

    record ImageRow(long id, String hash, String s3Key) {}
    record newImage(String hash, String s3Key) {}
}
