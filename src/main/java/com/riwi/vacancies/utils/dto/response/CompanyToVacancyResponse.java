package com.riwi.vacancies.utils.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyToVacancyResponse {
  private String id;
  private String name;
  private String location;
  private String contact;
}
