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

import tr.bays.entity.fon.Fon;
import tr.bays.entity.fon.QFon;
import util.Util;

@Repository
public class CustomFonRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Fon fon, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(fon.getAd())) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.ad.likeIgnoreCase(fon.getAd()+"%"));
		}

		if(fon.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.gizlilik.eq(fon.getGizlilik()));
		}

		if(fon.getGomlek_defter_tarih_tur() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.gomlek_defter_tarih_tur.eq(fon.getGomlek_defter_tarih_tur()));
		}

		if(Util.notEmpty(fon.getTarih())) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.tarih.eq(fon.getTarih()));
		}

		if(Util.notEmpty(fon.getOnay_tarihi())) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.onay_tarihi.eq(fon.getOnay_tarihi()));
		}

		if(fon.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.tur.eq(fon.getTur()));
		}

		if(fon.getArsiv_kurumu_id() != null && fon.getArsiv_kurumu_id().getId() != null && fon.getArsiv_kurumu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.arsiv_kurumu_id.id.eq(fon.getArsiv_kurumu_id().getId()));
		}

		if(fon.getArsiv_sahibi_id() != null && fon.getArsiv_sahibi_id().getId() != null && fon.getArsiv_sahibi_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.arsiv_sahibi_id.id.eq(fon.getArsiv_sahibi_id().getId()));
		}

		if(fon.getUst_fon_id() != null && fon.getUst_fon_id().getId() != null && fon.getUst_fon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFon.fon.ust_fon_id.id.eq(fon.getUst_fon_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QFon.fon.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Fon> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Fon fon, String sorgu) {
		BooleanExpression booleanExpression = kriter(fon, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Fon> query = queryFactory.
				select(QFon.fon).
				from(QFon.fon);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Fon> builder = new PathBuilder<Fon>(QFon.fon.getType(), QFon.fon.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Fon> list = (List<Fon>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Fon> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QFon.fon.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Fon> query = queryFactory.
				select(QFon.fon).
				from(QFon.fon).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Fon>) query.fetch();
	}

}

