package tr.bays.customdao.arsivSahibi;

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

import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;
import tr.bays.entity.arsivSahibi.QArsivSahibiPersonel;
import util.Util;

@Repository
public class CustomArsivSahibiPersonelRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivSahibiPersonel arsivSahibiPersonel, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivSahibiPersonel.getAd_soyad())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.ad_soyad.likeIgnoreCase(arsivSahibiPersonel.getAd_soyad()+"%"));
		}

		if(Util.notEmpty(arsivSahibiPersonel.getEposta())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.eposta.likeIgnoreCase(arsivSahibiPersonel.getEposta()+"%"));
		}

		if(Util.notEmpty(arsivSahibiPersonel.getKurum_tel_no())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.kurum_tel_no.likeIgnoreCase(arsivSahibiPersonel.getKurum_tel_no()+"%"));
		}

		if(Util.notEmpty(arsivSahibiPersonel.getCep_tel_no())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.cep_tel_no.likeIgnoreCase(arsivSahibiPersonel.getCep_tel_no()+"%"));
		}

		if(Util.notEmpty(arsivSahibiPersonel.getGorev())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.gorev.likeIgnoreCase(arsivSahibiPersonel.getGorev()+"%"));
		}

		if(arsivSahibiPersonel.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.aktif.eq(arsivSahibiPersonel.getAktif()));
		}

		if(arsivSahibiPersonel.getArsiv_sahibi_id() != null && arsivSahibiPersonel.getArsiv_sahibi_id().getId() != null && arsivSahibiPersonel.getArsiv_sahibi_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonel.arsivSahibiPersonel.arsiv_sahibi_id.id.eq(arsivSahibiPersonel.getArsiv_sahibi_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibiPersonel.arsivSahibiPersonel.ad_soyad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibiPersonel.arsivSahibiPersonel.eposta.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibiPersonel.arsivSahibiPersonel.kurum_tel_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibiPersonel.arsivSahibiPersonel.cep_tel_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibiPersonel.arsivSahibiPersonel.gorev.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivSahibiPersonel> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibiPersonel arsivSahibiPersonel, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivSahibiPersonel, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibiPersonel> query = queryFactory.
				select(QArsivSahibiPersonel.arsivSahibiPersonel).
				from(QArsivSahibiPersonel.arsivSahibiPersonel);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivSahibiPersonel> builder = new PathBuilder<ArsivSahibiPersonel>(QArsivSahibiPersonel.arsivSahibiPersonel.getType(), QArsivSahibiPersonel.arsivSahibiPersonel.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivSahibiPersonel> list = (List<ArsivSahibiPersonel>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivSahibiPersonel> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivSahibiPersonel.arsivSahibiPersonel.ad_soyad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibiPersonel> query = queryFactory.
				select(QArsivSahibiPersonel.arsivSahibiPersonel).
				from(QArsivSahibiPersonel.arsivSahibiPersonel).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivSahibiPersonel>) query.fetch();
	}

}

