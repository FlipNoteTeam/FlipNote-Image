package flipnote.image.application.port.out;

import java.util.List;
import java.util.Optional;

public interface ImageRepositoryPort {
    long saveNewImage(String s3Key, String originalFileName);
    Optional<ImageRow> findById(long imageId);
    List<ImageIdKey> findOrphanCandidates(Long cursor, int batchSize);
    void hardDeleteById(long imageId);

    record ImageRow(long id, String s3Key, String originalFileName) {}
    record ImageIdKey(long id, String s3Key) {}
}
