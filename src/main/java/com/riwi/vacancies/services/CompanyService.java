package com.riwi.vacancies.services;

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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public CompanyResponse update(CompanyRequest request, String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public CompanyResponse getById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
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

}
