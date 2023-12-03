package com.eorhn.bookstore.repository;

import com.eorhn.bookstore.model.entities.TblMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<TblMembers,Long>, JpaSpecificationExecutor {

    Optional<TblMembers> findById(long memberId);

}
