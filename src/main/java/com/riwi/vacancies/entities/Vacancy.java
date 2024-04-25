package com.riwi.vacancies.entities;

import com.riwi.vacancies.utils.enums.StatusVacancy;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;

  //crear un enumerado en java, creo folder util/enums hermana de entities, class StatusVacancy cmabio la palavra class por enum
  //Reconfigura mi enum y lo importo, borro el anterios
  @Enumerated(EnumType.STRING) //Especificar que el enumerado será de tipo String
  private StatusVacancy status;

  /**
   * @ManyToOne: Muchas vacantes puedes pertenecer a una empresa en entidad debil
   */
  @ManyToOne(fetch = FetchType.LAZY) //dejar la debil siempre lazy para que solo haga el join cuando yo lo especifique
  //especificar la llave foranea(name, reference)
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;
}
