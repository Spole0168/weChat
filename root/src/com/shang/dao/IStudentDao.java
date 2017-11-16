package com.shang.dao;

import java.util.List;
import java.util.Map;

import com.shang.Bean.Student;

public interface IStudentDao {
	public int insert(Student stu);

	public int delete(int id);

	public int update(Student stu);

	public Student findById(int id);

	public List<Student> findAll(Map<String,Object> map);

}
