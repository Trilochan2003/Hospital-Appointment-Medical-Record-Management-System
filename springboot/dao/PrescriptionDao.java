package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Prescription;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.InvalidOperationException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.MedicalRecordRepository;
import jsp.springboot.repository.PrescriptionRepository;

@Repository
public class PrescriptionDao {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Transactional
	public Prescription create(Integer medicalRecordId, Prescription prescription) {
		Optional<MedicalRecord> opt = medicalRecordRepository.findById(medicalRecordId);
		if(opt.isPresent()) {
			 if (prescriptionRepository.existsByMedicalRecord_RecordId(medicalRecordId)) {
			        throw new InvalidOperationException(
			                "Prescription already exists for this medical record"
			        );
			    }
			prescription.setMedicalRecord(opt.get());
			return prescriptionRepository.save(prescription);
		}else throw new IdNotFoundException(
			    "Medical record not found with id " + medicalRecordId
				);
	}
	public List<Prescription> getAll() {
		List<Prescription> prescriptions = prescriptionRepository.findAll();
		if(!prescriptions.isEmpty()) {
			return prescriptions;
		}
		else throw new ResourceNotFoundException("No Prescription Record Found in DB");
	}
	public Prescription getById(Integer id) {
		Optional<Prescription> opt = prescriptionRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else throw new IdNotFoundException(
			    "Prescription not found with id " + id
				);
	}
	public Prescription getByMedicalRecord(Integer medicalRecordId) {
		Optional<Prescription> opt = prescriptionRepository.findByMedicalRecord_RecordId(medicalRecordId);
		if(opt.isPresent()) {
			return opt.get();			
		}
		else throw new ResourceNotFoundException(
			    "Prescription not found for medical record id " + medicalRecordId
				);
	}
	public List<Prescription> getByPatient(Integer patientId){
		List<Prescription> prescriptions =
			    prescriptionRepository.findByMedicalRecord_Patient_PatientId(patientId);

			if (!prescriptions.isEmpty()) {
			    return prescriptions;
			}
			throw new ResourceNotFoundException(
			    "No prescriptions found for patient id " + patientId
			);
	}
	
}
