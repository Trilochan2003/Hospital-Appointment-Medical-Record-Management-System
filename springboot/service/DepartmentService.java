package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.dao.DepartmentDao;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.exception.ResourceNotFoundException;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	public ResponseEntity<ResponseStructure<Department>> createDept(Department department){
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Department Created Sucessfully");
		response.setData(departmentDao.create(department));
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Department>>> createAllDept(List<Department> departments){
		ResponseStructure<List<Department>> response = new ResponseStructure<List<Department>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Departments created successfully");
		response.setData(departmentDao.createAll(departments));
		return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<List<Department>>> getAllDept(){
		ResponseStructure<List<Department>> response = new ResponseStructure<List<Department>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Department fetched Sucessfully");
		response.setData(departmentDao.getAll());
		return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Department>> getDeptById(Integer id){
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department Fetched by id Sucessfully");
		response.setData(departmentDao.getById(id));
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Department>> updateDept(Department department){
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department Updated Sucessfully");
		response.setData(departmentDao.update(department));
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<String>> deleteDept(Integer id){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Department deleted successfully");
		response.setData(departmentDao.delete(id));
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<Department>> getDeptByName(String dname){
		Optional<Department> opt = departmentDao.getByName(dname);
		ResponseStructure<Department> response = new ResponseStructure<Department>();
		if(opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Department fetched successfully");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);			
		}else throw new ResourceNotFoundException(
	            "Department not found with name " + dname
		        );
	}
}
