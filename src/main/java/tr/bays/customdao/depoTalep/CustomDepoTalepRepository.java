package tr.bays.customdao.depoTalep;

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

import tr.bays.entity.depoTalep.DepoTalep;
import tr.bays.entity.depoTalep.QDepoTalep;
import util.Util;

@Repository
public class CustomDepoTalepRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoTalep depoTalep, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(depoTalep.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.tarih_saat.eq(depoTalep.getTarih_saat()));
		}

		if(depoTalep.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.durum.eq(depoTalep.getDurum()));
		}

		if(Util.notEmpty(depoTalep.getIp())) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.ip.likeIgnoreCase(depoTalep.getIp()+"%"));
		}

		if(depoTalep.getTalep_eden_birim_id() != null && depoTalep.getTalep_eden_birim_id().getId() != null && depoTalep.getTalep_eden_birim_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.talep_eden_birim_id.id.eq(depoTalep.getTalep_eden_birim_id().getId()));
		}

		if(depoTalep.getTalep_eden_kullanici_id() != null && depoTalep.getTalep_eden_kullanici_id().getId() != null && depoTalep.getTalep_eden_kullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.talep_eden_kullanici_id.id.eq(depoTalep.getTalep_eden_kullanici_id().getId()));
		}

		if(depoTalep.getTeslim_alan_id() != null && depoTalep.getTeslim_alan_id().getId() != null && depoTalep.getTeslim_alan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.teslim_alan_id.id.eq(depoTalep.getTeslim_alan_id().getId()));
		}

		if(depoTalep.getDepo_gorevlisi_id() != null && depoTalep.getDepo_gorevlisi_id().getId() != null && depoTalep.getDepo_gorevlisi_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.depo_gorevlisi_id.id.eq(depoTalep.getDepo_gorevlisi_id().getId()));
		}

		if(depoTalep.getDepo_id() != null && depoTalep.getDepo_id().getId() != null && depoTalep.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalep.depoTalep.depo_id.id.eq(depoTalep.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepoTalep.depoTalep.ip.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoTalep> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalep depoTalep, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoTalep, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalep> query = queryFactory.
				select(QDepoTalep.depoTalep).
				from(QDepoTalep.depoTalep);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoTalep> builder = new PathBuilder<DepoTalep>(QDepoTalep.depoTalep.getType(), QDepoTalep.depoTalep.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoTalep> list = (List<DepoTalep>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoTalep> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDepoTalep.depoTalep.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalep> query = queryFactory.
				select(QDepoTalep.depoTalep).
				from(QDepoTalep.depoTalep).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoTalep>) query.fetch();
	}

}

