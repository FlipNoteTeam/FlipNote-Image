package flipnote.image.infrastructure.persistence.querydsl;

import static flipnote.image.domain.model.image.QImage.*;
import static flipnote.image.domain.model.reference.QImageRef.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import flipnote.image.domain.model.image.Image;
import flipnote.image.domain.model.image.QImage;
import flipnote.image.domain.model.reference.Reference;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Image> findImageByImageRef_Reference(Reference reference) {

		BooleanBuilder where = new BooleanBuilder()
			.and(imageRef.reference.type.eq(reference.getType()))
			.and(imageRef.reference.id.eq(reference.getId()));

		Image image = queryFactory
			.select(QImage.image)
			.from(imageRef)
			.join(imageRef.image, QImage.image)
			.where(where)
			.fetchOne();

		return Optional.ofNullable(image);
	}

	@Override
	public List<Image> findImageByImageRef_NotExist(Long lastId, int batchSize) {

		BooleanBuilder where = new BooleanBuilder();

		if(lastId != null) {
			where.and(image.id.lt(lastId));
		}

		/**
		 * 참조하고 있지 않는 이미지 찾기
		 * select 1 ...
		 */
		BooleanExpression findNotReferenceImage = JPAExpressions.selectOne()
			.from(imageRef)
			.where(imageRef.image.eq(image))
			.notExists();

		where.and(findNotReferenceImage);

		return queryFactory
			.selectFrom(image)
			.where(where)
			.orderBy(image.id.desc())
			.limit(batchSize)
			.fetch();

	}

}
