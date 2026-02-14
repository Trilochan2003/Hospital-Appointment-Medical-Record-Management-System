package jsp.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springboot.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	public List<Doctor> findBySpecializationIgnoreCase(String specialization);
	public List<Doctor> findByDepartment_DepartmentNameIgnoreCase(String departmentName);
	public List<Doctor> findByAvailableDaysContainingIgnoreCase(String day);
}
