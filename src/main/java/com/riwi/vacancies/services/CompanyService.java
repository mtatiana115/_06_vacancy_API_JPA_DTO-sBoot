package com.riwi.vacancies.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacancies.entities.Company;
import com.riwi.vacancies.entities.Vacancy;
import com.riwi.vacancies.repositories.CompanyRepository;
import com.riwi.vacancies.services.interfaces.ICompanyService;
import com.riwi.vacancies.utils.dto.request.CompanyRequest;
import com.riwi.vacancies.utils.dto.response.CompanyResponse;
import com.riwi.vacancies.utils.dto.response.VacancyToCompanyResponse;
import com.riwi.vacancies.utils.exceptions.IdNotFoundExeption;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

  @Autowired
  private final CompanyRepository companyRepository;

  @Override
  public Page<CompanyResponse> getAll(int page, int size) {
    //getAll con pagination
    /**1. Configuramos la paginacion */
    if (page<0) 
      page = 0;
    PageRequest pagination = PageRequest.of(page, size);
    /**2. llamamos el repositorio */
    //return this.companyRepository.findAll(pagination).map(company -> this.entityToResponse(company));
    //Con expresión landa
    return this.companyRepository.findAll(pagination).map(this::entityToResponse);
  }

  @Override
  public CompanyResponse create(CompanyRequest request) {
    //creo primero un metodo para convertir requestToEntity para que lo pueda recibir el método
    /**Convertirmos el request en la entidad */

    Company company = this.requestToEntity(request, new Company());
    //debo de convertir de nuevo usando el método entity to response
    /**Agregamos la entidad en el repositorio y el retorno lo conveertimos en respuesta */
    return this.entityToResponse(this.companyRepository.save(company));



  }

  @Override
  public CompanyResponse update(CompanyRequest request, String id) {
    //validar si el id que llega como parámetro es valido
    Company companyToUpdate = this.find(id);
    //Convertir el request a entidad ya que me llega un request
    Company company= this.requestToEntity(request, companyToUpdate);
    return this.entityToResponse(this.companyRepository.save(company));
  }

  @Override
  public void delete(String id) {
    //Buscamos la compañía a la que corresponde el id
    Company company = this.find(id);
    //Eliminamos
    this.companyRepository.delete(company);
  }

  @Override
  public CompanyResponse getById(String id) {

    //Buscamos la compañia con el id
    Company company = this.find(id);

    //Convertimos la entidad al dto de respuesta y lo retornamos
    return this.entityToResponse(company);
  }

  /**
   * Este método se encargará de convertir una entidad en el dto de respuesta de la entidad
   */

  private CompanyResponse entityToResponse(Company entity){
    CompanyResponse response = new CompanyResponse();
    //convierto todos los atributos de la entidad y se los paso a la respuesta
    //para no convertir manual(hacer los get) utilizo beanUtils para convertir la entidad a una respuesta

    /**Bean utils nos permite hacer una copia de una clase en otra, en este caso toda la entidad de tipo company será copiada con la información requerida por la variable tipo CompanyResponse */
    BeanUtils.copyProperties(entity, response);

    /**
     * stream -> Convierte la lista en colección para poder iterarse
     * map -> Itera toda la lsita y retorna cambios
     * collect -> Crea de nuevo toda la lista que se habia transformado en colección
     */

    response.setVacancies(entity.getVacancies().stream().map(vacancy -> this.vacancyToResponse(vacancy)).collect(Collectors.toList()));
    return response;
  }

  private VacancyToCompanyResponse vacancyToResponse (Vacancy entity){
    VacancyToCompanyResponse response = new VacancyToCompanyResponse();

    BeanUtils.copyProperties(entity, response);

    return response;
  }

  private Company requestToEntity(CompanyRequest entity, Company company){
    company.setContact(entity.getContact());
    company.setLocation(entity.getLocation());
    company.setName(entity.getName());
    company.setVacancies(new ArrayList<>());
    return company;

  }

  //para el findById
  private Company find(String id){
    return this.companyRepository.findById(id).orElseThrow(()-> new IdNotFoundExeption("company"));
  }

}
