package com.lms.controller;


import com.github.pagehelper.PageHelper;
import com.lms.data.GradeMore;
import com.lms.data.LessonMore;
import com.lms.data.Result;
import com.lms.mapper.*;
import com.lms.pojo.*;
import com.lms.util.StringUtil;
import org.apache.coyote.http11.upgrade.UpgradeGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

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
        return "student/index";
    }
    @GetMapping("/welcome")
    public String toWelcome(){
        return "student/welcome";
    }
    @GetMapping("/userMess")
    public String toUserInfo(HttpSession session,Model model){
        Student student =(Student) session.getAttribute("student");
        model.addAttribute("student",student);
        List<Department> list = departmentMapper.selectByExample(null);

        model.addAttribute("departmentList",list);
        return "student/userMess";
    }
    @GetMapping("/userUpdate")
    public String toUserUpdate(){
        return "student/userUpdate";
    }


    @GetMapping("/takeList")
    public String toTakeList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "student/takeList";
    }

    @GetMapping("/lessonList")
    public String toLessonList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "student/lessonList";
    }
    @GetMapping("/deleteList")
    public String toDeleteList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "student/deleteList";
    }

    @GetMapping("/gradeList")
    public String toGradeList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "student/gradeList";
    }



    /*****************************************2.获取内容*********************************************/

    @PostMapping("/userMess")
    @ResponseBody
    public Result<Object> toUserMess(Student student,HttpSession session){
        studentMapper.updateByPrimaryKey(student);
        session.setAttribute("student",student);
        return Result.success("成功修改个人信息");
    }

    //获取未选择的课程
    @ResponseBody
    @RequestMapping("/takeLesson")
    public Result<Object> takeLesson(Integer page,
                                    Integer limit,
                                    String lno,
                                    String cno,
                                    String rno,
                                    String tno,
                                    String csweek,
                                    String cday,
                                    String csnumber,
                                    HttpSession session
    ){
        Student student = (Student) session.getAttribute("student");
        GradeExample example1 = new GradeExample();
        example1.createCriteria().andSnoEqualTo(student.getSno());
        List<Grade> grades = gradeMapper.selectByExample(example1);
        List<String> res=new LinkedList<>();
        for(Grade g:grades){
            res.add(g.getLno());
        }

        LessonExample example = new LessonExample();
        LessonExample.Criteria in = example.createCriteria().andLnoNotIn(res);
        if(StringUtil.checkString(lno)){
            in.andLnoEqualTo(lno);
        }else {
            if (StringUtil.checkString(cno)) {
                LessonExample.Criteria criteria = in.andCnoEqualTo(cno);
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria1 = criteria.andRnoEqualTo(rno);
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria2 = criteria1.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria3 = criteria2.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria4 = criteria3.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria4.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }
                }else{
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria1 = criteria.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }
                }
            }else{
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria = in.andRnoEqualTo(rno);
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria1 = criteria.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }

                        }
                    }
                }else{
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria = in.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }

                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria = in.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria = in.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    in.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }
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

    //查看已选择的课表
    @ResponseBody
    @RequestMapping("/getLesson")
    public Result<Object> getLesson(Integer page,
                                    Integer limit,
                                    String lno,
                                    String cno,
                                    String rno,
                                    String tno,
                                    String csweek,
                                    String cday,
                                    String csnumber,
                                    HttpSession session
    ){
        Student student = (Student) session.getAttribute("student");
        GradeExample example1 = new GradeExample();
        example1.createCriteria().andSnoEqualTo(student.getSno());
        List<Grade> grades = gradeMapper.selectByExample(example1);
        List<String> res=new LinkedList<>();
        for(Grade g:grades){
            res.add(g.getLno());
        }

        LessonExample example = new LessonExample();
        LessonExample.Criteria in = example.createCriteria().andLnoIn(res);
        if(StringUtil.checkString(lno)){
            in.andLnoEqualTo(lno);
        }else {
            if (StringUtil.checkString(cno)) {
                LessonExample.Criteria criteria = in.andCnoEqualTo(cno);
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria1 = criteria.andRnoEqualTo(rno);
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria2 = criteria1.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria3 = criteria2.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria4 = criteria3.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria4.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }
                }else{
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria1 = criteria.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }
                    }
                }
            }else{
                if (StringUtil.checkString(rno)) {
                    LessonExample.Criteria criteria = in.andRnoEqualTo(rno);
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria1 = criteria.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria2 = criteria1.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria3 = criteria2.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria3.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }

                        }
                    }
                }else{
                    if (StringUtil.checkString(tno)) {
                        LessonExample.Criteria criteria = in.andTnoEqualTo(tno);
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria1 = criteria.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria2 = criteria1.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria2.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }

                        }
                    }else{
                        if (StringUtil.checkString(csweek)) {
                            LessonExample.Criteria criteria = in.andCsweekEqualTo(Integer.valueOf(csweek));
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria1 = criteria.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria1.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));
                            }
                        }else{
                            if (StringUtil.checkString(cday)) {
                                LessonExample.Criteria criteria = in.andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    in.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }
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


    //查看成绩
    @ResponseBody
    @RequestMapping("/getGrade")
    public Result<Object> getGrade(Integer page,
                                    Integer limit,
                                    String lno,
                                    String cno,
                                    String tno,
                                    HttpSession session
    ){
        Student student = (Student) session.getAttribute("student");
        GradeExample example1 = new GradeExample();
        example1.createCriteria().andSnoEqualTo(student.getSno());
        List<Grade> grades = gradeMapper.selectByExample(example1);
        List<String> res=new LinkedList<>();
        for(Grade g:grades){
            res.add(g.getLno());
        }

        LessonExample example = new LessonExample();
        LessonExample.Criteria in = example.createCriteria().andLnoIn(res);
        if(StringUtil.checkString(lno)){
            in.andLnoEqualTo(lno);
        }else {
            if (StringUtil.checkString(cno)&&StringUtil.checkString(tno))
                in.andCnoEqualTo(cno).andTnoEqualTo(tno);
            else if(StringUtil.checkString(cno))
                    in.andCnoEqualTo(cno);
            else if(StringUtil.checkString(tno))
                   in.andTnoEqualTo(tno);
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
            Integer score = gradeMapper.selectByPrimaryKey(new GradeKey(student.getSno(), lesson.getLno())).getScore();
            data.add(new GradeMore(lesson, cname, tname,rname,score));
        }
        return Result.success(count,data);
    }




    //选课
    @RequestMapping("/takeLesson/{lno}")
    @ResponseBody
    public Result ToLessonUpdate(@PathVariable("lno") String lno,HttpSession session){

        Student student = (Student) session.getAttribute("student");
        Lesson lesson = lessonMapper.selectByPrimaryKey(lno);
        //判断是否已经选了其他老师上的该课程
        GradeExample example2 = new GradeExample();
        example2.createCriteria().andSnoEqualTo(student.getSno());
        List<Grade> grades = gradeMapper.selectByExample(example2);
       List<String> res=new LinkedList<>();
        for(Grade g:grades){
           res.add(g.getLno());
       }

        LessonExample example3 = new LessonExample();
        example3.createCriteria().andLnoIn(res).andCnoEqualTo(lesson.getCno());
        List<Lesson> list = lessonMapper.selectByExample(example3);
        if(list.size()!=0)
            return Result.fail("选课失败，你已经选修了其他老师讲授的本课程");

        //****判断课程是否选满

           //获取选中该课的人数
        GradeExample example = new GradeExample();
        example.createCriteria().andLnoEqualTo(lesson.getLno());
        int size = gradeMapper.selectByExample(example).size();
           //获取上课教室容量
        Integer rcapacity = roomMapper.selectByPrimaryKey(lesson.getRno()).getRcapacity();
        if(size==rcapacity){
            return Result.fail("选择该课程的人数已满");
        }

        //****判断时间是否冲突
        LessonExample example1 = new LessonExample();
        example1.createCriteria().andCdayEqualTo(lesson.getCday()).andCsnumberEqualTo(lesson.getCsnumber()).andLnoNotEqualTo(lesson.getLno());
        List<Lesson> lessons = lessonMapper.selectByExample(example1);
        for(Lesson lesson1:lessons){
            if(gradeMapper.selectByPrimaryKey(new GradeKey(student.getSno(),lesson1.getLno()))!=null)
                return Result.fail("和其他已选课程时间冲突，不能选择该课程");
        }
        Grade grade = new Grade();
        grade.setSno(student.getSno());
        grade.setLno(lesson.getLno());
        gradeMapper.insertSelective(grade);
        return Result.success("成功选择该课程");
    }
    //退课
    @RequestMapping("/deleteLesson/{lno}")
    @ResponseBody
    public Result ToDeleteLesson(@PathVariable("lno") String lno,HttpSession session){

        Student student = (Student) session.getAttribute("student");
        Lesson lesson = lessonMapper.selectByPrimaryKey(lno);

        gradeMapper.deleteByPrimaryKey(new GradeKey(student.getSno(),lesson.getLno()));
        return Result.success("成功退选该课程");
    }

    @RequestMapping("/updatePass")
    @ResponseBody
    public Result<Object> updatePass(String pass, String new_pass, String again_pass, HttpSession session){
        if(!again_pass.equals(new_pass))
            return Result.fail("新密码两次填写不一致");
        Student student = (Student) session.getAttribute("student");
        System.out.println(student.getSpass());
        if(pass.equals(student.getSpass())) {
            student.setSpass(new_pass);
            studentMapper.updateByPrimaryKey(student);
            session.setAttribute("student",student);
            return Result.success();
        }
        else
            return Result.fail("旧的密码填写不正确");

    }
}
