package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.DoctorRepository;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	public Doctor add(Doctor doctor){
		return doctorRepository.save(doctor);
	}
	public List<Doctor> getAll(){
		List<Doctor> doctors = doctorRepository.findAll();
		if(!doctors.isEmpty())
		return doctors;
		else throw new ResourceNotFoundException("No doctors found");
	}
	public Doctor getById(Integer id) {
		Optional<Doctor> opt = doctorRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();			
		}
		else throw new ResourceNotFoundException(
			    "Doctor not found with id " + id
				);
	}
	public List<Doctor> getBySpecialization(String Specialization){
		List<Doctor> doctors =
		        doctorRepository.findBySpecializationIgnoreCase(Specialization);

		if (doctors.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No doctors found with specialization " + Specialization
		    );
		}
		return doctors;
	}
	public List<Doctor> getByDepartemnt(String dept){
		List<Doctor> doctors =
		        doctorRepository.findByDepartment_DepartmentNameIgnoreCase(dept);

		if (doctors.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No doctors found in department " + dept
		    );
		}
		return doctors;
	}
	public List<Doctor> findDoctorsByPatient(Integer patientId) {
	    return appointmentRepository.findDistinctDoctorsByPatient(patientId);
	}
	public Doctor getDoctorByAppointment(Integer appointmentId) {
		Optional<Appointment> opt = appointmentRepository.findById(appointmentId);
		if(opt.isPresent()) {
			Appointment appointment = opt.get();
			return appointment.getDoctor();
		}
		else throw new ResourceNotFoundException(
			    "Appointment not found with id " + appointmentId
				);
	}
	//by patient
	//by appointment
	
	
	public List<Doctor> getByDays(String day){
		if (day.length() < 3) {
		    throw new ResourceNotFoundException("Day must have at least 3 characters");
		}
		day = day.substring(0, 3).toUpperCase();
		List<Doctor> doctors = doctorRepository.findByAvailableDaysContainingIgnoreCase(day);
		if(!doctors.isEmpty())
		return doctors;
		else throw new ResourceNotFoundException(
			    "No doctors available on " + day
				);
	}
	public Doctor update(Doctor doctor) {
		if(doctor.getDoctorId()==null)
			throw new IdNotFoundException("Id doesn't exist in DB");
		Optional<Doctor> opt = doctorRepository.findById(doctor.getDoctorId());
		if(opt.isPresent()) {
			return doctorRepository.save(doctor);
		}
		else throw new ResourceNotFoundException(
			    "Doctor not found with id " + doctor.getDoctorId()
				);
	}
	public String delete(Integer id) {
		Optional<Doctor> opt = doctorRepository.findById(id);
		if(opt.isPresent()) {
			doctorRepository.delete(opt.get());
			return "Doctor deleted successfully";
		}else throw new ResourceNotFoundException(
			    "Doctor not found with id " + id
				);
	}
}
