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

import tr.bays.entity.teskilat.ArsivKurumuAlan;
import tr.bays.entity.teskilat.QArsivKurumuAlan;
import util.Util;

@Repository
public class CustomArsivKurumuAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivKurumuAlan arsivKurumuAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivKurumuAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QArsivKurumuAlan.arsivKurumuAlan.deger.likeIgnoreCase(arsivKurumuAlan.getDeger()+"%"));
		}

		if(arsivKurumuAlan.getArsiv_kurumu_id() != null && arsivKurumuAlan.getArsiv_kurumu_id().getId() != null && arsivKurumuAlan.getArsiv_kurumu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivKurumuAlan.arsivKurumuAlan.arsiv_kurumu_id.id.eq(arsivKurumuAlan.getArsiv_kurumu_id().getId()));
		}

		if(arsivKurumuAlan.getAlan_id() != null && arsivKurumuAlan.getAlan_id().getId() != null && arsivKurumuAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivKurumuAlan.arsivKurumuAlan.alan_id.id.eq(arsivKurumuAlan.getAlan_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivKurumuAlan.arsivKurumuAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivKurumuAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivKurumuAlan arsivKurumuAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivKurumuAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivKurumuAlan> query = queryFactory.
				select(QArsivKurumuAlan.arsivKurumuAlan).
				from(QArsivKurumuAlan.arsivKurumuAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivKurumuAlan> builder = new PathBuilder<ArsivKurumuAlan>(QArsivKurumuAlan.arsivKurumuAlan.getType(), QArsivKurumuAlan.arsivKurumuAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivKurumuAlan> list = (List<ArsivKurumuAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivKurumuAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivKurumuAlan.arsivKurumuAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivKurumuAlan> query = queryFactory.
				select(QArsivKurumuAlan.arsivKurumuAlan).
				from(QArsivKurumuAlan.arsivKurumuAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivKurumuAlan>) query.fetch();
	}

}

