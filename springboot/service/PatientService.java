package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.PatientDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.DuplicateResourceException;

@Service
public class PatientService {
	@Autowired
	private PatientDao patientDao;
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(Patient patient){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient registered successfully");
		response.setData(patientDao.register(patient));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatients(){
		ResponseStructure<List<Patient>> response = new ResponseStructure<List<Patient>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All patients fetched successfully");
		response.setData(patientDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Patient>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> getPatientById(Integer id){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient fetched successfully");
		response.setData(patientDao.getById(id));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> getPatientByPhoneNumber(Long phone){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		Optional<Patient> opt = patientDao.getByPhoneNumber(phone);
		if(!opt.isPresent()) throw new DuplicateResourceException("Phone Number Already Exist");
		else {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Patient fetched successfully");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);			
		}
	}
	public ResponseEntity<ResponseStructure<List<Patient>>> getPatientAgeGreaterThan(Integer age){
		ResponseStructure<List<Patient>> response = new ResponseStructure<List<Patient>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patients fetched with age greater than " + age);
		response.setData(patientDao.getAgeGreaterThan(age));
		return new ResponseEntity<ResponseStructure<List<Patient>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> getPatientByAppointment(Integer appointmentId){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient fetched for appointment");
		response.setData(patientDao.getPatientByAppointment(appointmentId));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> getPatientByMedicalRecord(Integer medicalRecordId){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient fetched for medical record");
		response.setData(patientDao.getPatientByMedicalRecord(medicalRecordId));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(Patient patient){
		ResponseStructure<Patient> response = new ResponseStructure<Patient>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient updated successfully");
		response.setData(patientDao.update(patient));
		return new ResponseEntity<ResponseStructure<Patient>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> deletePatient(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Patient deleted successfully");
		response.setData(patientDao.delete(id));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
}
