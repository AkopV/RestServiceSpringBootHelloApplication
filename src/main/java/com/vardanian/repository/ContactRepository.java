package com.vardanian.repository;

import com.vardanian.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ContactRepository")
public interface ContactRepository extends CrudRepository<Contact, Long> {

}
