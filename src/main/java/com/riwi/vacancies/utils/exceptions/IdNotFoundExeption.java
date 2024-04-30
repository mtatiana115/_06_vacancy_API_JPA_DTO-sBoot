package com.riwi.vacancies.utils.exceptions;

/**
 * RuntimeException es la clase general de errores de Java
 * La emplearemos para utilizar su contructor y generar errores
 */
public class IdNotFoundExeption extends RuntimeException{
    private static final String ERROR_MESSGE = "No hay registros en la entidad %s con el id suministrado";

    /**
     * Utilizamos el constructor de RuntimeException y enviamos el mensaje
     * inyectando el nombre de la entidad
     */
    //Creo un contrustor a mano
    public IdNotFoundExeption(String nameEntity){
        //Para inyectar la variable utilizo el método super
        super(String.format(ERROR_MESSGE, nameEntity));

    }
}
