
import co.com.ps.c24a.entity.Person;
import co.com.ps.c24a.repository.PersonRepository;
import co.com.ps.c24a.service.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImp personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPersons() {
        Iterable<Person> persons = List.of(new Person(), new Person());
        when(personRepository.findAll()).thenReturn(persons);

        Iterable<Person> result = personService.getPersonAll();
        assertNotNull(result);
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testGetPersonById() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Sandra");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.getPersonById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());


        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPersonById_NotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personService.getPersonById(1L));
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void testSavePerson() {
        Person person = new Person();
        person.setName("John Doe");
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person result = personService.savePerson(person);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());

        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testDeletePerson() {
        when(personRepository.existsById(1L)).thenReturn(true);
        doNothing().when(personRepository).deleteById(1L);

        personService.deletePerson(1L);
        verify(personRepository, times(1)).existsById(1L);
        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePerson_NotFound() {
        when(personRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> personService.deletePerson(1L));
        verify(personRepository, times(1)).existsById(1L);
        verify(personRepository, never()).deleteById(1L);
    }
}