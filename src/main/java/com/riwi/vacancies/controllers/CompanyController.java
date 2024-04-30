package com.riwi.vacancies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacancies.services.interfaces.ICompanyService;
import com.riwi.vacancies.utils.dto.request.CompanyRequest;
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

  @PostMapping
  public ResponseEntity<CompanyResponse> insert (
    @Validated @RequestBody CompanyRequest company){
      return ResponseEntity.ok(this.iCompanyService.create(company));
    }

  @GetMapping(path = "/{id}") //path variable por slash
  public ResponseEntity<CompanyResponse> get(
    @PathVariable String id ){
      return ResponseEntity.ok(this.iCompanyService.getById(id));
    }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Void> delete(
    @PathVariable String id){
      this.iCompanyService.delete(id);
      return ResponseEntity.noContent().build();
    }
  
  @PutMapping(path = "/{id}")
  public ResponseEntity<CompanyResponse> update(
    @Validated @PathVariable String id,
    @RequestBody CompanyRequest company){
      return ResponseEntity.ok(this.iCompanyService.update(company, id));
    }
  
  
}
