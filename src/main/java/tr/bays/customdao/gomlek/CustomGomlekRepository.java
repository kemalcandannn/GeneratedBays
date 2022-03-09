package tr.bays.customdao.gomlek;

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

import tr.bays.entity.gomlek.Gomlek;
import tr.bays.entity.gomlek.QGomlek;
import util.Util;

@Repository
public class CustomGomlekRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Gomlek gomlek, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(gomlek.getTranskripsiyon())) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.transkripsiyon.likeIgnoreCase(gomlek.getTranskripsiyon()+"%"));
		}

		if(gomlek.getTarih_tur() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.tarih_tur.eq(gomlek.getTarih_tur()));
		}

		if(Util.notEmpty(gomlek.getAy())) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.ay.likeIgnoreCase(gomlek.getAy()+"%"));
		}

		if(Util.notEmpty(gomlek.getGun())) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.gun.likeIgnoreCase(gomlek.getGun()+"%"));
		}

		if(Util.notEmpty(gomlek.getYil())) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.yil.likeIgnoreCase(gomlek.getYil()+"%"));
		}

		if(gomlek.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.gizlilik.eq(gomlek.getGizlilik()));
		}

		if(gomlek.getRestorasyon_ihtiyaci() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.restorasyon_ihtiyaci.eq(gomlek.getRestorasyon_ihtiyaci()));
		}

		if(Util.notEmpty(gomlek.getOzel_no())) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.ozel_no.likeIgnoreCase(gomlek.getOzel_no()+"%"));
		}

		if(gomlek.getOzel_no_turu_id() != null && gomlek.getOzel_no_turu_id().getId() != null && gomlek.getOzel_no_turu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.ozel_no_turu_id.id.eq(gomlek.getOzel_no_turu_id().getId()));
		}

		if(gomlek.getKlasor_id() != null && gomlek.getKlasor_id().getId() != null && gomlek.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlek.gomlek.klasor_id.id.eq(gomlek.getKlasor_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlek.gomlek.transkripsiyon.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlek.gomlek.ay.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlek.gomlek.gun.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlek.gomlek.yil.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlek.gomlek.ozel_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Gomlek> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Gomlek gomlek, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlek, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Gomlek> query = queryFactory.
				select(QGomlek.gomlek).
				from(QGomlek.gomlek);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Gomlek> builder = new PathBuilder<Gomlek>(QGomlek.gomlek.getType(), QGomlek.gomlek.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Gomlek> list = (List<Gomlek>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Gomlek> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QGomlek.gomlek.transkripsiyon.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Gomlek> query = queryFactory.
				select(QGomlek.gomlek).
				from(QGomlek.gomlek).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Gomlek>) query.fetch();
	}

}

