 package com.lpit.myssh.web;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class HelloControler {
	
	Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("/hello1")
	public ModelAndView Hello1() {
//		System.out.println("执行处理器1");
		logger.info("执行处理器1");
		return new ModelAndView("hello").addObject("msg", "hello world1");
		
	}

}
