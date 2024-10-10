package com.example.creato.objets.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.objets.projet.ProjetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjetRepository projetRepository;

    @PostMapping("/add")
    public Employee postMethodName(@RequestBody Employee Employee) {
        return this.employeeRepository.save(Employee);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployes() {
        List<Employee> employees = this.employeeRepository.findAll();
        for (Employee employee : employees) {
            employee.projets = this.projetRepository.countByAssignee(employee.nomComplet);
        }
        ;
        return employees;
    }

    @GetMapping("path")
    public String getMethodName() {
        return "fred";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return "Success";
    }

}
