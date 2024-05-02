package com.riwi.vacancies.services.interfaces;

import com.riwi.vacancies.utils.dto.request.VacancyRequest;
import com.riwi.vacancies.utils.dto.response.VacancyResponse;
/**
 * Interface para establecer el contrato con el servicio
 */

public interface IVacanciesService extends CrudService<VacancyRequest, VacancyResponse, Long>{

}
