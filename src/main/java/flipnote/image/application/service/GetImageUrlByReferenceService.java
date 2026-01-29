package flipnote.image.application.service;

import org.springframework.stereotype.Service;

import flipnote.image.application.port.in.GetImageUrlByReferenceUseCase;
import flipnote.image.application.port.out.ImagePort;
import flipnote.image.application.port.out.PublicUrlPort;
import flipnote.image.domain.model.reference.ReferenceType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetImageUrlByReferenceService implements GetImageUrlByReferenceUseCase {

	private final ImagePort imagePort;
	private final PublicUrlPort publicUrlPort;

	@Override
	public String getUrl(ReferenceType type, long referenceId) {
		var image = imagePort.findByReference(type, referenceId).orElseThrow(
			() -> new IllegalArgumentException("file not Exist")
		);
		return publicUrlPort.urlOf(image.s3Key());
	}
}
