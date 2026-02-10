package jsp.springboot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.AppointmentDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;

@Service
public class AppointmentService {
	@Autowired
	private AppointmentDao appointmentDao;
	
	public ResponseEntity<ResponseStructure<Appointment>> createAppointment(Appointment appointment){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Appointment created successfully");
		response.setData(appointmentDao.create(appointment));
		return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments(){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All appointments fetched successfully");
		response.setData(appointmentDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Appointment>> getAppointmentById(Integer id){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment created");
		response.setData(appointmentDao.getById(id));
		return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDate(LocalDate date){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment created");
		response.setData(appointmentDao.getAppointmentByDate(date));
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(Integer id){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment created");
		response.setData(appointmentDao.getAppointmentByDoctor(id));
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByPatient(Integer id){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment created");
		response.setData(appointmentDao.getAppointmentByPatient(id));
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByStatus(String status){
		ResponseStructure<List<Appointment>> response = new ResponseStructure<List<Appointment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment created");
		response.setData(appointmentDao.getAppointmentByStatus(status));
		return new ResponseEntity<ResponseStructure<List<Appointment>>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(Integer id){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment cancelled successfully");
		response.setData(appointmentDao.cancelAppointment(id));
		return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Appointment>> updateAppointment(Appointment appointment){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment UPDATED");
		response.setData(appointmentDao.update(appointment));
		return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
	}
}
