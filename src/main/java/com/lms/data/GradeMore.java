package com.lms.data;

import com.lms.pojo.Grade;
import com.lms.pojo.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GradeMore extends LessonMore{
    Integer score;
    public GradeMore(Lesson lesson, String cname,  String tname, String rname,Integer score){
        super(lesson,cname, tname,rname);
        this.score=score;
    }
}
