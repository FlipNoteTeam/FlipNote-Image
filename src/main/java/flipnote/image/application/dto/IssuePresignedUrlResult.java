package flipnote.image.application.dto;

public record IssuePresignedUrlResult(String presignedUrl, String s3Key, long expiresAtEpochSec) {
}
