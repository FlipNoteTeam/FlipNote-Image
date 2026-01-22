package flipnote.image.application.dto;

public record ConfirmUploadCommand(String s3Key, String originalFileName) {
}
