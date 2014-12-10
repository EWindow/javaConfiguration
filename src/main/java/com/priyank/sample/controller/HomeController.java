package com.priyank.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.priyank.sample.bean.CustomUserBean;
import com.priyank.sample.constant.ProjectConstant;
import com.priyank.sample.domain.Employee;
import com.priyank.sample.domain.UserRoles;
import com.priyank.sample.service.EmployeeService;

@Controller
public class HomeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PasswordEncoder encoder;

	@Value("${defaultPassword}")
	private String defaultPassword;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.S"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage(Model model) {
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/crudUsers", method = RequestMethod.POST)
	public String crudUsers(@ModelAttribute Employee employee, Model model,
			HttpServletRequest request, BindingResult result,
			@AuthenticationPrincipal CustomUserBean user) throws Exception {
		System.out
				.println("************** controller password encoder ***********"
						+ encoder);
		String Status = "";
		String message = "";
		if (result.hasErrors()) {
			Status = ProjectConstant.UPLOAD_STATUS_FAIL;
			message = result.toString();
		} else {
			String operation = request.getParameter("operation");
			if (ProjectConstant.CRUD_CRAETE.equals(operation)) {
				try {
					String encodedPass = encoder.encode(defaultPassword);
					employee.setCreated(new Date());
					employee.setUpdated(new Date());
					employee.setUpdated(new Date());
					if (user == null) {
						employee.setCreatedBy(0);
					} else {
						employee.setCreatedBy(user.getEmployee().getId());
					}
					employee.setPassword(encodedPass);
					UserRoles roles = new UserRoles(employee, "ROLE_USER");
					Set<UserRoles> userRoleses = new HashSet<UserRoles>();
					userRoleses.add(roles);
					employee.setUserRoleses(userRoleses);
					employeeService.save(employee);
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data saved successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_DELETE.equals(operation)) {
				try {
					employeeService.delete(employee.getId().longValue());
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data deleted successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			} else if (ProjectConstant.CRUD_UPDATE.equals(operation)) {
				try {
					Employee oldEmployee = employeeService.get(employee.getId()
							.longValue());
					employee.setPassword(oldEmployee.getPassword());
					employee.setCreated(employee.getCreated());
					employee.setUpdated(new Date());
					employee.setCreatedBy(employee.getCreatedBy());
					employeeService.update(employee);
					employee.setCreated(new Date());
					Status = ProjectConstant.UPLOAD_STATUS_PASS;
					message = "Data updated successfully";
				} catch (Exception e) {
					e.printStackTrace();
					Status = ProjectConstant.UPLOAD_STATUS_FAIL;
					message = "Error saving data : " + e.getMessage();
				}
			}
		}
		employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("status", Status);
		model.addAttribute("message", message);
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		return "home";
	}
}
