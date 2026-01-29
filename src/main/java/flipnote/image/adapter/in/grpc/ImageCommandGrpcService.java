package flipnote.image.adapter.in.grpc;


import org.springframework.grpc.server.service.GrpcService;

import flipnote.image.application.port.in.GetImageUrlByReferenceUseCase;
import flipnote.image.domain.model.reference.ReferenceType;
import flipnote.image.grpc.v1.CleanOrphanImagesRequest;
import flipnote.image.grpc.v1.CleanOrphanImagesResponse;
import flipnote.image.grpc.v1.ConfirmUploadRequest;
import flipnote.image.grpc.v1.ConfirmUploadResponse;
import flipnote.image.grpc.v1.GetUrlByReferenceRequest;
import flipnote.image.grpc.v1.GetUrlByReferenceResponse;
import flipnote.image.grpc.v1.ImageCommandServiceGrpc;
import flipnote.image.grpc.v1.Type;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

@GrpcService
@RequiredArgsConstructor
public class ImageCommandGrpcService extends ImageCommandServiceGrpc.ImageCommandServiceImplBase {

	private final GetImageUrlByReferenceUseCase getImageUrlByReferenceUseCase;

	/**
	 * 참조 타입 및 아이디를 통해 url 조회
	 * @param request
	 * @param responseObserver
	 */
	@Override
	public void getUrlByReference(GetUrlByReferenceRequest request,
		StreamObserver<GetUrlByReferenceResponse> responseObserver) {
		try {
			ReferenceType type = mapType(request.getReferenceType());
			//url 조회
			String url = getImageUrlByReferenceUseCase.getUrl(type, request.getReferenceId());

			GetUrlByReferenceResponse res = GetUrlByReferenceResponse.newBuilder()
				.setImageUrl(url)
				.build();

			responseObserver.onNext(res);
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(e);
		}
	}

	@Override
	public void confirmUpload(ConfirmUploadRequest request,
		StreamObserver<ConfirmUploadResponse> responseObserver) {
	}

	@Override
	public void cleanOrphanImages(CleanOrphanImagesRequest request,
		StreamObserver<CleanOrphanImagesResponse> responseObserver) {

	}

	/**
	 * 타입 변환
	 * @param type
	 * @return
	 */
	private ReferenceType mapType(Type type) {
		return switch (type) {
			case USER -> ReferenceType.USER;
			case GROUP -> ReferenceType.GROUP;
			case CARD_SET -> ReferenceType.CARD_SET;
			default -> throw new IllegalArgumentException("INVALID_REFERENCE_TYPE");
		};
	}
}
