package co.com.ps.c24a.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//@Table("person") /* Aveces tambien el sistema puede pedirme esta linea, se encarga de decirle que esta clase Persona es la misma tabla persona*/
@Setter
@Getter

public class Person {
    @Id  /*Estos sin son obligatorios siempre*/
    private Long id;
    private String name;

}
