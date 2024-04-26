package com.riwi.vacancies.utils.dto.response;

import com.riwi.vacancies.utils.enums.StatusVacancy;

public class VacancyResponse {
  private Long id;
  private String title;
  private String description;
  private StatusVacancy status;
  private CompanyToVacancyResponse company;
}
