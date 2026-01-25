package flipnote.image.application.port.out;

import java.util.Optional;

public interface ImagePort {

    Optional<ImageRow> findByHash(String hash);
    ImageRow save(newImage newImage);

    record ImageRow(long id, String hash, String s3Key) {}
    record newImage(String hash, String s3Key) {}
}
