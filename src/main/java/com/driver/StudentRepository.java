package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentDb = new HashMap<>();

    private HashMap<String,Teacher> teacherDb = new HashMap<>();

    private HashMap<String, ArrayList<String>> teacherToStudentsDb = new HashMap<>();


    public void addStudent(Student student) {
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(teacherToStudentsDb.containsKey(teacher)==false){
            System.out.println("incondion");
            teacherToStudentsDb.put(teacher,new ArrayList<>());
        }
        System.out.println(teacherToStudentsDb);
        teacherToStudentsDb.get(teacher).add(student);
    }

    public Student getStudentByName(String name) {
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherToStudentsDb.get(teacher);
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        for(String key:studentDb.keySet())list.add(key);
        return list;
    }

    public void deleteTeacherByName(String teacher) {
        for(String str:teacherToStudentsDb.get(teacher)){
            studentDb.remove(str);
        }
        teacherDb.remove(teacher);
    }

    public void deleteALlTeachers() {
        teacherDb.clear();
    }
}
