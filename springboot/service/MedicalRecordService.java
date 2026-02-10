package jsp.springboot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.MedicalDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.MedicalRecord;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalDao medicalDao;
	public ResponseEntity<ResponseStructure<MedicalRecord>> createRecord(Integer id,MedicalRecord medicalRecord){
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Medical record created successfully");
		response.setData(medicalDao.create(id, medicalRecord));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getAllRecord(){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All medical records fetched successfully");
		response.setData(medicalDao.getAll());
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<MedicalRecord>> getRecordById(Integer id){
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical record fetched successfully");
		response.setData(medicalDao.getById(id));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByPatient(Integer id){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical records fetched for patient");
		response.setData(medicalDao.getRecordByPatient(id));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByDoctor(Integer id){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical records fetched for doctor");
		response.setData(medicalDao.getRecordByDoctor(id));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<MedicalRecord>> getRecordByAppointment(Integer id){
		ResponseStructure<MedicalRecord> response = new ResponseStructure<MedicalRecord>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical record fetched for appointment");
		response.setData(medicalDao.getRecordByAppointment(id));
		return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> getRecordByVisitDate(LocalDate date){
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<List<MedicalRecord>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Medical records fetched for visit date " + date);
		response.setData(medicalDao.getRecordByVisitDate(date));
		return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
	}
	
}
