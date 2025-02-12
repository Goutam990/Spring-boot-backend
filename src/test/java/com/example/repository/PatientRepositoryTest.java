import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.entity.Patient;
import com.example.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class PatientRepositoryTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private Patient patient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientRepository.findById(1L);
        assertTrue(foundPatient.isPresent());
        assertEquals("John Doe", foundPatient.get().getName());
    }

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setAge(25);
        patient.setGender("Female");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientRepository.save(patient);
        assertNotNull(savedPatient);
        assertEquals("Jane Doe", savedPatient.getName());
    }

    @Test
    public void testDeletePatient() {
        Patient patient = new Patient();
        patient.setId(1L);

        doNothing().when(patientRepository).delete(patient);
        patientRepository.delete(patient);
        verify(patientRepository, times(1)).delete(patient);
    }
}