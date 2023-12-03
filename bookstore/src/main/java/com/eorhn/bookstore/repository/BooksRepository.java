package com.eorhn.bookstore.repository;

import com.eorhn.bookstore.model.entities.TblBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<TblBooks,Long>, JpaSpecificationExecutor {

    Optional<TblBooks> findById(long bookId);
}
