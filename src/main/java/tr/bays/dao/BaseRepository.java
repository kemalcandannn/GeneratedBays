package tr.bays.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import tr.bays.entity.VersionedEntity;

public interface BaseRepository<T extends VersionedEntity> extends JpaRepository<T,Long>, QuerydslPredicateExecutor<T> {
}
