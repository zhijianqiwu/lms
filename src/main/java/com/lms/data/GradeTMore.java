package com.lms.data;

import com.lms.pojo.Grade;
import lombok.Data;

@Data
public class GradeTMore extends Grade{
    String sname;
    String cname;
    public GradeTMore(Grade grade,String sname,String cname){
        super(grade.getSno(),grade.getLno(),grade.getScore());
        this.sname=sname;
        this.cname=cname;
    }
}
