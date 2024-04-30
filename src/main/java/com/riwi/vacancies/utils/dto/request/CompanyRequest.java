package com.riwi.vacancies.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //patron de diseño para crear clases
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
  @Size(min = 0, max = 40, message = "The name exceeds the number of characters allowed")
  @NotBlank(message = "Company name is required")
  private String name;
  @NotBlank(message = "Location name is required")
  private String location;
  @Size(min = 0, max = 15, message = "The contact exceeds the number of characters allowed")
  @NotBlank(message = "contact number is required")
  private String contact;

}
