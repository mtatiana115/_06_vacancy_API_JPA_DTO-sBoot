package com.riwi.vacancies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacancies.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository <Company, String> {

  
} 