package com.lms.controller;

import com.github.pagehelper.PageHelper;
import com.lms.data.GradeMore;
import com.lms.data.GradeTMore;
import com.lms.data.LessonMore;
import com.lms.data.Result;
import com.lms.mapper.*;
import com.lms.pojo.*;
import com.lms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    BuildingMapper buildingMapper;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    LessonMapper lessonMapper;

    /*****************************************1.页面跳转*********************************************/



    @GetMapping("")
    public String toIndex(){
        return "teacher/index";
    }
    @GetMapping("/welcome")
    public String toWelcome(){
        return "teacher/welcome";
    }
    @GetMapping("/userMess")
    public String toUserInfo(HttpSession session,Model model){
        Teacher teacher =(Teacher) session.getAttribute("teacher");
        model.addAttribute("teacher",teacher);
        List<Department> list = departmentMapper.selectByExample(null);

        model.addAttribute("departmentList",list);
        return "teacher/userMess";
    }
    @GetMapping("/userUpdate")
    public String toUserUpdate(){
        return "teacher/userUpdate";
    }

    @GetMapping("/lessonList")
    public String toLessonList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "teacher/lessonList";
    }

    @GetMapping("/studentList")
    public String toStudentList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        return "teacher/studentList";
    }

    @GetMapping("/gradeList")
    public String toGradeList(Model model,HttpSession session){
        //老师教的课程
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        LessonExample example1 = new LessonExample();
            example1.createCriteria().andTnoEqualTo(teacher.getTno());
        List<Lesson> lessons1 = lessonMapper.selectByExample(example1);

        List<String> res=new LinkedList<>();//老师教课程编号
        for(Lesson l:lessons1){
            res.add(l.getCno());
        }
        CourseExample example = new CourseExample();
        example.createCriteria().andCnoIn(res);
        List<Course> list = courseMapper.selectByExample(example);
        model.addAttribute("courseList",list);
        return "teacher/gradeList";
    }


    @GetMapping("/getLesson/{lno}")
    public String ToLessonUpdate(@PathVariable("lno") String lno, Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        Lesson lesson = lessonMapper.selectByPrimaryKey(lno);
        model.addAttribute("lesson",lesson);
        return "teacher/lessonUpdate";
    }

    @ResponseBody
    @PostMapping("/updateLesson")
    public Result<Object> updateLesson(Lesson lesson,HttpSession session){
        if(lesson==null)
            return Result.fail("传入数据为空");
        Teacher teacher = (Teacher)session.getAttribute("teacher");
        if(StringUtil.checkString(teacher.getTno()))
            lesson.setTno(teacher.getTno());

        if(lesson.getCsnumber()!=null){
            if(lesson.getCsnumber()==1) lesson.setCwnumber(4);
            if(lesson.getCsnumber()==5) lesson.setCwnumber(8);
            if(lesson.getCsnumber()==9) lesson.setCwnumber(11);
        }
        if(lesson.getCsweek()!=null){
            if(lesson.getCsweek()==1) lesson.setCwnumber(10);
            if(lesson.getCsweek()==2) lesson.setCwnumber(11);
            if(lesson.getCsweek()==3) lesson.setCwnumber(12);
            if(lesson.getCsweek()==4) lesson.setCwnumber(13);

        }
        lessonMapper.updateByPrimaryKey(lesson);
        return Result.success("用户修改成功");
    }

    @GetMapping("/getGrade/{sno}/{lno}")
    public String ToDepartmentUpdate(@PathVariable("sno") String sno,
                                     @PathVariable("lno") String lno,Model model){
        GradeKey key = new GradeKey(sno, lno);
        Grade grade = gradeMapper.selectByPrimaryKey(key);
        model.addAttribute("grade",grade);
        return "teacher/gradeUpdate";
    }

    @PostMapping("/userMess")
    @ResponseBody
    public Result<Object> toUserMess(Teacher
                                     teacher,HttpSession session){
        teacherMapper.updateByPrimaryKey(teacher);
        session.setAttribute("teacher",teacher);
        return Result.success("成功修改个人信息");
    }


    @ResponseBody
    @PostMapping("/updateGrade")
    public Result<Object> updateGrade(Grade grade){
        if(grade==null)
            return Result.fail("传入数据为空");
        gradeMapper.updateByPrimaryKey(grade);
        return Result.success("对象修改成功");
    }

    //管理学生成绩
    @ResponseBody
    @RequestMapping("/getGrade")
    public Result<Object> getGrade(Integer page,
                                   Integer limit,
                                   String lno,
                                   String cno,
                                   String sno,
                                   HttpSession session
    ){
        List<String> res=new LinkedList<>();//老师教的授课编号
        if(StringUtil.checkString(lno)){
            res.add(lno);
        }else{
        Teacher teacher= (Teacher) session.getAttribute("teacher");
        LessonExample example1 = new LessonExample();
        if(StringUtil.checkString(cno))
            example1.createCriteria().andTnoEqualTo(teacher.getTno()).andCnoEqualTo(cno);
        else
           example1.createCriteria().andTnoEqualTo(teacher.getTno());
        List<Lesson> lessons1 = lessonMapper.selectByExample(example1);
        for(Lesson l:lessons1){
            res.add(l.getLno());
        }
        }

        GradeExample example = new GradeExample();
        GradeExample.Criteria in = example.createCriteria().andLnoIn(res);
        if(StringUtil.checkString(sno))
            in.andSnoEqualTo(sno);

        PageHelper.startPage(page, limit);
        List<Grade> grades = gradeMapper.selectByExample(example);
        Long count= (long) gradeMapper.selectByExample(example).size();
        List<GradeTMore> data=new LinkedList<>();
        for(Grade grade:grades ){
            String sname="";
            String cname="";
            if(StringUtil.checkString(grade.getSno()))
                sname = studentMapper.selectByPrimaryKey(grade.getSno()).getSname();
            if(StringUtil.checkString(grade.getLno())){
                String cno1 = lessonMapper.selectByPrimaryKey(grade.getLno()).getCno();
                cname=courseMapper.selectByPrimaryKey(cno1).getCname();
            }

            data.add(new GradeTMore(grade,sname,cname));
        }
        return Result.success(count,data);
    }




    //查看已选择的课表
    @ResponseBody
    @RequestMapping("/getLesson")
    public Result<Object> getLesson(Integer page,
                                    Integer limit,
                                    String lno,
                                    String cno,
                                    String rno,
                                    String csweek,
                                    String cday,
                                    String csnumber,
                                    HttpSession session
    ){
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String tno = teacher.getTno();
        LessonExample example = new LessonExample();
        LessonExample.Criteria in = example.createCriteria().andTnoEqualTo(tno);
        if(StringUtil.checkString(lno)){
            in.andLnoEqualTo(lno);
        }else {
            if (StringUtil.checkString(cno)) {
                LessonExample.Criteria criteria = in.andCnoEqualTo(cno);
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria1 = criteria.andRnoEqualTo(rno);
                    if(StringUtil.checkString(csweek)){
                        LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }else{
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }
                }else{
                    if(StringUtil.checkString(csweek)){
                        LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }else{
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }


                }
            }else{
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria = in.andRnoEqualTo(rno);
                    if(StringUtil.checkString(csweek)){
                        LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }else{
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }
                }else{
                    if(StringUtil.checkString(csweek)){
                        LessonExample.Criteria criteria = in.andCsweekEqualTo(Integer.valueOf(csweek));
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }else{
                        if(StringUtil.checkString(cday)){
                            LessonExample.Criteria criteria = in.andCdayEqualTo(Integer.valueOf(cday));
                            if(StringUtil.checkString(csnumber))
                                criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));

                        }else{
                            if(StringUtil.checkString(csnumber))
                                in.andCsnumberEqualTo(Integer.valueOf(csnumber));
                        }

                    }


                }
            }
        }

        PageHelper.startPage(page, limit);
        List<Lesson> lessons = lessonMapper.selectByExample(example);
        Long count= (long) lessonMapper.selectByExample(example).size();
        List<LessonMore> data=new LinkedList<>();
        for(Lesson lesson:lessons ){
            String cname="";
            String tname="";
            String rname="";
            if(StringUtil.checkString(lesson.getCno()))
                cname = courseMapper.selectByPrimaryKey(lesson.getCno()).getCname();
            if(StringUtil.checkString(lesson.getTno()))
                tname = teacherMapper.selectByPrimaryKey(lesson.getTno()).getTname();
            if(StringUtil.checkString(lesson.getRno()))
                rname=roomMapper.selectByPrimaryKey(lesson.getRno()).getRname();
            data.add(new LessonMore(lesson, cname, tname,rname));
        }
        return Result.success(count,data);
    }



}
