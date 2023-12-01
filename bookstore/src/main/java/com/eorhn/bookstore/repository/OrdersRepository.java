package com.eorhn.bookstore.repository;

import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.entities.TblOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<TblOrders,Long>, JpaSpecificationExecutor {

}
