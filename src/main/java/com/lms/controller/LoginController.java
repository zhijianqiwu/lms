package com.lms.controller;

import com.lms.data.Result;
import com.lms.data.User;
import com.lms.mapper.AdminMapper;
import com.lms.mapper.StudentMapper;
import com.lms.mapper.TeacherMapper;
import com.lms.pojo.Admin;
import com.lms.pojo.Student;
import com.lms.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    //打开程序来到登录页
    @GetMapping(value = {"/","/login"})
    public String toLogin(){
        return "login";
    }


    @PostMapping("/userLogin")
    @ResponseBody
    public Result<Object> login(User user, HttpSession session){
        if(user==null||user.getIdentity()==null||user.getPassword()==null||user.getNumber()==null){
            return Result.fail("传入数据不完整");
        }
        switch (user.getIdentity()) {
            case "管理员":
                Admin admin=adminMapper.selectByPrimaryKey(user.getNumber());
                if(admin==null){
                    return Result.fail("账号输入错误");}
                if(!admin.getApass().equals(user.getPassword())){
                    return Result.fail("密码输入错误");
                }
                session.setAttribute("identity",user.getIdentity());
                session.setAttribute("admin",admin);
                return Result.success("登录成功");
            case "学生":
                Student student=studentMapper.selectByPrimaryKey(user.getNumber());
                if(student==null){
                    return Result.fail("账号输入错误");}
                if(!student.getSpass().equals(user.getPassword())){
                    return Result.fail("密码输入错误");
                }
                session.setAttribute("identity",user.getIdentity());
                session.setAttribute("student",student);
                return Result.success("登录成功");
            case "教师":
                Teacher teacher=teacherMapper.selectByPrimaryKey(user.getNumber());
                if(teacher==null){
                    return Result.fail("账号输入错误");}
                if(!teacher.getTpass().equals(user.getPassword())){
                    return Result.fail("密码输入错误");
                }
                session.setAttribute("identity",user.getIdentity());
                session.setAttribute("teacher",teacher);
                return Result.success("登录成功");

        }
        return Result.fail("传入数据发生错误");
    }



}
