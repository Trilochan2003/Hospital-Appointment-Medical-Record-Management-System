package jsp.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.service.MedicalRecordService;

@RestController
@RequestMapping("/hospital/report")
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService medicalRecordService;
	@PostMapping("/create/appointment/{id}/medical-record")
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(@PathVariable Integer id,@RequestBody MedicalRecord medicalRecord){
		return medicalRecordService.createRecord(id, medicalRecord);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllRecord(){
		return medicalRecordService.getAllRecord();
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> getRecordById(@PathVariable Integer id){
		return medicalRecordService.getRecordById(id);
	}
	@GetMapping("/patient/{id}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByPatient(@PathVariable Integer id){
		return medicalRecordService.getRecordByPatient(id);
	}
	@GetMapping("/doctor/{id}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByDoctor(@PathVariable Integer id){
		return medicalRecordService.getRecordByDoctor(id);
	}
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByVisitDate(@PathVariable LocalDate date){
		return medicalRecordService.getRecordByVisitDate(date);
	}
	@GetMapping("/appointment/id/{id}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> getRecordByAppointment(@PathVariable Integer id){
		return medicalRecordService.getRecordByAppointment(id);
	}
}
