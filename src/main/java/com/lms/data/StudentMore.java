package com.lms.data;

import com.lms.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMore extends Student {
    String dname;
    public StudentMore(Student student,String dname){
        super(student.getSno(), student.getSname(),student.getSsex(),student.getSage(), student.getSpass(), student.getDno(), student.getSclass());
         this.dname=dname;
    }

}
