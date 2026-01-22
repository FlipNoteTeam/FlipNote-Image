package flipnote.image.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flipnote.image.application.dto.CleanOrphanImagesCommand;
import flipnote.image.application.dto.CleanOrphanImagesResult;
import flipnote.image.application.dto.ConfirmUploadCommand;
import flipnote.image.application.dto.ConfirmUploadResult;
import flipnote.image.application.dto.IssuePresignedUrlCommand;
import flipnote.image.application.dto.IssuePresignedUrlResult;
import flipnote.image.application.port.out.ImageRefRepositoryPort;
import flipnote.image.application.port.out.ImageRepositoryPort;
import flipnote.image.application.port.out.ImageStoragePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageCommandService {

	private final ImageRepositoryPort imageRepository;
	private final ImageRefRepositoryPort imageRefRepository;
	private final ImageStoragePort imageStorage;

	/**
	 * 1) Presigned URL 발급
	 * - DB에는 아직 안 넣고(선택), s3Key만 발급해서 클라이언트가 업로드하도록
	 */
	@Transactional(readOnly = true)
	public IssuePresignedUrlResult issuePresignedUrl(IssuePresignedUrlCommand cmd) {
		return null;
	}

	/**
	 * 2) 업로드 확정
	 * - 여기서 DB에 Image를 만들고(혹은 status 변경) 반환
	 */
	@Transactional
	public ConfirmUploadResult confirmUpload(ConfirmUploadCommand cmd) {
		return null;
	}

	/**
	 * 3) 고아 이미지 정리
	 * - “S3 삭제는 트랜잭션 밖” + “DB 삭제는 짧게”
	 * - 네가 올린 방식 그대로 gRPC에서도 적용 가능
	 */
	public CleanOrphanImagesResult cleanOrphanImages(CleanOrphanImagesCommand cmd) {
		return null;
	}

	@Transactional
	protected void hardDeleteImageTx(long imageId) {
	}

	private static String extractExt(String fileName) {
		return null;
	}
}
