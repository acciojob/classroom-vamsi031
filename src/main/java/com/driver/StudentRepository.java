package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository{
    Map<String, Student> studentMap = new HashMap<>();
    Map<String, Teacher> teacherMap = new HashMap<>();
    Map<String, List<String>> teacherStudentMap = new HashMap<>();
    public void addStudent(Student student) {
        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(!teacherStudentMap.containsKey(teacher))
            teacherStudentMap.put(teacher, new ArrayList<>());

        List<String> studentList = teacherStudentMap.get(teacher);
        studentList.add(student);

        Teacher teacher1 = teacherMap.get(teacher);
        teacher1.setNumberOfStudents(studentList.size());

        teacherStudentMap.put(teacher, studentList);
    }

    public Student getStudentByName(String name) {
        if(studentMap.containsKey(name))
            return studentMap.get(name);
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if(teacherMap.containsKey(name))
            return teacherMap.get(name);
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        if(teacherStudentMap.containsKey(teacher))
            return teacherStudentMap.get(teacher);
        return new ArrayList<>();
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        List<String> students = teacherStudentMap.get(teacher);
        teacherStudentMap.remove(teacher);
        for(String student : students){
            studentMap.remove(student);
        }
    }

    public void deleteAllTeachers() {
        for(String teacher : teacherMap.keySet()){
            deleteTeacherByName(teacher);
        }
    }
}