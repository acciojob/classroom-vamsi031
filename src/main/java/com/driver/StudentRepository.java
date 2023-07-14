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
        if(!teacherToStudentsDb.containsKey(teacher))
            teacherToStudentsDb.put(teacher, new ArrayList<>());

        ArrayList<String> studentList = teacherToStudentsDb.get(teacher);
        studentList.add(student);

        Teacher teacher1 = teacherDb.get(teacher);
        teacher1.setNumberOfStudents(studentList.size());

        teacherToStudentsDb.put(teacher, studentList);
    }

    public Student getStudentByName(String name) {
        if(studentDb.containsKey(name))
            return studentDb.get(name);
        return null;
    }

    public Teacher getTeacherByName(String name) {
        if(teacherDb.containsKey(name))
            return teacherDb.get(name);
        return null;
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
        studentDb.clear();
        teacherToStudentsDb.clear();
    }
}
