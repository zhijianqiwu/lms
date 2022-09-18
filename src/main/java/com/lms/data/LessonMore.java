package com.lms.data;

import com.lms.pojo.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonMore extends Lesson {
    String tname;
    String cname;
    String rname;

    public LessonMore(Lesson lesson, String cname,  String tname,String rname) {
        setLno(lesson.getLno());
        setRno(lesson.getRno());
        setTno(lesson.getTno());
        setCno(lesson.getCno());
        setCsweek(lesson.getCsweek());
        setCeweek(lesson.getCeweek());
        setCday(lesson.getCday());
        setCsnumber(lesson.getCsnumber());
        setCwnumber(lesson.getCwnumber());
        this.tname=tname;
        this.cname=cname;
        this.rname=rname;
    }


}
