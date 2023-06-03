package com.example.Task31.controller;

import com.example.Task31.entity.Student;
import com.example.Task31.repositoryies.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class ControllerHome {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping(value = "/add-student")
        public String openAddStudent(){
        return "add-student";
    }
    @PostMapping(value = "/add-student")
    public String addStudentPost(@RequestParam("student_name") String name,
                                 @RequestParam("student_surname") String surname,
                                 @RequestParam("student_exam") int exam) {

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setExam(exam);
        if (exam >= 90 && exam <= 100) {
            student.setMark("A");
        } else if (exam >= 75 && exam <= 89) {
            student.setMark("B");
        } else if (exam >= 60 && exam <= 74) {
            student.setMark("C");
        } else if (exam >= 50 && exam <= 59) {
            student.setMark("D");
        } else if (exam < 50) {
            student.setMark("F");
        }
        studentRepository.save(student);
        return "redirect:home";
    }
        @GetMapping(value="/home")
            public String openHome(Model model){
            List<Student> students=studentRepository.findAll();
            model.addAttribute("stundets", students);
            return "home";
        }
}

