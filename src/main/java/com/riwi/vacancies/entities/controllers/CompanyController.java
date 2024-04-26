package com.riwi.vacancies.entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacancies.services.interfaces.ICompanyService;
import com.riwi.vacancies.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;

//Para indicar que todos los m+etodos van a reponser en restcontoller osea json
@RestController
//Para que los endpoints sean genéricos
@RequestMapping(path = "/company")
@AllArgsConstructor
public class CompanyController {

  @Autowired
  //inyecto final con CompanyService, el final siempre pide constructor
  private final ICompanyService iCompanyService;

  //M´todo para exponer el endpoint de obtener
  @GetMapping
  //uso requestParam para signo de interrogacio, o path variable para slash
  public ResponseEntity<Page<CompanyResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
    return ResponseEntity.ok(this.iCompanyService.getAll(page - 1, size));
  }
}
