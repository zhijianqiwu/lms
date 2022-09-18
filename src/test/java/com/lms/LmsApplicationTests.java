package com.lms;

import com.github.pagehelper.PageHelper;
import com.lms.data.Result;
import com.lms.mapper.AdminMapper;
import com.lms.mapper.StudentMapper;
import com.lms.pojo.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.soap.Addressing;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class LmsApplicationTests {
   @Autowired
    AdminMapper adminMapper;
   @Autowired
   StudentMapper studentMapper;
   @Test
   public void test(){

       StudentExample example = new StudentExample();
       example.createCriteria().andDnoEqualTo("1");
       List<Student> students = studentMapper.selectByExample(example);
       System.out.println(students.size());

   }

    void contextLoads(Integer page,
                      Integer limit,
                      String sno,
                      String sname,
                      String dno,
                      String sclass){
        StudentExample example = new StudentExample();
        if(sno!=null){
            example.createCriteria().andSnoEqualTo(sno);
        }else {
            if (sname != null) {
                StudentExample.Criteria c1 = example.createCriteria().andSnameLike("%" + sname + "%");
                if(dno!=null&&sclass!=null)
                    c1.andDnoEqualTo(dno).andSclassEqualTo(sclass);
                else if(dno!=null)
                    c1.andDnoEqualTo(dno);
                else if(sclass!=null)
                    c1.andSclassEqualTo(sclass);
            }else{
                if(dno!=null&&sclass!=null)
                    example.createCriteria().andDnoEqualTo(dno).andSclassEqualTo(sclass);
                else if(dno!=null)
                    example.createCriteria().andDnoEqualTo(dno);
                else if(sclass!=null)
                    example.createCriteria().andSclassEqualTo(sclass);

            }
        }
        PageHelper.startPage(page, limit);
        List<Student> data = studentMapper.selectByExample(example);
        for (Student admin:data
             ) {
            System.out.println(admin);
        }
        int size = studentMapper.selectByExample(example).size();
        System.out.println("size"+size);
    }

}
