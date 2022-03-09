package tr.bays.customdao.genel;

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

import tr.bays.entity.genel.RolIslem;
import tr.bays.entity.genel.QRolIslem;
import util.Util;

@Repository
public class CustomRolIslemRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(RolIslem rolIslem, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(rolIslem.getRol_id() != null && rolIslem.getRol_id().getId() != null && rolIslem.getRol_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QRolIslem.rolIslem.rol_id.id.eq(rolIslem.getRol_id().getId()));
		}

		if(rolIslem.getIslem_id() != null && rolIslem.getIslem_id().getId() != null && rolIslem.getIslem_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QRolIslem.rolIslem.islem_id.id.eq(rolIslem.getIslem_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<RolIslem> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, RolIslem rolIslem, String sorgu) {
		BooleanExpression booleanExpression = kriter(rolIslem, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<RolIslem> query = queryFactory.
				select(QRolIslem.rolIslem).
				from(QRolIslem.rolIslem);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<RolIslem> builder = new PathBuilder<RolIslem>(QRolIslem.rolIslem.getType(), QRolIslem.rolIslem.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<RolIslem> list = (List<RolIslem>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<RolIslem> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<RolIslem> query = queryFactory.
				select(QRolIslem.rolIslem).
				from(QRolIslem.rolIslem).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<RolIslem>) query.fetch();
	}

}

