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
	}

	@Override
	public void confirmUpload(ConfirmUploadRequest request,
		StreamObserver<ConfirmUploadResponse> responseObserver) {
	}

	@Override
	public void cleanOrphanImages(CleanOrphanImagesRequest request,
		StreamObserver<CleanOrphanImagesResponse> responseObserver) {

	}
}
