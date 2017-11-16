package com.shang.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shang.Bean.Student;
import com.shang.biz.IStudentService;


@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IStudentService stuService;
	/**
	 * ����
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest request,
			HttpServletResponse response, Student stu) {
		System.out.println("ADD" + stu.toString());
		stuService.insert(stu);
		return "redirect:/student/allStudent.do";
	}

	/**
	 * ɾ��
	 */
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id) {
		System.out.println("DEL" + id);
		stuService.delete(id);
		return "redirect:/student/allStudent.do";
	}

	/**
	 * ��ʾ
	 */
	@RequestMapping("/allStudent")
	public ModelAndView allStudent(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		ModelAndView modelAndView = new ModelAndView();
		List<Student> stuList = stuService.findAll(map);
		System.out.println(stuList);
		modelAndView.addObject("stuList", stuList);
		System.out.println("allStudent:\t"+stuList);
		modelAndView.setViewName("allStudent");
		return modelAndView;
	}
	/**
	 * ��ʾ
	 */
	@RequestMapping(value="/listStudent",method = RequestMethod.POST)
	public ModelAndView listStudentByCondition(HttpServletRequest request,
			HttpServletResponse response , Student stu){
		System.out.println(""+stu.toString());
		Map<String,Object> map = new HashMap<String, Object>();
		if(stu!=null&&stu.getName()!=null&&!"".equals(stu.getName().trim())){
			map.put("name", stu.getName());
		}
		if(stu!=null&&stu.getSex()!=null&&!"".equals(stu.getSex().trim())){
			map.put("sex", stu.getSex());
		}
		if(stu!=null&&stu.getAge()!=0){
			map.put("age", stu.getAge());
		}
		ModelAndView modelAndView = new ModelAndView();
		List<Student> stuList = stuService.findAll(map);
		System.out.println("listStudentByCondition:\t"+stuList);
		modelAndView.addObject("stuList", stuList);
		modelAndView.setViewName("allStudent");
		return modelAndView;
	}

	/**
	 * ����
	 */
	@RequestMapping("/pre4Update/{id}")
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") int id) {
		System.out.println("ID"+id);
		Student stu = stuService.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("stu", stu);
		modelAndView.setViewName("operaStudent");
		return modelAndView;
	}
	/**
	 */
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public String updateStudent(HttpServletRequest request,
			HttpServletResponse response, Student stu) {
		System.out.println("UPD" + stu.toString());
		this.stuService.update(stu);
		System.out.println("UPD  OK");
		return "redirect:/student/allStudent.do";
	}

	/**
	 * 用于跳转
	 */
	@RequestMapping("redir/{url}")
	public String redir(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("url") String url) {
		System.out.println("URL" + url);
		return url;
	}
}