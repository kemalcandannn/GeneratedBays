package tr.bays.customdao.defter;

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

import tr.bays.entity.defter.DefterSayfa;
import tr.bays.entity.defter.QDefterSayfa;
import util.Util;

@Repository
public class CustomDefterSayfaRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DefterSayfa defterSayfa, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(defterSayfa.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfa.defterSayfa.gizlilik.eq(defterSayfa.getGizlilik()));
		}

		if(defterSayfa.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfa.defterSayfa.tur.eq(defterSayfa.getTur()));
		}

		if(Util.notEmpty(defterSayfa.getSayfa_no())) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfa.defterSayfa.sayfa_no.likeIgnoreCase(defterSayfa.getSayfa_no()+"%"));
		}

		if(defterSayfa.getDefter_id() != null && defterSayfa.getDefter_id().getId() != null && defterSayfa.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfa.defterSayfa.defter_id.id.eq(defterSayfa.getDefter_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterSayfa.defterSayfa.sayfa_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DefterSayfa> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfa defterSayfa, String sorgu) {
		BooleanExpression booleanExpression = kriter(defterSayfa, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfa> query = queryFactory.
				select(QDefterSayfa.defterSayfa).
				from(QDefterSayfa.defterSayfa);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DefterSayfa> builder = new PathBuilder<DefterSayfa>(QDefterSayfa.defterSayfa.getType(), QDefterSayfa.defterSayfa.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DefterSayfa> list = (List<DefterSayfa>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DefterSayfa> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefterSayfa.defterSayfa.sayfa_no.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfa> query = queryFactory.
				select(QDefterSayfa.defterSayfa).
				from(QDefterSayfa.defterSayfa).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DefterSayfa>) query.fetch();
	}

}

