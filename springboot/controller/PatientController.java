package jsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Patient;
import jsp.springboot.service.PatientService;

@RestController
@RequestMapping("hospital/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(@RequestBody Patient patient){
		return patientService.registerPatient(patient); 
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatients(){
		return patientService.getAllPatients(); 
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Patient>> getPatientById(@PathVariable Integer id){
		return patientService.getPatientById(id); 
	}
	@GetMapping("/phone/{phone}")
	public ResponseEntity<ResponseStructure<Patient>> getPatientByPhoneNumber(@PathVariable Long phone){
		return patientService.getPatientByPhoneNumber(phone); 
	}
	@GetMapping("/age/{age}")
	public ResponseEntity<ResponseStructure<List<Patient>>> getPatientAgeGreaterThan(@PathVariable Integer age){
		return patientService.getPatientAgeGreaterThan(age); 
	}
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<ResponseStructure<Patient>> getPatientByAppointment(@PathVariable Integer appointmentId){
		return patientService.getPatientByAppointment(appointmentId);
	}
	@GetMapping("/medical-record/{medicalRecordId}")
	public ResponseEntity<ResponseStructure<Patient>> getPatientByMedicalRecord(@PathVariable Integer medicalRecordId){
		return patientService.getPatientByMedicalRecord(medicalRecordId);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestBody Patient patient){
		return patientService.updatePatient(patient); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> updatePatient(@PathVariable Integer id){
		return patientService.deletePatient(id); 
	}
}
