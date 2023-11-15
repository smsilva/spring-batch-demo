package com.github.smsilva.batch.demo.boundary;

import com.github.smsilva.batch.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
