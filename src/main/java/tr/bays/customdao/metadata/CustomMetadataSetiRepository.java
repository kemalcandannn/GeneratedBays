package tr.bays.customdao.metadata;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import tr.bays.entity.metadata.MetadataSeti;
import tr.bays.entity.metadata.QMetadataSeti;
import util.Util;

@Repository
public class CustomMetadataSetiRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(MetadataSeti metadataSeti, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(metadataSeti.getAd())) {
			booleanExpression = Util.and(booleanExpression,QMetadataSeti.metadataSeti.ad.likeIgnoreCase(metadataSeti.getAd()+"%"));
		}

		if(metadataSeti.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QMetadataSeti.metadataSeti.tur.eq(metadataSeti.getTur()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QMetadataSeti.metadataSeti.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<MetadataSeti> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, MetadataSeti metadataSeti, String sorgu) {
		BooleanExpression booleanExpression = kriter(metadataSeti, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<MetadataSeti> query = queryFactory.
				select(QMetadataSeti.metadataSeti).
				from(QMetadataSeti.metadataSeti);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<MetadataSeti> builder = new PathBuilder<MetadataSeti>(QMetadataSeti.metadataSeti.getType(), QMetadataSeti.metadataSeti.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<MetadataSeti> list = (List<MetadataSeti>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<MetadataSeti> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QMetadataSeti.metadataSeti.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<MetadataSeti> query = queryFactory.
				select(QMetadataSeti.metadataSeti).
				from(QMetadataSeti.metadataSeti).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<MetadataSeti>) query.fetch();
	}

}

