package jsp.springboot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jsp.springboot.entity.Department;
import jsp.springboot.exception.DuplicateResourceException;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.InvalidOperationException;
import jsp.springboot.exception.ResourceNotFoundException;
import jsp.springboot.repository.DepartmentRepository;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Transactional
	public Department create(Department department) {
		if (department.getDepartmentName() == null ||
			    department.getDepartmentName().trim().isEmpty()) {
			    throw new InvalidOperationException("Department name must be provided");
			}
		if (departmentRepository
		        .findByDepartmentNameIgnoreCase(
		                department.getDepartmentName()
		        ).isPresent()) {
		    throw new DuplicateResourceException(
		            "Department already exists"
		    );
		}
		return departmentRepository.save(department);
	}
	public List<Department> createAll(List<Department> departments){
		return departmentRepository.saveAll(departments);
	}
	public List<Department> getAll(){
		List<Department> dept = departmentRepository.findAll();
		if(!dept.isEmpty())
		return dept;
		else throw new ResourceNotFoundException("No departments found");
	}
	public Department getById(Integer id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new ResourceNotFoundException(
			    "Department not found with id " + id
			);
	}
	@Transactional
	public Department update(Department department) {
		if (department.getDepartmentId() == null) {
		    throw new IdNotFoundException("Department id must be provided for update");
		}

		Optional<Department> opt = departmentRepository.findById(department.getDepartmentId());
		if(opt.isPresent()) {
			Department existing = opt.get();

			if (department.getDepartmentName() != null) {
			    existing.setDepartmentName(department.getDepartmentName());
			}

			return departmentRepository.save(existing);
		}else
		throw new ResourceNotFoundException(
			    "Department not found with id " + department.getDepartmentId()
			);

	}
	@Transactional
	public String delete(Integer id) {
		Optional<Department> opt = departmentRepository.findById(id);
		if(opt.isPresent()) {
			if (!opt.get().getDoctors().isEmpty()) {
			    throw new InvalidOperationException(
			        "Cannot delete department with assigned doctors"
			    );
			}
			departmentRepository.delete(opt.get());
			return "Department deleted successfully";
		}
		else throw new ResourceNotFoundException(
			    "Department not found with id " + id
				);
	}
	public Optional<Department> getByName(String dname) {
		return departmentRepository.findByDepartmentNameIgnoreCase(dname);
	}
}
