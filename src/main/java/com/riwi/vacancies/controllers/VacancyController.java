package com.riwi.vacancies.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  //id dinamico
  @GetMapping(path = "/{id}")
  public ResponseEntity<VacancyResponse> get(
    @PathVariable Long id
  ){
    return ResponseEntity.ok(this.iVacanciesService.getById(id));
  }

  @PostMapping
  public ResponseEntity<VacancyResponse> insert(@Validated @RequestBody VacancyRequest vacancy) {
    
    return ResponseEntity.ok(this.iVacanciesService.create(vacancy));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Map<String, String>> delete(
    @PathVariable Long id
  ){
    //Creamos el mapa
    //con hashmap nos ahorramos hacer la clase dto, y maneja poca info
    Map<String, String> response = new HashMap<>();
    
    response.put("message", "vacancy successfully removed");

    this.iVacanciesService.delete(id);
    
    return ResponseEntity.ok(response);
  }
}
