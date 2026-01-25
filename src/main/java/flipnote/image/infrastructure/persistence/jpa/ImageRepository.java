package flipnote.image.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import flipnote.image.domain.model.image.Image;
import flipnote.image.infrastructure.persistence.querydsl.ImageRepositoryCustom;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom {
	Optional<Image> findByHash(String fileName);
}
