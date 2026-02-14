	package jsp.springboot.entity;
	
	import com.fasterxml.jackson.annotation.JsonIgnore;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToOne;
	import jakarta.persistence.Table;
	
	@Entity
	public class Prescription {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer prescriptionId;
		private String medicine;
		private String dosage;
		private String instruction;
		
		@JsonIgnore
		@OneToOne
		@JoinColumn(name = "medical_record_id", unique = true, nullable = false)
		private MedicalRecord medicalRecord;
	
		public Integer getPrescriptionId() {
			return prescriptionId;
		}
	
		public void setPrescriptionId(Integer prescriptionId) {
			this.prescriptionId = prescriptionId;
		}
	
		public String getMedicine() {
			return medicine;
		}
	
		public void setMedicine(String medicine) {
			this.medicine = medicine;
		}
	
		public String getDosage() {
			return dosage;
		}
	
		public void setDosage(String dosage) {
			this.dosage = dosage;
		}
	
		public String getInstruction() {
			return instruction;
		}
	
		public void setInstruction(String instruction) {
			this.instruction = instruction;
		}
	
		public MedicalRecord getMedicalRecord() {
			return medicalRecord;
		}
	
		public void setMedicalRecord(MedicalRecord medicalRecord) {
			this.medicalRecord = medicalRecord;
		}
		
	}
	
