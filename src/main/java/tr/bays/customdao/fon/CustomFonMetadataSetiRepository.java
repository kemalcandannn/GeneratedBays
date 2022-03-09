package tr.bays.customdao.fon;

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

import tr.bays.entity.fon.FonMetadataSeti;
import tr.bays.entity.fon.QFonMetadataSeti;
import util.Util;

@Repository
public class CustomFonMetadataSetiRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(FonMetadataSeti fonMetadataSeti, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(fonMetadataSeti.getMetadata_seti_id() != null && fonMetadataSeti.getMetadata_seti_id().getId() != null && fonMetadataSeti.getMetadata_seti_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonMetadataSeti.fonMetadataSeti.metadata_seti_id.id.eq(fonMetadataSeti.getMetadata_seti_id().getId()));
		}

		if(fonMetadataSeti.getFon_id() != null && fonMetadataSeti.getFon_id().getId() != null && fonMetadataSeti.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonMetadataSeti.fonMetadataSeti.fon_id.id.eq(fonMetadataSeti.getFon_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<FonMetadataSeti> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonMetadataSeti fonMetadataSeti, String sorgu) {
		BooleanExpression booleanExpression = kriter(fonMetadataSeti, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonMetadataSeti> query = queryFactory.
				select(QFonMetadataSeti.fonMetadataSeti).
				from(QFonMetadataSeti.fonMetadataSeti);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<FonMetadataSeti> builder = new PathBuilder<FonMetadataSeti>(QFonMetadataSeti.fonMetadataSeti.getType(), QFonMetadataSeti.fonMetadataSeti.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<FonMetadataSeti> list = (List<FonMetadataSeti>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<FonMetadataSeti> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonMetadataSeti> query = queryFactory.
				select(QFonMetadataSeti.fonMetadataSeti).
				from(QFonMetadataSeti.fonMetadataSeti).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<FonMetadataSeti>) query.fetch();
	}

}

