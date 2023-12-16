package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student addNewStudet(Student student) {
        var studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email is taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw  new IllegalStateException("Student with ID: "+id+" does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with Id: "+id+" is not found"));

        if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            var studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw  new IllegalStateException("Email: "+email+" is taken");
            }
            student.setEmail(email);
        }

        return student;
    }
}
