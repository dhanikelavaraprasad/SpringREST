package com.cyb.portal.controller;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.portal.entity.Employee;
import com.cyb.portal.service.EmployeeService;
import com.cyb.portal.util.URIConstants;

@RestController
@RequestMapping(URIConstants.GET_ALL_EMP)
public class EmployeeController {
	
	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	
	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

    @Autowired
    private EmployeeService employeeService;

   /* @RequestMapping("createEmployee")
    public ModelAndView createEmployee(@ModelAttribute Employee employee) {
    	logger.info("Creating Employee. Data: "+employee);
        return new ModelAndView("employeeForm");
    }*/
    
    @RequestMapping(value = URIConstants.EDIT_EMP, method = RequestMethod.PUT)
    public @ResponseBody Employee editEmployee(@PathVariable("id") long id) {
    	logger.info("Updating the Employee for the Id "+id);
        return employeeService.getEmployee(id);
    }
    
    @RequestMapping(value = URIConstants.CREATE_EMP, method = RequestMethod.POST)
    public String saveEmployee(@RequestBody Employee employee) {
    	logger.info("Saving the Employee. Data : "+employee);
        if(employee.getId() == 0){ // if employee id is 0 then creating the employee other updating the employee
            employeeService.createEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        return "redirect:getAllEmployees";
    }
    
    @RequestMapping(value = URIConstants.DELETE_EMP, method = RequestMethod.PUT)
    public String deleteEmployee(@PathVariable("id") long id) {
    	logger.info("Deleting the Employee. Id : "+id);
        employeeService.deleteEmployee(id);
        return "redirect:getAllEmployees";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Employee> getAllEmployees() {
    	logger.info("Getting the all Employees.");
        return employeeService.getAllEmployees();
    }
    
    /*@RequestMapping("searchEmployee")
    public ModelAndView searchEmployee(@RequestParam("searchName") String searchName) {  
    	logger.info("Searching the Employee. Employee Names: "+searchName);
    	List<Employee> employeeList = employeeService.getAllEmployees(searchName);
        return new ModelAndView("employeeList", "employeeList", employeeList);    	
    }*/
}