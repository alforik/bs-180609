package kr.ac.slipp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.slipp.dto.QnaRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@GetMapping("")
	public String list(Model model) {
		
		model.addAttribute("qnas", qnaRepository.findAll());
		return "index";
	}

}
