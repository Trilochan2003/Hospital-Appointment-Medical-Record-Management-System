package jsp.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.DoctorDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;
	public ResponseEntity<ResponseStructure<Doctor>> createDoctor(Doctor doctor){
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("All doctors fetched successfully");
		response.setData(doctorDao.add(doctor));
		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctor(){
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor Created Sucessfully");
		response.setData(doctorDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Doctor>> getDoctorById(Integer id){
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor fetched successfully");
		response.setData(doctorDao.getById(id));
		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorBySpecailization(String Specialization){
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors fetched by specialization");
		response.setData(doctorDao.getBySpecialization(Specialization));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByDepartment(String dept){
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors fetched by department");
		response.setData(doctorDao.getByDepartemnt(dept));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> findDoctorsByPatient(Integer patientId) {
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors fetched for patient");
		response.setData(doctorDao.findDoctorsByPatient(patientId));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> getDoctorByAvailableDays(String day){
		ResponseStructure<List<Doctor>> response = new ResponseStructure<List<Doctor>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctors available on " + day + " fetched successfully");
		response.setData(doctorDao.getByDays(day));
		return new ResponseEntity<ResponseStructure<List<Doctor>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Doctor>> getDoctorByAppointment(Integer appointmentId){
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor fetched for appointment");
		response.setData(doctorDao.getDoctorByAppointment(appointmentId));
		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(Doctor doctor){
		ResponseStructure<Doctor> response = new ResponseStructure<Doctor>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor updated successfully");
		response.setData(doctorDao.update(doctor));
		return new ResponseEntity<ResponseStructure<Doctor>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Doctor deleted successfully");
		response.setData(doctorDao.delete(id));
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
}
