package com.riwi.vacancies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacancies.services.interfaces.IVacanciesService;
import com.riwi.vacancies.utils.dto.request.VacancyRequest;
import com.riwi.vacancies.utils.dto.response.VacancyResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/vacancy")
@AllArgsConstructor
public class VacancyController {

  @Autowired
  private final IVacanciesService iVacanciesService;

  //Recordar que el método está paginado, se debe requerir la paginación
  //uso requestParam
  @GetMapping
  public ResponseEntity<Page<VacancyResponse>> getAll(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "7") int size
  ){
  
    return ResponseEntity.ok(this.iVacanciesService.getAll(page-1, size));

  }

  @PostMapping
  public ResponseEntity<VacancyResponse> insert(@RequestBody VacancyRequest vacancy) {
    
    return ResponseEntity.ok(this.iVacanciesService.create(vacancy));
  }
}
