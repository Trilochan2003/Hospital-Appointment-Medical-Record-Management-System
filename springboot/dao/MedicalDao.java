package jsp.springboot.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.AppointmentStatus;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.InvalidOperationException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.MedicalRecordRepository;

@Repository
public class MedicalDao {
	@Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord create(Integer appointmentId,MedicalRecord medicalRecord){
		Optional<Appointment>opt = appointmentRepository.findById(appointmentId);
		if(opt.isPresent()) {
			Appointment appointment = opt.get();
			if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
			    throw new InvalidOperationException(
			        "Medical record can be created only after appointment is completed"
			    );
			}
			medicalRecord.setDoctor(appointment.getDoctor());
			medicalRecord.setPatient(appointment.getPatient());
			return medicalRecordRepository.save(medicalRecord);
		}else throw new InvalidOperationException("To create Medical Record Appoint must be completed ");
	}
	public List<MedicalRecord> getAll(){
		List<MedicalRecord> records = medicalRecordRepository.findAll();
		if(!records.isEmpty()) {
			return records;
		}
		else throw new ResourceNotFoundException("No medical records found");
	}
	public MedicalRecord getById(Integer id) {
		Optional<MedicalRecord> opt = medicalRecordRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else throw new ResourceNotFoundException(
			    "Medical record not found with id " + id
				);
	}
	public List<MedicalRecord> getRecordByPatient(Integer id) {
		List<MedicalRecord> records =
		        medicalRecordRepository.findByPatient_PatientId(id);

		if (records.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No medical records found for patient id " + id
		    );
		}
		return records;
	}
	public List<MedicalRecord> getRecordByDoctor(Integer id) {
		List<MedicalRecord> records =
				medicalRecordRepository.findByDoctor_DoctorId(id);

		if (records.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No medical records found for doctor id " + id
		    );
		}
		return records;
	}
	public List<MedicalRecord> getRecordByVisitDate(LocalDate date) {
		List<MedicalRecord> records =
				medicalRecordRepository.findByVisitDate(date);

		if (records.isEmpty()) {
		    throw new ResourceNotFoundException(
		        "No medical records found for visit date id " + date
		    );
		}
		return records;
	}
	public MedicalRecord getRecordByAppointment(Integer id) {
		Optional<Appointment>opt = appointmentRepository.findById(id);
		if(opt.isPresent()) {
			Appointment appointment = opt.get();
			LocalDate date = appointment.getAppointmentDateTime().toLocalDate();
			LocalDateTime start = date.atStartOfDay();
			LocalDateTime end = date.plusDays(1).atStartOfDay();
			Optional<MedicalRecord> opt1 = medicalRecordRepository.findMedicalRecordByAppointmentId(id,start,end);
			if(!opt1.isPresent()) {
				throw new ResourceNotFoundException(
					    "Medical record not found for appointment id " + id
					);
			}
			return opt1.get();
		}
		else throw new ResourceNotFoundException(
			    "Appointment not found with id " + id
				);
	}
}
