package jsp.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Department;
import jsp.springboot.service.DepartmentService;

@RestController
@RequestMapping("/hospital/dept")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@PostMapping()
	public ResponseEntity<ResponseStructure<Department>> createDept(@RequestBody Department department){
		return departmentService.createDept(department);
	}
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Department>>> createAllDept(@RequestBody List<Department> departments){
		return departmentService.createAllDept(departments);
	}
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Department>>> getAllDept(){
		return departmentService.getAllDept();
	}
	@GetMapping()
	public ResponseEntity<ResponseStructure<Department>> getAllDeptById(@PathVariable Integer id){
		return departmentService.getDeptById(id);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Department>> updateDept(@RequestBody Department department){
		return departmentService.updateDept(department);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDept(@PathVariable Integer id){
		return departmentService.deleteDept(id);
	}
	@GetMapping("/name/{dname}")
	public ResponseEntity<ResponseStructure<Department>> getAllDeptById(@PathVariable String dname){
		return departmentService.getDeptByName(dname);
	}
}