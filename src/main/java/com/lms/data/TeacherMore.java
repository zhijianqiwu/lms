package com.lms.data;

import com.lms.pojo.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherMore extends Teacher {
    String dname;
    public TeacherMore(Teacher teacher,String dname){
        super(teacher.getTno(),teacher.getTname(),teacher.getTsex(),teacher.getTage(),teacher.getTpass(),teacher.getDno());
      this.dname=dname;
    }
}
