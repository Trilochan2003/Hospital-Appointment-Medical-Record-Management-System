package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Department;
import jsp.springboot.entity.Doctor;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.InvalidOperationException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.DepartmentRepository;
import jsp.springboot.repository.DoctorRepository;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Transactional
	public Doctor add(Doctor doctor){
		System.out.println("Doctor add method called");

		if (doctor.getDepartment() == null ||
		        doctor.getDepartment().getDepartmentId() == null) {
		        throw new InvalidOperationException("Department must be provided");
		    }
		if (doctor.getDoctorName() == null || doctor.getDoctorName().trim().isEmpty())
		    throw new InvalidOperationException("Doctor name must be provided");

		if (doctor.getSpecialization() == null || doctor.getSpecialization().trim().isEmpty())
		    throw new InvalidOperationException("Specialization must be provided");

		if (doctor.getAvailableDays() == null || doctor.getAvailableDays().trim().isEmpty())
		    throw new InvalidOperationException("Available days must be provided");

		Optional<Department> opt = departmentRepository.findById(doctor.getDepartment().getDepartmentId());
		if(opt.isPresent()) {
			Department dept = opt.get();
			doctor.setDepartment(dept);
		}
		else throw new ResourceNotFoundException("Department not found with id "+ doctor.getDepartment().getDepartmentId());                
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
		List<Doctor> patient =
				appointmentRepository.findDistinctDoctorsByPatient(patientId);

		if (patient.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No doctors found for Patient Id " + patientId
		    );
		}
		return patient;
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
	@Transactional
	public Doctor update(Doctor doctor) {
		if(doctor.getDoctorId()==null)
			throw new IdNotFoundException("Id doesn't exist in DB");
		Optional<Doctor> opt = doctorRepository.findById(doctor.getDoctorId());
		if(opt.isPresent()) {
			Doctor existingDoctor = opt.get();
			if (doctor.getDepartment() == null ||
			        doctor.getDepartment().getDepartmentId() == null) {
			        throw new InvalidOperationException("Department must be provided");
			    }
			Optional<Department> opt2 = departmentRepository.findById(doctor.getDepartment().getDepartmentId());
			if(opt2.isPresent()) {
				Department department = opt2.get();
				if (doctor.getDoctorName() != null)
				    existingDoctor.setDoctorName(doctor.getDoctorName());

				if (doctor.getSpecialization() != null)
				    existingDoctor.setSpecialization(doctor.getSpecialization());

				if (doctor.getAvailableDays() != null)
				    existingDoctor.setAvailableDays(doctor.getAvailableDays());

			    existingDoctor.setDepartment(department);
			} else throw new ResourceNotFoundException("Department not found with id " +doctor.getDepartment().getDepartmentId());                               
			return doctorRepository.save(existingDoctor);
		}
		else throw new ResourceNotFoundException(
			    "Doctor not found with id " + doctor.getDoctorId()
				);
	}
	@Transactional
	public String delete(Integer id) {
		Optional<Doctor> opt = doctorRepository.findById(id);
		if(opt.isPresent()) {
			if (!opt.get().getAppointments().isEmpty()) {
			    throw new InvalidOperationException(
			        "Cannot delete doctor with existing appointments"
			    );
			}
			doctorRepository.delete(opt.get());
			return "Doctor deleted successfully";
		}else throw new ResourceNotFoundException(
			    "Doctor not found with id " + id
				);
	}
}
