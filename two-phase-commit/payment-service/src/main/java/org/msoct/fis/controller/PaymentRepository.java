package org.msoct.fis.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByItem(String item);

}
