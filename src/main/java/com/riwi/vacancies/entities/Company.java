package com.riwi.vacancies.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "company")

//derocadores de loombok
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Company {

  @Id
  //UUID 36 caracteres
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(length = 40, nullable = false)
  private String name;

  @Column(length = 60, nullable = false)
  private String location;

  @Column(length = 15, nullable = false)
  private String contact;

/**
 *@OneToMany: (Uno a muchos) Una empresa puede tener muchas vacantes
 *MapedBy: debemos especificar en que propiedad se está mapeando en la otra entidad
 *Cascade.All: Especificamos el tipo de cascada, All quiere decir que tendrá todos los tipos de cascada 
 *orphanRemoval: Se utiliza para especificar que un objeto huérfano (sin llave foranea) será eliminado
 * true para que se elimine, false para que no se eliminen
 * La cascada es para la base de datos pero orphanremoval es para java
 */
//Excluir en one to many las listas para que no cree un bucle con to Strings
  @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
  @ToString.Exclude //excluimos esta propiedad de toString 
  @EqualsAndHashCode.Exclude //excluimos las propiedad dentro de la lista
  private List<Vacancy> vacancies;
}
