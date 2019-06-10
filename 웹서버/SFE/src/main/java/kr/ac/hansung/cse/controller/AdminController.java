package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Sensor;
import kr.ac.hansung.cse.service.SensorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SensorService productService;

	@RequestMapping
	public String adminPage() {
		return "admin";
	}
	
}
