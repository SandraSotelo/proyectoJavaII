package co.com.ps.c24a.controller;

import co.com.ps.c24a.entity.Person;
import co.com.ps.c24a.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id){
        return personService.getPersonById(id).orElseThrow(() -> new RuntimeException("No encontró el Id"));
    }

@PostMapping
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
}

@DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        return personService.updatePerson(id, person);
    }


    @GetMapping()
    public List<Person> getPerson(){
        return personService.getPersonAll();
    }


}
