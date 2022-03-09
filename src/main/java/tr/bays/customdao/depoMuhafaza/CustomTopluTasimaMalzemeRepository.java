package tr.bays.customdao.depoMuhafaza;

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

import tr.bays.entity.depoMuhafaza.TopluTasimaMalzeme;
import tr.bays.entity.depoMuhafaza.QTopluTasimaMalzeme;
import util.Util;

@Repository
public class CustomTopluTasimaMalzemeRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(TopluTasimaMalzeme topluTasimaMalzeme, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(topluTasimaMalzeme.getEski_konum())) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.eski_konum.likeIgnoreCase(topluTasimaMalzeme.getEski_konum()+"%"));
		}

		if(Util.notEmpty(topluTasimaMalzeme.getYeni_konum())) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.yeni_konum.likeIgnoreCase(topluTasimaMalzeme.getYeni_konum()+"%"));
		}

		if(topluTasimaMalzeme.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.tur.eq(topluTasimaMalzeme.getTur()));
		}

		if(topluTasimaMalzeme.getKlasor_id() != null && topluTasimaMalzeme.getKlasor_id().getId() != null && topluTasimaMalzeme.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.klasor_id.id.eq(topluTasimaMalzeme.getKlasor_id().getId()));
		}

		if(topluTasimaMalzeme.getDefter_id() != null && topluTasimaMalzeme.getDefter_id().getId() != null && topluTasimaMalzeme.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.defter_id.id.eq(topluTasimaMalzeme.getDefter_id().getId()));
		}

		if(topluTasimaMalzeme.getArsiv_materyali_id() != null && topluTasimaMalzeme.getArsiv_materyali_id().getId() != null && topluTasimaMalzeme.getArsiv_materyali_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.arsiv_materyali_id.id.eq(topluTasimaMalzeme.getArsiv_materyali_id().getId()));
		}

		if(topluTasimaMalzeme.getIslenmemis_klasor_id() != null && topluTasimaMalzeme.getIslenmemis_klasor_id().getId() != null && topluTasimaMalzeme.getIslenmemis_klasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.islenmemis_klasor_id.id.eq(topluTasimaMalzeme.getIslenmemis_klasor_id().getId()));
		}

		if(topluTasimaMalzeme.getToplu_tasima_id() != null && topluTasimaMalzeme.getToplu_tasima_id().getId() != null && topluTasimaMalzeme.getToplu_tasima_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasimaMalzeme.topluTasimaMalzeme.toplu_tasima_id.id.eq(topluTasimaMalzeme.getToplu_tasima_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QTopluTasimaMalzeme.topluTasimaMalzeme.eski_konum.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QTopluTasimaMalzeme.topluTasimaMalzeme.yeni_konum.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<TopluTasimaMalzeme> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, TopluTasimaMalzeme topluTasimaMalzeme, String sorgu) {
		BooleanExpression booleanExpression = kriter(topluTasimaMalzeme, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<TopluTasimaMalzeme> query = queryFactory.
				select(QTopluTasimaMalzeme.topluTasimaMalzeme).
				from(QTopluTasimaMalzeme.topluTasimaMalzeme);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<TopluTasimaMalzeme> builder = new PathBuilder<TopluTasimaMalzeme>(QTopluTasimaMalzeme.topluTasimaMalzeme.getType(), QTopluTasimaMalzeme.topluTasimaMalzeme.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<TopluTasimaMalzeme> list = (List<TopluTasimaMalzeme>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<TopluTasimaMalzeme> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QTopluTasimaMalzeme.topluTasimaMalzeme.eski_konum.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<TopluTasimaMalzeme> query = queryFactory.
				select(QTopluTasimaMalzeme.topluTasimaMalzeme).
				from(QTopluTasimaMalzeme.topluTasimaMalzeme).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<TopluTasimaMalzeme>) query.fetch();
	}

}

