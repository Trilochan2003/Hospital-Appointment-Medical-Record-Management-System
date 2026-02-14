package jsp.springboot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{
	public List<MedicalRecord> findByPatient_PatientId(Integer patientId);
	public List<MedicalRecord> findByDoctor_DoctorId(Integer doctorId);
	@Query("select mr from MedicalRecord mr, Appointment a where mr.doctor=a.doctor"
			+ " AND mr.patient = a.patient AND a.appointmentId = :appointmentId AND a.appointmentDateTime >= :start"
			+ " AND a.appointmentDateTime < :end")
	public Optional<MedicalRecord> findMedicalRecordByAppointmentId(  @Param("appointmentId") Integer appointmentId,
	        @Param("start") LocalDateTime start, 
	        @Param("end") LocalDateTime end);	
	public List<MedicalRecord> findByVisitDate(LocalDate visitDate);
	

}
