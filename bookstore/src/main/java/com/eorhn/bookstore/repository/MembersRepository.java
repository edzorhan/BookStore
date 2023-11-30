package com.eorhn.bookstore.repository;

import com.eorhn.bookstore.model.entities.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<Members,Long>, JpaSpecificationExecutor {

    Members findBymemberId(long memberId);
}
