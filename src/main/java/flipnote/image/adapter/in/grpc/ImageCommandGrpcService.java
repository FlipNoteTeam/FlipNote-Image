package flipnote.image.adapter.in.grpc;


import org.springframework.grpc.server.service.GrpcService;

import flipnote.image.application.dto.CleanOrphanImagesCommand;
import flipnote.image.application.dto.ConfirmUploadCommand;
import flipnote.image.application.port.in.command.IssuePresignedUrlCommand;
import flipnote.image.application.service.ImageCommandService;
import flipnote.image.grpc.v1.CleanOrphanImagesRequest;
import flipnote.image.grpc.v1.CleanOrphanImagesResponse;
import flipnote.image.grpc.v1.ConfirmUploadRequest;
import flipnote.image.grpc.v1.ConfirmUploadResponse;
import flipnote.image.grpc.v1.ImageCommandServiceGrpc;
import flipnote.image.grpc.v1.IssuePresignedUrlRequest;
import flipnote.image.grpc.v1.IssuePresignedUrlResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ImageCommandGrpcService extends ImageCommandServiceGrpc.ImageCommandServiceImplBase {

	private final ImageCommandService imageService;

	public ImageCommandGrpcService(ImageCommandService imageService) {
		this.imageService = imageService;
	}

	@Override
	public void issuePresignedUrl(IssuePresignedUrlRequest request,
		StreamObserver<IssuePresignedUrlResponse> responseObserver) {
		try {
			var result = imageService.issuePresignedUrl(new IssuePresignedUrlCommand(
				request.getFileName(),
				request.getContentType(),
				request.getContentLength()
			));

			var response = IssuePresignedUrlResponse.newBuilder()
				.setPresignedUrl(result.presignedUrl())
				.setS3Key(result.s3Key())
				.setExpiresAtEpochSec(result.expiresAtEpochSec())
				.build();

			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} catch (IllegalArgumentException e) {
			responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException());
		} catch (Exception e) {
			responseObserver.onError(Status.INTERNAL.withDescription("issuePresignedUrl failed").withCause(e).asRuntimeException());
		}
	}

	@Override
	public void confirmUpload(ConfirmUploadRequest request,
		StreamObserver<ConfirmUploadResponse> responseObserver) {
		try {
			var result = imageService.confirmUpload(new ConfirmUploadCommand(
				request.getS3Key(),
				request.getOriginalFileName()
			));

			var response = ConfirmUploadResponse.newBuilder()
				.setImageId(result.imageId())
				.setImageUrl(result.imageUrl())
				.build();

			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} catch (IllegalStateException e) {
			responseObserver.onError(Status.FAILED_PRECONDITION.withDescription(e.getMessage()).asRuntimeException());
		} catch (Exception e) {
			responseObserver.onError(Status.INTERNAL.withDescription("confirmUpload failed").withCause(e).asRuntimeException());
		}
	}

	@Override
	public void cleanOrphanImages(CleanOrphanImagesRequest request,
		StreamObserver<CleanOrphanImagesResponse> responseObserver) {
		try {
			var result = imageService.cleanOrphanImages(new CleanOrphanImagesCommand(
				request.getBatchSize(),
				request.getMaxBatches()
			));

			var response = CleanOrphanImagesResponse.newBuilder()
				.setProcessedBatches(result.processedBatches())
				.setS3DeletedCount(result.s3DeletedCount())
				.setDbDeletedCount(result.dbDeletedCount())
				.setS3FailedCount(result.s3FailedCount())
				.setDbFailedCount(result.dbFailedCount())
				.build();

			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(Status.INTERNAL.withDescription("cleanOrphanImages failed").withCause(e).asRuntimeException());
		}
	}
}
