package com.riwi.vacancies.utils.dto.errors;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

//implementar una interfaz de java.io Serializable, con superbuilder para crear clases sin la palabra reservada new(crea un constructor con el super por dentro), poner notación básica

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse implements Serializable{
    private String status;
    private Integer code;
}
