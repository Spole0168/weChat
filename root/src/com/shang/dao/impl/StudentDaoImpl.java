package com.shang.dao.impl;

import java.util.*;

import com.base.BaseDao;
import com.shang.Bean.Student;
import com.shang.dao.IStudentDao;

public class StudentDaoImpl extends BaseDao implements IStudentDao {
	
	public StudentDaoImpl() {
		super();
		this.nameSpace = "student.";
	}
	
	public int insert(Student stu) {
		logger.info(this.getClass()+"ADD-stu"+stu);
		return this.sqlSession.insert(nameSpace+"insert", stu);
	}
	
	public int delete(int id) {
		return this.sqlSession.delete(nameSpace+"delete", id);
	}

	public int update(Student stu) {
		return this.sqlSession.update(nameSpace+"update", stu);
	}
	
	public Student findById(int id) {
		Student stu = (Student) sqlSession.selectOne(nameSpace+"findById", id);
		return stu;
	}
	
	public List<Student> findAll(Map<String,Object> map) {
		List<Student> list =  sqlSession.selectList(nameSpace+"findAll",map);
		logger.info(this.getClass()+"findAll-"+list);
		return list;
	}




}