package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public  StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return  studentService.getStudents();
    }

    @PostMapping
    public Student registerNewStudent(@RequestBody Student student){
        return studentService.addNewStudet(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public Student updateStudent(@PathVariable("studentId") Long id,
                                 @RequestParam(required= false) String name,
                                 @RequestParam(required = false) String email){
        return studentService.updateStudent(id, name, email);
    }

}
