package com.mirzoiev.testApp.repository;

import com.mirzoiev.testApp.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Additional Column repository class
 * which shows a different execution approach
 * native sql query
 *
 * @author R.M.
 * @since 15.07.2022
 * @see JpaRepository
 */
@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    @Query(value = "SELECT * FROM COLUMN_ENTITY", nativeQuery = true)
    List<ColumnEntity> getAllColumns();

}
