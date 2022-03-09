package tr.bays.customdao.loglama;

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

import tr.bays.entity.loglama.KlasorTarihceLog;
import tr.bays.entity.loglama.QKlasorTarihceLog;
import util.Util;

@Repository
public class CustomKlasorTarihceLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(KlasorTarihceLog klasorTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(klasorTarihceLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.tarih_saat.eq(klasorTarihceLog.getTarih_saat()));
		}

		if(Util.notEmpty(klasorTarihceLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.ip.likeIgnoreCase(klasorTarihceLog.getIp()+"%"));
		}

		if(klasorTarihceLog.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.durum.eq(klasorTarihceLog.getDurum()));
		}

		if(Util.notEmpty(klasorTarihceLog.getVeri())) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.veri.likeIgnoreCase(klasorTarihceLog.getVeri()+"%"));
		}

		if(klasorTarihceLog.getKullanici_id() != null && klasorTarihceLog.getKullanici_id().getId() != null && klasorTarihceLog.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.kullanici_id.id.eq(klasorTarihceLog.getKullanici_id().getId()));
		}

		if(klasorTarihceLog.getTeslim_alan_depo_sorumlusu_id() != null && klasorTarihceLog.getTeslim_alan_depo_sorumlusu_id().getId() != null && klasorTarihceLog.getTeslim_alan_depo_sorumlusu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.teslim_alan_depo_sorumlusu_id.id.eq(klasorTarihceLog.getTeslim_alan_depo_sorumlusu_id().getId()));
		}

		if(klasorTarihceLog.getKlasor_id() != null && klasorTarihceLog.getKlasor_id().getId() != null && klasorTarihceLog.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorTarihceLog.klasorTarihceLog.klasor_id.id.eq(klasorTarihceLog.getKlasor_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKlasorTarihceLog.klasorTarihceLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKlasorTarihceLog.klasorTarihceLog.veri.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<KlasorTarihceLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorTarihceLog klasorTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(klasorTarihceLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorTarihceLog> query = queryFactory.
				select(QKlasorTarihceLog.klasorTarihceLog).
				from(QKlasorTarihceLog.klasorTarihceLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<KlasorTarihceLog> builder = new PathBuilder<KlasorTarihceLog>(QKlasorTarihceLog.klasorTarihceLog.getType(), QKlasorTarihceLog.klasorTarihceLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<KlasorTarihceLog> list = (List<KlasorTarihceLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<KlasorTarihceLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QKlasorTarihceLog.klasorTarihceLog.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorTarihceLog> query = queryFactory.
				select(QKlasorTarihceLog.klasorTarihceLog).
				from(QKlasorTarihceLog.klasorTarihceLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<KlasorTarihceLog>) query.fetch();
	}

}

