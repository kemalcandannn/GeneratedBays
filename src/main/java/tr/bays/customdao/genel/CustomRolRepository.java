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

import tr.bays.entity.genel.Rol;
import tr.bays.entity.genel.QRol;
import util.Util;

@Repository
public class CustomRolRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Rol rol, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(rol.getAd())) {
			booleanExpression = Util.and(booleanExpression,QRol.rol.ad.likeIgnoreCase(rol.getAd()+"%"));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QRol.rol.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Rol> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Rol rol, String sorgu) {
		BooleanExpression booleanExpression = kriter(rol, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Rol> query = queryFactory.
				select(QRol.rol).
				from(QRol.rol);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Rol> builder = new PathBuilder<Rol>(QRol.rol.getType(), QRol.rol.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Rol> list = (List<Rol>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Rol> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QRol.rol.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Rol> query = queryFactory.
				select(QRol.rol).
				from(QRol.rol).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Rol>) query.fetch();
	}

}

