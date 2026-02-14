package jsp.springboot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.Doctor;
import jsp.springboot.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	public boolean existsByPatientAndAppointmentDateTimeBetween(Patient patient, LocalDateTime start, LocalDateTime end);
	@Query("select a from Appointment a where DATE(a.appointmentDateTime)=?1")
	public List<Appointment> findByAppointmentDate(LocalDate date);
	
	public List<Appointment> findByDoctor_DoctorId(Integer doctorId);
	public List<Appointment> findByPatient_PatientId(Integer doctorId);
	public List<Appointment> findByStatus(AppointmentStatus status);
	@Query("select distinct a.doctor from Appointment a where a.patient.patientId = ?1")
	public List<Doctor> findDistinctDoctorsByPatient(Integer patientId);
}
