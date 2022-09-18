package com.lms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private String lno;

    private String cno;

    private String rno;

    private String tno;

    private Integer csweek;

    private Integer ceweek;

    private Integer cday;

    private Integer csnumber;

    private Integer cwnumber;


}