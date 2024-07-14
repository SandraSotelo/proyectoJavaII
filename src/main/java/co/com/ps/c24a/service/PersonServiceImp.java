package co.com.ps.c24a.service;

import co.com.ps.c24a.entity.Person;
import co.com.ps.c24a.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PersonServiceImp implements PersonService{

    private final PersonRepository personRepository;

    @Override
   public Optional<Person> getPersonById(Long id) {
// Debo validar primero que si encuentre el archivo y responder cuando no esté

      //  return personRepository.findById(id);
    return Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontró registro")));
    }

    //Lo anterior es lo mismo que decir:
   /* public Optional<Person> getPersonById(Long id) {

      Optional<Person> result = null;
      try{
        result = personRepository.findById(id);

      }catch (RuntimeException e){
          System.out.println("No se encontró registro");

      }
      return result;

    //return Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontró registro")));
    }*/

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        if(!personRepository.existsById(person.getId())){
            throw new RuntimeException("No se encontró registro");
        }
        person.setId(id);
        return personRepository.save(person); //Normalment

        //save y update se manejan igual, no hay un metodo update
    }

    @Override
    public void deletePerson(Long id) {

        if(!personRepository.existsById(id)){

            throw new RuntimeException("No se encontró esa persona");

        }

        personRepository.deleteById(id);
    }

    @Override
    public List<Person> getPersonAll() {
        List<Person> tmp = new ArrayList<>();
        personRepository.findAll().forEach(tmp::add);
        return tmp;
    }
}
