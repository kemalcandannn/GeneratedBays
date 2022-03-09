package tr.bays.customdao.teskilat;

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

import tr.bays.entity.teskilat.Birim;
import tr.bays.entity.teskilat.QBirim;
import util.Util;

@Repository
public class CustomBirimRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Birim birim, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(birim.getAd())) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.ad.likeIgnoreCase(birim.getAd()+"%"));
		}

		if(birim.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.aktif.eq(birim.getAktif()));
		}

		if(birim.getUst_birim_id() != null && birim.getUst_birim_id().getId() != null && birim.getUst_birim_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.ust_birim_id.id.eq(birim.getUst_birim_id().getId()));
		}

		if(birim.getArsiv_kurumu_id() != null && birim.getArsiv_kurumu_id().getId() != null && birim.getArsiv_kurumu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.arsiv_kurumu_id.id.eq(birim.getArsiv_kurumu_id().getId()));
		}

		if(birim.getBirim_turu_sozluk_satir_id() != null && birim.getBirim_turu_sozluk_satir_id().getId() != null && birim.getBirim_turu_sozluk_satir_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.birim_turu_sozluk_satir_id.id.eq(birim.getBirim_turu_sozluk_satir_id().getId()));
		}

		if(birim.getYonetici_id() != null && birim.getYonetici_id().getId() != null && birim.getYonetici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QBirim.birim.yonetici_id.id.eq(birim.getYonetici_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QBirim.birim.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Birim> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Birim birim, String sorgu) {
		BooleanExpression booleanExpression = kriter(birim, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Birim> query = queryFactory.
				select(QBirim.birim).
				from(QBirim.birim);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Birim> builder = new PathBuilder<Birim>(QBirim.birim.getType(), QBirim.birim.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Birim> list = (List<Birim>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Birim> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QBirim.birim.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Birim> query = queryFactory.
				select(QBirim.birim).
				from(QBirim.birim).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Birim>) query.fetch();
	}

}

