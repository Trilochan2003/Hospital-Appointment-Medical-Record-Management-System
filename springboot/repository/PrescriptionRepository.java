package jsp.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
	public Optional<Prescription> findByMedicalRecord_RecordId(Integer recordId);
	public List<Prescription> findByMedicalRecord_Patient_PatientId(Integer patientId);
	boolean existsByMedicalRecord_RecordId(Integer recordId);
}
