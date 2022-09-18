package com.lms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade extends GradeKey {
    private Integer score;
public Grade(String sno,String lno,Integer score){
    setSno(sno);
    setLno(lno);
    this.score=score;
}

}