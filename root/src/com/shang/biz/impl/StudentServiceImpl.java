package com.shang.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shang.Bean.Student;
import com.shang.biz.IStudentService;
import com.shang.dao.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	@Autowired
	private IStudentDao stuDao;

	public int insert(Student stu) {
		return stuDao.insert(stu);
	}
	
	public int delete(int id) {
		return stuDao.delete(id);
	}

	public int update(Student stu) {
		return stuDao.update(stu);
	}
	
	public Student findById(int id) {
		return stuDao.findById(id);
	}
	
	public List<Student> findAll(Map<String,Object> map) {
		return stuDao.findAll(map);
	}

}
