package flipnote.image.application.port.in;

import flipnote.image.domain.model.reference.ReferenceType;

public interface GetImageUrlByReferenceUseCase {
    String getUrl(ReferenceType type, long referenceId);
}
