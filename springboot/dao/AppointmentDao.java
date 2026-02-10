package jsp.springboot.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.DuplicateResourceException;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.InvalidOperationException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.AppointmentRepository;

@Repository
public class AppointmentDao {
	@Autowired
	private AppointmentRepository appointmentRepository;
	public Appointment create(Appointment appointment) {
		LocalDate appointmentDate = appointment.getAppointmentDateTime().toLocalDate();
		LocalDateTime start = appointmentDate.atStartOfDay();
		LocalDateTime end = appointmentDate.atTime(LocalTime.MAX);
		boolean exists = appointmentRepository.existsByPatientAndAppointmentDateTimeBetween(appointment.getPatient(), start, end);
		
		if(!exists)
			return appointmentRepository.save(appointment);
		else throw new DuplicateResourceException(
			    "Patient already has an appointment on this date"
			);

	}
	public List<Appointment> getAll(){
		List<Appointment> appointments = appointmentRepository.findAll();
		if(!appointments.isEmpty()) {
			return appointments;
		}
		else throw new ResourceNotFoundException("No appointments found");

	}
	public Appointment getById(Integer id) {
		Optional<Appointment> opt = appointmentRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else throw new ResourceNotFoundException(
			    "Appointment not found with id " + id
			);

	}
	public List<Appointment> getAppointmentByDate(LocalDate date){
		List<Appointment> list = appointmentRepository.findByAppointmentDate(date);
		if (list.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No appointments found on date " + date
		    );
		}
		return list;

	}
	public List<Appointment> getAppointmentByDoctor(Integer id){
		List<Appointment> list = appointmentRepository.findByDoctor_DoctorId(id);
		if (list.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No appointments found for doctor id " + id
		    );
		}
		return list;

	}
	public List<Appointment> getAppointmentByPatient(Integer id){
		List<Appointment> list = appointmentRepository.findByPatient_PatientId(id);
		if (list.isEmpty()) {
		    throw new ResourceNotFoundException(
		    		"No appointments found for patient id " + id
		    );
		}
		return list;
	}
	public List<Appointment> getAppointmentByStatus(String status) {
	    try {
	        AppointmentStatus appointmentStatus =
	                AppointmentStatus.valueOf(status.toUpperCase());
	        return appointmentRepository.findByStatus(appointmentStatus);
	    } catch (IllegalArgumentException e) {
	        throw new InvalidOperationException(
	            "Invalid appointment status: " + status
	        );
	    }
	}

	public Appointment cancelAppointment(Integer id) {
		 Optional<Appointment> opt = appointmentRepository.findById(id);
		 Appointment appointment = opt.get();
		 if(opt.isPresent()) {
			 if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
		            throw new InvalidOperationException("Appointment is already cancelled");
		        }
	        
		        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
		            throw new InvalidOperationException("Completed appointment cannot be cancelled");
		        }
			 
			 appointment.setStatus(AppointmentStatus.CANCELLED);
			 return appointmentRepository.save(appointment);
		 }else throw new ResourceNotFoundException("No Appointment Found in DB");
	}
	public Appointment update(Appointment appointment) {
		if (appointment.getAppointmentId() == null) {
	        throw new InvalidOperationException("Appointment ID must be provided for update");
	    }
		if (appointment.getDoctor() == null || appointment.getDoctor().getDoctorId() == null) {
	        throw new InvalidOperationException("Doctor ID must be provided");
	    }
		if (appointment.getPatient() == null || appointment.getPatient().getPatientId() == null) {
			throw new InvalidOperationException("Patient ID must be provided");
		}
		Optional<Appointment> opt = appointmentRepository.findById(appointment.getAppointmentId());
		if(opt.isPresent()) {
			Appointment existing = opt.get();
			 if (!existing.getDoctor().getDoctorId()
			            .equals(appointment.getDoctor().getDoctorId())) {
			        throw new InvalidOperationException(
			            "Doctor cannot be changed for an appointment");
			    }

			    if (!existing.getPatient().getPatientId()
			            .equals(appointment.getPatient().getPatientId())) {
			        throw new InvalidOperationException(
			            "Patient cannot be changed for an appointment");
			    }
			return appointmentRepository.save(appointment);
		}else throw new ResourceNotFoundException(
			    "Appointment not found with id " + appointment.getAppointmentId()
				);
	}
}
