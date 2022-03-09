package tr.bays.customdao.calismaGrubu;

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

import tr.bays.entity.calismaGrubu.CalismaGrubu;
import tr.bays.entity.calismaGrubu.QCalismaGrubu;
import util.Util;

@Repository
public class CustomCalismaGrubuRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(CalismaGrubu calismaGrubu, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(calismaGrubu.getAd())) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.ad.likeIgnoreCase(calismaGrubu.getAd()+"%"));
		}

		if(Util.notEmpty(calismaGrubu.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.tarih_saat.eq(calismaGrubu.getTarih_saat()));
		}

		if(calismaGrubu.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.durum.eq(calismaGrubu.getDurum()));
		}

		if(Util.notEmpty(calismaGrubu.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.aciklama.likeIgnoreCase(calismaGrubu.getAciklama()+"%"));
		}

		if(Util.notEmpty(calismaGrubu.getResmi_yazi_url())) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.resmi_yazi_url.likeIgnoreCase(calismaGrubu.getResmi_yazi_url()+"%"));
		}

		if(Util.notEmpty(calismaGrubu.getSonuc_url())) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.sonuc_url.likeIgnoreCase(calismaGrubu.getSonuc_url()+"%"));
		}

		if(calismaGrubu.getFon_id() != null && calismaGrubu.getFon_id().getId() != null && calismaGrubu.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubu.calismaGrubu.fon_id.id.eq(calismaGrubu.getFon_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QCalismaGrubu.calismaGrubu.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QCalismaGrubu.calismaGrubu.aciklama.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QCalismaGrubu.calismaGrubu.resmi_yazi_url.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QCalismaGrubu.calismaGrubu.sonuc_url.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<CalismaGrubu> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, CalismaGrubu calismaGrubu, String sorgu) {
		BooleanExpression booleanExpression = kriter(calismaGrubu, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<CalismaGrubu> query = queryFactory.
				select(QCalismaGrubu.calismaGrubu).
				from(QCalismaGrubu.calismaGrubu);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<CalismaGrubu> builder = new PathBuilder<CalismaGrubu>(QCalismaGrubu.calismaGrubu.getType(), QCalismaGrubu.calismaGrubu.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<CalismaGrubu> list = (List<CalismaGrubu>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<CalismaGrubu> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QCalismaGrubu.calismaGrubu.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<CalismaGrubu> query = queryFactory.
				select(QCalismaGrubu.calismaGrubu).
				from(QCalismaGrubu.calismaGrubu).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<CalismaGrubu>) query.fetch();
	}

}

