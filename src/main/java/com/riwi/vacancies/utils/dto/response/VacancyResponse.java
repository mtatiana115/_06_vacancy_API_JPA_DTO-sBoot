package com.riwi.vacancies.utils.dto.response;

import com.riwi.vacancies.utils.enums.StatusVacancy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyResponse {
  private Long id;
  private String title;
  private String description;
  private StatusVacancy status;
  private CompanyToVacancyResponse company;
}
