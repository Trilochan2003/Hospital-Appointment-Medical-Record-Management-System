package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.MedicalRecordRepository;
import jsp.springboot.repository.PatientRepository;

@Repository
public class PatientDao {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	public Patient register(Patient patient) {
		return patientRepository.save(patient); 
	}
	public List<Patient> getAll(){
		List<Patient> patients = patientRepository.findAll();
		if(!patients.isEmpty())
			return patients;
		else
			 throw new ResourceNotFoundException("No Patient Found in DB");
	}
	public Patient getById(Integer Id) {
		Optional<Patient> opt = patientRepository.findById(Id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else throw new IdNotFoundException("Patient not found with id " + Id);
	}
	public Optional<Patient> getByPhoneNumber(Long phone){
		return patientRepository.findByPhone(phone);
	}
	public List<Patient> getAgeGreaterThan(Integer age){
		List<Patient> patients = patientRepository.findByAgeGreaterThan(age);
		if(!patients.isEmpty())
			return patients;
		else throw new ResourceNotFoundException("No Patient Found in DB with age Greater Than "+age );
	}
	public Patient getPatientByAppointment(Integer appointmentId) {
		Optional<Appointment> opt = appointmentRepository.findById(appointmentId);
		if(opt.isPresent()) {
			Appointment appointment = opt.get();
			return appointment.getPatient();
		}
		else throw new IdNotFoundException(
			    "Appointment not found with id " + appointmentId
				);
	}
	public Patient getPatientByMedicalRecord(Integer medicalRecordId) {
		Optional<MedicalRecord> opt = medicalRecordRepository.findById(medicalRecordId);
		if(opt.isPresent()) {
			MedicalRecord medicalRecord = opt.get();
			return medicalRecord.getPatient();
		}
		else throw new IdNotFoundException(
			    "Medical record not found with id " + medicalRecordId
				);
	}
	//by appointment
	//by medical records
	
	
	public Patient update(Patient patient) {
		if (patient.getPatientId() == null) {
		    throw new IdNotFoundException("Patient id must be provided for update");
		}

		Optional<Patient> opt = patientRepository.findById(patient.getPatientId());
		if(opt.isPresent()) {
		    return patientRepository.save(patient);
		}
		throw new ResourceNotFoundException(
		    "Patient not found with id " + patient.getPatientId()
		);
	}
	public String delete(Integer id) {
		Optional<Patient> opt = patientRepository.findById(id);
		if(opt.isPresent()) {
			patientRepository.delete(opt.get());
			return "Deleted";
		}else throw new IdNotFoundException("Id doesn't exist in DB with "+id);
	}
}
