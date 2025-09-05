package com.JPA.Spring.JPA.Repository;

import com.JPA.Spring.JPA.enitity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
