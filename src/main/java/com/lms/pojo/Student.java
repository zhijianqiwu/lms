package com.lms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String sno;

    private String sname;

    private String ssex;

    private Integer sage;

    private String spass="123456";

    private String dno;

    private String sclass;


}