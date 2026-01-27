package flipnote.image.application.service;

import org.springframework.stereotype.Service;

import flipnote.image.application.port.in.ActivateImageUseCase;
import flipnote.image.application.port.out.ImagePort;
import flipnote.image.application.port.out.ImageRefPort;
import flipnote.image.domain.model.image.ImageMeta;
import flipnote.image.domain.model.reference.ReferenceType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivateImageService implements ActivateImageUseCase {

	private final ImagePort imagePort;
	private final ImageRefPort imageRefPort;

	/**
	 * 이미지 활성화
	 * @param imageRefId 이미지 참조 아이디
	 * @param referenceType 참조 타입
	 * @param referenceId 참조 아이디
	 */
	@Override
	public ImageMeta activateImage(Long imageRefId, ReferenceType referenceType, Long referenceId) {
		//이미지 참조 활성화
		imageRefPort.activate(imageRefId, referenceType, referenceId);

		//이미지 참조로부터 이미지 아이디 조회
		Long imageId = imageRefPort.getImageIdByRefId(imageRefId);
		// 위의 두가지를 분리한 이유는 범용성 때문

		imagePort.findById(imageId);

		return null;
	}
}
