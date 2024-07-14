package co.com.ps.c24a.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Setter
@Getter
public class Address {

    @Id
    private Long id;

//@Column("person_id") /*Aveces tengo que poner esto por si el sistema no entiende a que se refiere mi atributo ya que quite el guón  */

private Long personId;
private String city;


}
