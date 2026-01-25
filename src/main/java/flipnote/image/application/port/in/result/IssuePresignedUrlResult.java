package flipnote.image.application.port.in.result;

import java.net.URL;

public record IssuePresignedUrlResult(Long imageRefId, String presignedUrl) {
}
