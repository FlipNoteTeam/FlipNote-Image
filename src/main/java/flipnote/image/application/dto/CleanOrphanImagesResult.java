package flipnote.image.application.dto;

public record CleanOrphanImagesResult(int processedBatches, int s3DeletedCount, int dbDeletedCount, int s3FailedCount,
									  int dbFailedCount) {
}
