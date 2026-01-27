package flipnote.image.application.port.in;

import flipnote.image.domain.model.image.ImageMeta;
import flipnote.image.domain.model.reference.ReferenceType;

public interface ActivateImageUseCase {
	ImageMeta activateImage(Long imageRefId, ReferenceType referenceType, Long referenceId);
}
