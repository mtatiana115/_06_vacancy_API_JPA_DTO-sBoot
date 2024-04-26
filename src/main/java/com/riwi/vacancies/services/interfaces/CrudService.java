package com.riwi.vacancies.services.interfaces;

import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID> {

    //RS => Response  RQ=> Request   ID=> tipoId
    //ex. vacancyResponse, VacancyRequest, tipoId Vacancy

    //creo los métodos
    //metodo para paginar de springFramework data domain
    Page<RS> getAll(int page, int size);

    RS create(RQ request);

    //Método para actualizar
    RS update(RQ request, ID id);

    //Método para elimminar
    void delete(ID id);

    //Método para buscar por id
    RS getById(ID id);
  
} 
