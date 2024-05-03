package com.riwi.vacancies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacancies.services.interfaces.ICompanyService;
import com.riwi.vacancies.utils.dto.errors.ErrorResponse;
import com.riwi.vacancies.utils.dto.errors.ErrorsResponse;
import com.riwi.vacancies.utils.dto.request.CompanyRequest;
import com.riwi.vacancies.utils.dto.response.CompanyResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  //Colocar una descripción 
  @Operation(summary = "Obtiene toda la lista de de compañias de forma paginada")

  //M´todo para exponer el endpoint de obtener
  @GetMapping
  //uso requestParam para signo de interrogacio, o path variable para slash
  public ResponseEntity<Page<CompanyResponse>> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size){
    return ResponseEntity.ok(this.iCompanyService.getAll(page - 1, size));
  }

  @Operation(summary = "Obtiene toda la lista de de compañias de forma paginada", description = "holaaaaaaaaaaa")
    //documentar los controladores
    @ApiResponse(responseCode = "400",
    description = "Cuando el request no es válido",
    content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = ErrorsResponse.class))
    } )
  @PostMapping
  public ResponseEntity<CompanyResponse> insert (
    @Validated @RequestBody CompanyRequest company){
      return ResponseEntity.ok(this.iCompanyService.create(company));
    }

  @Operation(summary = "Obtiene toda la lista de de compañias de forma paginada")
  @GetMapping(path = "/{id}") //path variable por slash
  public ResponseEntity<CompanyResponse> get(
    @PathVariable String id ){
      return ResponseEntity.ok(this.iCompanyService.getById(id));
    }

  @Operation(summary = "Obtiene toda la lista de de compañias de forma paginada")
     //documentar los controladores
  @ApiResponse(responseCode = "400",
  description = "Cuando el id no es válido",
  content = {
        @Content(mediaType = "application/json",
        schema = @Schema(implementation = ErrorResponse.class))
  } )
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(
    @PathVariable String id){
      this.iCompanyService.delete(id);
      return ResponseEntity.noContent().build();
    }

  //error id no enconytrador
  //errorss cuando algo del request quedaba mal
      //documentar los controladores
  @Operation(summary = "Obtiene toda la lista de de compañias de forma paginada")
  @ApiResponse(responseCode = "400",
  description = "Cuando el request no es válido",
  content = {
        @Content(mediaType = "application/json",
        schema = @Schema(implementation = ErrorsResponse.class))
  } )
  @PutMapping(path = "/{id}")
  public ResponseEntity<CompanyResponse> update(
    @Validated @PathVariable String id,
    @RequestBody CompanyRequest company){
      return ResponseEntity.ok(this.iCompanyService.update(company, id));
    }
  
  
}
