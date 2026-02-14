package jsp.springboot.controller;

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
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Prescription;
import jsp.springboot.service.PrescriptionService;

@RestController
@RequestMapping("/hospital/prescription")
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;
	@PostMapping("/generate/id/{medicalRecordId}")
	public ResponseEntity<ResponseStructure<Prescription>> createAppointment(@PathVariable Integer medicalRecordId ,@RequestBody Prescription prescription){
		return prescriptionService.createPrescription(medicalRecordId,prescription);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAllPrescription(){
		return prescriptionService.getAllPrescription();
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Prescription>> getPrescriptionById(@PathVariable Integer id){
		return prescriptionService.getPrescriptionById(id);
	}
	@GetMapping("/medical-record/{medicalRecordId}")
	public ResponseEntity<ResponseStructure<Prescription>> getPrescriptionByMedicalRecord(@PathVariable Integer medicalRecordId){
		return prescriptionService.getPrescriptionByMedicalRecord(medicalRecordId);
	}
	@GetMapping("/patient/{patientdId}")
	public ResponseEntity<ResponseStructure<List<Prescription>>> getPrescriptionByPatient(@PathVariable Integer patientdId){
		return prescriptionService.getPrescriptionByPatient(patientdId);
	}
}
