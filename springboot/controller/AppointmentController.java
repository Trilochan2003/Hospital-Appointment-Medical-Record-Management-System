package jsp.springboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.service.AppointmentService;

@RestController
@RequestMapping("/hospital/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	@PostMapping("/book")
	public ResponseEntity<ResponseStructure<Appointment>> createAppointment(@RequestBody Appointment appointment){
		return appointmentService.createAppointment(appointment);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointments(){
		return appointmentService.getAllAppointments();
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> getAppointmentById(@PathVariable Integer id){
		return appointmentService.getAppointmentById(id);
	}
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDate(@PathVariable LocalDate date){
		return appointmentService.getAppointmentByDate(date);
	}
	@GetMapping("/doctor/{id}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByDoctor(@PathVariable Integer id){
		return appointmentService.getAppointmentByDoctor(id);
	}
	@GetMapping("/patient/{id}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByPatient(@PathVariable Integer id){
		return appointmentService.getAppointmentByPatient(id);
	}
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAppointmentByStatus(@PathVariable String status){
		return appointmentService.getAppointmentByStatus(status);
	}
	@PutMapping("/cancel/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(@PathVariable Integer id){
		return appointmentService.cancelAppointment(id);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Appointment>> updateAppointment(@RequestBody Appointment appointment){
		return appointmentService.updateAppointment(appointment);
	}
}
