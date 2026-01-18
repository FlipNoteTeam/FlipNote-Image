package flipnote.image.domain.repository;

import java.util.Optional;

import flipnote.image.domain.model.reference.ImageRef;
import flipnote.image.domain.model.reference.Reference;

public interface ImageRefRepository {
	Optional<ImageRef> findByReference(Reference reference);
	ImageRef save(ImageRef imageRef);
}
