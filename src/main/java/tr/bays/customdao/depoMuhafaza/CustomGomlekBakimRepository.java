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

import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.entity.depoMuhafaza.QGomlekBakim;
import util.Util;

@Repository
public class CustomGomlekBakimRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(GomlekBakim gomlekBakim, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(gomlekBakim.getTalep_tarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakim.gomlekBakim.talep_tarih_saat.eq(gomlekBakim.getTalep_tarih_saat()));
		}

		if(Util.notEmpty(gomlekBakim.getIp())) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakim.gomlekBakim.ip.likeIgnoreCase(gomlekBakim.getIp()+"%"));
		}

		if(gomlekBakim.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakim.gomlekBakim.durum.eq(gomlekBakim.getDurum()));
		}

		if(gomlekBakim.getKullanici_id() != null && gomlekBakim.getKullanici_id().getId() != null && gomlekBakim.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakim.gomlekBakim.kullanici_id.id.eq(gomlekBakim.getKullanici_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlekBakim.gomlekBakim.ip.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<GomlekBakim> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekBakim gomlekBakim, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlekBakim, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekBakim> query = queryFactory.
				select(QGomlekBakim.gomlekBakim).
				from(QGomlekBakim.gomlekBakim);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<GomlekBakim> builder = new PathBuilder<GomlekBakim>(QGomlekBakim.gomlekBakim.getType(), QGomlekBakim.gomlekBakim.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<GomlekBakim> list = (List<GomlekBakim>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<GomlekBakim> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QGomlekBakim.gomlekBakim.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekBakim> query = queryFactory.
				select(QGomlekBakim.gomlekBakim).
				from(QGomlekBakim.gomlekBakim).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<GomlekBakim>) query.fetch();
	}

}

