package com.riwi.vacancies.utils.dto.request;

import com.riwi.vacancies.utils.enums.StatusVacancy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyRequest {
  private String title;
  private String description;
  private StatusVacancy status;
  private String companyId;
}
