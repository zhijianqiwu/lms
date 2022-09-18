package com.lms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String tno;

    private String tname;

    private String tsex;

    private Integer tage;

    private String tpass="123456";

    private String dno;


}