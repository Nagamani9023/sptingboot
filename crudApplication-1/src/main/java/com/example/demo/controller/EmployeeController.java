package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee) ;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id){
		Optional<Employee> employee=employeeRepository.findById(id);

		return ResponseEntity.ok(employee);
	}
	@PutMapping	("{id}")	
  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody  Employee employeeDatails){
	  Employee updateEmployee = employeeRepository.findById(id)
	.orElseThrow(null);
	  updateEmployee.setFirstName(employeeDatails.getFirstName());
	  updateEmployee.setListName(employeeDatails.getListName());
	  updateEmployee.setEmailId(employeeDatails.getEmailId());
	  
	  
	  employeeRepository.save(updateEmployee);
	  return ResponseEntity.ok(updateEmployee);
  }
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> delateEmployee(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id)
				
		.orElseThrow(null);
		 employeeRepository.delete(employee);
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
