package com.example.creato.objets.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.auth.MessageResponse;
import com.example.creato.objets.projet.ProjetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjetRepository projetRepository;

    @SuppressWarnings("finally")
    @PostMapping("/add")
    public Employee postMethodName(@RequestBody Employee employee) {

        try {
            String isoDateTime = employee.dateInterco;
            ZonedDateTime givenDateTime = ZonedDateTime.parse(isoDateTime, DateTimeFormatter.ISO_DATE_TIME);
            ZonedDateTime currentDateTime = ZonedDateTime.now(givenDateTime.getZone());
            employee.nbJourInterco = ChronoUnit.DAYS.between(givenDateTime, currentDateTime);

        } catch (Exception e) {
            // TODO: handle exception
            employee.nbJourInterco = Long.valueOf(0);
        }

        return this.employeeRepository.save(employee);

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

    @DeleteMapping("/delete/{employeeId}")
    public MessageResponse deleteEmployee(@PathVariable Long employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return new MessageResponse("Success");
    }

}
