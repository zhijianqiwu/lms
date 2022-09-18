package com.lms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String rno;

    private String rname;

    private Integer rcapacity;

    private String bno;


}