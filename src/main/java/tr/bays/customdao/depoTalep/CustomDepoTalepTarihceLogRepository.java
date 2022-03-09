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

import tr.bays.entity.depoTalep.DepoTalepTarihceLog;
import tr.bays.entity.depoTalep.QDepoTalepTarihceLog;
import util.Util;

@Repository
public class CustomDepoTalepTarihceLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoTalepTarihceLog depoTalepTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(depoTalepTarihceLog.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.tur.eq(depoTalepTarihceLog.getTur()));
		}

		if(Util.notEmpty(depoTalepTarihceLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.tarih_saat.eq(depoTalepTarihceLog.getTarih_saat()));
		}

		if(Util.notEmpty(depoTalepTarihceLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.ip.likeIgnoreCase(depoTalepTarihceLog.getIp()+"%"));
		}

		if(Util.notEmpty(depoTalepTarihceLog.getSebep())) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.sebep.likeIgnoreCase(depoTalepTarihceLog.getSebep()+"%"));
		}

		if(depoTalepTarihceLog.getIptal_red_sebebi_id() != null && depoTalepTarihceLog.getIptal_red_sebebi_id().getId() != null && depoTalepTarihceLog.getIptal_red_sebebi_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.iptal_red_sebebi_id.id.eq(depoTalepTarihceLog.getIptal_red_sebebi_id().getId()));
		}

		if(depoTalepTarihceLog.getDepo_sorumlusu_id() != null && depoTalepTarihceLog.getDepo_sorumlusu_id().getId() != null && depoTalepTarihceLog.getDepo_sorumlusu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.depo_sorumlusu_id.id.eq(depoTalepTarihceLog.getDepo_sorumlusu_id().getId()));
		}

		if(depoTalepTarihceLog.getBirim_sorumlusu_id() != null && depoTalepTarihceLog.getBirim_sorumlusu_id().getId() != null && depoTalepTarihceLog.getBirim_sorumlusu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.birim_sorumlusu_id.id.eq(depoTalepTarihceLog.getBirim_sorumlusu_id().getId()));
		}

		if(depoTalepTarihceLog.getDepo_talep_id() != null && depoTalepTarihceLog.getDepo_talep_id().getId() != null && depoTalepTarihceLog.getDepo_talep_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepTarihceLog.depoTalepTarihceLog.depo_talep_id.id.eq(depoTalepTarihceLog.getDepo_talep_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepoTalepTarihceLog.depoTalepTarihceLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepoTalepTarihceLog.depoTalepTarihceLog.sebep.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoTalepTarihceLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalepTarihceLog depoTalepTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoTalepTarihceLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalepTarihceLog> query = queryFactory.
				select(QDepoTalepTarihceLog.depoTalepTarihceLog).
				from(QDepoTalepTarihceLog.depoTalepTarihceLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoTalepTarihceLog> builder = new PathBuilder<DepoTalepTarihceLog>(QDepoTalepTarihceLog.depoTalepTarihceLog.getType(), QDepoTalepTarihceLog.depoTalepTarihceLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoTalepTarihceLog> list = (List<DepoTalepTarihceLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoTalepTarihceLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDepoTalepTarihceLog.depoTalepTarihceLog.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalepTarihceLog> query = queryFactory.
				select(QDepoTalepTarihceLog.depoTalepTarihceLog).
				from(QDepoTalepTarihceLog.depoTalepTarihceLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoTalepTarihceLog>) query.fetch();
	}

}

