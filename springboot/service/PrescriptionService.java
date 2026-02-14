package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.PrescriptionDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Prescription;

@Service
public class PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;
	public ResponseEntity<ResponseStructure<Prescription>> createPrescription(Integer medicalRecordId,Prescription prescription){
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Prescription created successfully");
		response.setData(prescriptionDao.create(medicalRecordId,prescription));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Prescription>>> getAllPrescription(){
		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All prescriptions fetched successfully");
		response.setData(prescriptionDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Prescription>> getPrescriptionById(Integer id){
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Prescription fetched successfully");
		response.setData(prescriptionDao.getById(id));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Prescription>> getPrescriptionByMedicalRecord(Integer medicalRecordId){
		ResponseStructure<Prescription> response = new ResponseStructure<Prescription>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Prescription fetched for medical record");
		response.setData(prescriptionDao.getByMedicalRecord(medicalRecordId));
		return new ResponseEntity<ResponseStructure<Prescription>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Prescription>>> getPrescriptionByPatient(Integer patientdId){
		ResponseStructure<List<Prescription>> response = new ResponseStructure<List<Prescription>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Prescriptions fetched for patient");
		response.setData(prescriptionDao.getByPatient(patientdId));
		return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
	}
}
