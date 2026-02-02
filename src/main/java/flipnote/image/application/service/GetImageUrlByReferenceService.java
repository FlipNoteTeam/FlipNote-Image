package flipnote.image.application.service;

import org.springframework.stereotype.Service;

import flipnote.image.application.port.in.GetImageUrlByReferenceUseCase;
import flipnote.image.application.port.out.DefaultImagePort;
import flipnote.image.application.port.out.ImagePort;
import flipnote.image.application.port.out.PublicUrlPort;
import flipnote.image.domain.model.reference.ReferenceType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetImageUrlByReferenceService implements GetImageUrlByReferenceUseCase {

	private final ImagePort imagePort;
	private final PublicUrlPort publicUrlPort;
	private final DefaultImagePort defaultImagePort;

	@Override
	public String getUrl(ReferenceType type, long referenceId) {
		var image = imagePort.findByReference(type, referenceId);

		// 이미지 없을 시 기본 이미지 출력
		if(image.isEmpty()) {
			return publicUrlPort.urlOf(defaultImagePort.defaultUrl(type));
		}

		return publicUrlPort.urlOf(image.get().s3Key());
	}
}
