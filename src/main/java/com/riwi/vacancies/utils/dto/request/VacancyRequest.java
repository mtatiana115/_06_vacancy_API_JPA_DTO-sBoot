package com.riwi.vacancies.utils.dto.request;

import com.riwi.vacancies.utils.enums.StatusVacancy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyRequest {

  @Size(min = 0, max = 255)
  @NotBlank(message = "title is required")
  private String title;

  @NotBlank(message = "Description is required")
  private String description;
  private StatusVacancy status;

  @Size(min = 0, max = 32)
  @NotBlank(message = "ID company is required")
  private String companyId;
}
