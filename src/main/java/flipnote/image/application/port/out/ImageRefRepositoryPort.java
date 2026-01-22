package flipnote.image.application.port.out;

public interface ImageRefRepositoryPort {
    boolean existsByImageId(long imageId);
    // confirm 시점에 ref 생성/연결이 필요하면 메서드 추가
}
