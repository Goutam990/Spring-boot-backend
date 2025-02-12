import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.entity.Patient;
import com.example.repository.PatientRepository;
import com.example.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class PatientServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setGender("Male");
    }

    @Test
    void testAddPatient() throws Exception {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"age\":30,\"gender\":\"Male\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetPatientById() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(java.util.Optional.of(patient));

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}