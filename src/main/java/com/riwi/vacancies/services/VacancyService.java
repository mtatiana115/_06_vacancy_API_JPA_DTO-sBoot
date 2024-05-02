package com.riwi.vacancies.services;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacancies.entities.Company;
import com.riwi.vacancies.entities.Vacancy;
import com.riwi.vacancies.repositories.CompanyRepository;
import com.riwi.vacancies.repositories.VacancyRepository;
import com.riwi.vacancies.services.interfaces.IVacanciesService;
import com.riwi.vacancies.utils.dto.request.VacancyRequest;
import com.riwi.vacancies.utils.dto.response.CompanyToVacancyResponse;
import com.riwi.vacancies.utils.dto.response.VacancyResponse;
import com.riwi.vacancies.utils.enums.StatusVacancy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacancyService implements IVacanciesService{


  @Autowired
  private final VacancyRepository vacancyRepository;
  @Autowired
  private final CompanyRepository companyRepository;
  
  @Override
  public Page<VacancyResponse> getAll(int page, int size) {
    if (page<0) page  = 0;
    PageRequest pagination = PageRequest.of(page, size);
    
    /*Obtenemos todas las vacantes, las iteramos para convertir cada una a el dto de respuesta */
    return this.vacancyRepository.findAll(pagination).map(vacancy -> this.entityToResponse(vacancy));
  
  }

  //uso .orelsetrow ya que puede llegar a fallar
  @Override
  public VacancyResponse create(VacancyRequest request) {
    Company company = this.companyRepository.findById(request.getCompanyId()).orElseThrow(() -> new IdNotFoundExeption("Company"));
    Vacancy vacancy = this.requestToVacancy(request, new Vacancy());
    vacancy.setCompany(company);

    return this.entityToResponse(this.vacancyRepository.save(vacancy));
  }

  @Override
  public VacancyResponse update(VacancyRequest request, Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public VacancyResponse getById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

  private VacancyResponse entityToResponse(Vacancy entity){
    /*Creamos instancia del sto de vacante */
    VacancyResponse response = new VacancyResponse();

    /*Copiar toda la entidad en el DTO */
    BeanUtils.copyProperties(entity, response);

    /*Creamos instancia del dto de compañia dentro de la vacante */
    CompanyToVacancyResponse companyDTO = new CompanyToVacancyResponse();

    /*Copio todas las propiedades de la entidad que se encuentra dentro de la entidad (vacante) en el dto de respuesta */
    BeanUtils.copyProperties(entity.getCompany(),companyDTO);

    /*Agrego el dto lleno a la respuesta final */
    response.setCompany(companyDTO);

    return response;
  }

  //convertir para crear y uptade
  private Vacancy requestToVacancy (VacancyRequest request, Vacancy entity){

    entity.setTitle(request.getTitle());
    entity.setDescription(request.getDescription());
    entity.setStatus(StatusVacancy.ACTIVE);

    return entity;
  }
}
