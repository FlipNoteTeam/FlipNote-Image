package flipnote.image.application.dto;

public record IssuePresignedUrlCommand(String fileName, String contentType, long contentLength) {}

