package com.eventura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventura.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
