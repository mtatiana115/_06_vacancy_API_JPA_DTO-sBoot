package com.riwi.vacancies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.vacancies.entities.Vacancy;

public interface VacancyRepository extends JpaRepository <Vacancy, Long>{

  
}
