package com.lms.data;

import com.lms.pojo.Room;
import lombok.Data;

@Data
public class RoomMore extends Room {
    String bname;
    public RoomMore(Room room,String bname){
        super(room.getRno(), room.getRname(), room.getRcapacity(), room.getBno());
        this.bname=bname;
    }
}
