package com.lms.controller;

import com.github.pagehelper.PageHelper;
import com.lms.data.*;
import com.lms.mapper.*;
import com.lms.pojo.*;
import com.lms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

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

    /**************************1.1首页跳转**********************************/
    //去到显示学生的页面
    @GetMapping("")
    public String toIndex(){
        return "admin/index";
    }
    @GetMapping("/welcome")
    public String toWelcome(){
        return "admin/welcome";
    }
    @GetMapping("/userMess")
    public String toUserInfo(HttpSession session,Model model){
        Admin admin =(Admin) session.getAttribute("admin");
        model.addAttribute("admin",admin);
        return "admin/userMess";
    }

    @GetMapping("/userUpdate")
    public String toUserUpdate(){
        return "admin/userUpdate";
    }


    @PostMapping("/userMess")
    @ResponseBody
    public Result<Object> toUserMess(Admin admin,HttpSession session){
        adminMapper.updateByPrimaryKey(admin);
        session.setAttribute("admin",admin);
        return Result.success("成功修改个人信息");
    }



    /**************************1.1显示页跳转**********************************/
    @GetMapping("/studentList")
    public String toStudentList(Model model){
        List<Department> list = departmentMapper.selectByExample(null);
        model.addAttribute("departmentList",list);
        return "admin/studentList";
    }
    @GetMapping("/teacherList")
    public String toTeacherList(Model model){
        List<Department> list = departmentMapper.selectByExample(null);
        model.addAttribute("departmentList",list);
        return "admin/teacherList";
    }

    @GetMapping("/courseList")
    public String toCourseList(){
        return "admin/courseList";
    }

    @GetMapping("/departmentList")
    public String toDepartmentList(){
        return "admin/departmentList";
    }
    @GetMapping("/roomList")
    public String toRoomList(Model model){
        List<Building> list = buildingMapper.selectByExample(null);
        model.addAttribute("buildingList",list);
        return "admin/roomList";
    }
    @GetMapping("/lessonList")
    public String toLessonList(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        return "admin/lessonList";
    }
    @GetMapping("/gradeList")
    public String toGradeList(Model model){
        return "admin/gradeList";
    }
    @GetMapping("/buildingList")
    public String toBuildingList(){
        return "admin/buildingList";
    }
    @GetMapping("/adminList")
    public String toAdminList(){
        return "admin/adminList";
    }


    /**************************1.2增加页跳转**********************************/
    @GetMapping("/studentAdd")
    public String toStudentAdd(Model model){
        List<Department> list = departmentMapper.selectByExample(null);
        model.addAttribute("departmentList",list);
        return "admin/studentAdd";
    }
    @GetMapping("/teacherAdd")
    public String toTeacherAdd(Model model){
        List<Department> list = departmentMapper.selectByExample(null);
        model.addAttribute("departmentList",list);
        return "admin/teacherAdd";
    }
    @GetMapping("/adminAdd")
    public String toAdminAdd(Model model){
        return "admin/adminAdd";
    }
    @GetMapping("/roomAdd")
    public String toRoomAdd(Model model){
        List<Building> list = buildingMapper.selectByExample(null);
        model.addAttribute("buildingList",list);
        return "admin/roomAdd";
    }
    @GetMapping("/buildingAdd")
    public String toBuildingAdd(){
        return "admin/buildingAdd";
    }
    @GetMapping("/courseAdd")
    public String toCourseAdd(){
        return "admin/courseAdd";
    }
    @GetMapping("/gradeAdd")
    public String toGradeAdd(Model model){
        return "admin/gradeAdd";
    }
    @GetMapping("/lessonAdd")
    public String toLessonAdd(Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);

        return "admin/lessonAdd";
    }
    @GetMapping("/departmentAdd")
    public String toDepartmentAdd(){
        return "admin/departmentAdd";
    }
    /**************************1.3删除页跳转**********************************/

    @GetMapping("/studentDelete")
    public String toStudentDelete(Model model){
        return "admin/studentDelete";
    }
    @GetMapping("/teacherDelete")
    public String toTeacherDelete(Model model){
        return "admin/teacherDelete";
    }
    @GetMapping("/courseDelete")
    public String toCourseDelete(Model model){
        return "admin/courseDelete";
    }
    @GetMapping("/departmentDelete")
    public String toDepartmentDelete(Model model){
        return "admin/departmentDelete";
    }
    @GetMapping("/buildingDelete")
    public String toBuildingDelete(Model model){
        return "admin/buildingDelete";
    }
    @GetMapping("/lessonDelete")
    public String toLessonDelete(Model model){
        return "admin/lessonDelete";
    }   @GetMapping("/gradeDelete")
    public String toGradeDelete(Model model){
        return "admin/gradeDelete";
    }
    @GetMapping("/roomDelete")
    public String toRoomDelete(Model model){
        return "admin/roomDelete";
    }
    @GetMapping("/adminDelete")
    public String toAdminDelete(Model model){
        return "admin/adminDelete";
    }


    /**************************1.4更新页跳转**********************************/

    @GetMapping("/getStudent/{sno}")
    public String ToStudentupdate(@PathVariable("sno") String sno, Model model){
        Student student = studentMapper.selectByPrimaryKey(sno);

        model.addAttribute("student",student);
        DepartmentExample example = new DepartmentExample();
        example.setOrderByClause("dno");
        List<Department> list = departmentMapper.selectByExample(example);

        model.addAttribute("departmentList",list);
        return "admin/studentUpdate";
    }
    @GetMapping("/getTeacher/{tno}")
    public String ToTeacherUpdate(@PathVariable("tno") String tno, Model model){
        Teacher teacher = teacherMapper.selectByPrimaryKey(tno);
        model.addAttribute("teacher",teacher);
        DepartmentExample example = new DepartmentExample();
        List<Department> list = departmentMapper.selectByExample(example);

        model.addAttribute("departmentList",list);
        return "admin/teacherUpdate";
    }

    @GetMapping("/getAdmin/{ano}")
    public String ToAdminUpdate(@PathVariable("ano") String ano, Model model){
        Admin admin = adminMapper.selectByPrimaryKey(ano);
        model.addAttribute("admin",admin);
        return "admin/adminUpdate";
    }

    @GetMapping("/getCourse/{cno}")
    public String ToCourseUpdate(@PathVariable("cno") String cno, Model model){
        Course course = courseMapper.selectByPrimaryKey(cno);
        model.addAttribute("course",course);
        return "admin/courseUpdate";
    }

    @GetMapping("/getBuilding/{bno}")
    public String ToBuildingUpdate(@PathVariable("bno") String bno, Model model){
        Building building = buildingMapper.selectByPrimaryKey(bno);
        model.addAttribute("building",building);
        return "admin/buildingUpdate";
    }

    @GetMapping("/getDepartment/{dno}")
    public String ToDepartmentUpdate(@PathVariable("dno") String dno, Model model){
        Department department = departmentMapper.selectByPrimaryKey(dno);
        model.addAttribute("department",department);
        return "admin/departmentUpdate";
    }

    @GetMapping("/getGrade/{sno}/{lno}")
    public String ToDepartmentUpdate(@PathVariable("sno") String sno,
                                     @PathVariable("lno") String lno,Model model){
        GradeKey key = new GradeKey(sno, lno);
        Grade grade = gradeMapper.selectByPrimaryKey(key);
        model.addAttribute("grade",grade);
        return "admin/gradeUpdate";
    }
    @GetMapping("/getLesson/{lno}")
    public String ToLessonUpdate(@PathVariable("lno") String lno, Model model){
        List<Course> list = courseMapper.selectByExample(null);
        model.addAttribute("courseList",list);
        List<Room> list1 = roomMapper.selectByExample(null);
        model.addAttribute("roomList",list1);
        List<Teacher> list2 = teacherMapper.selectByExample(null);
        model.addAttribute("teacherList",list2);
        Lesson lesson = lessonMapper.selectByPrimaryKey(lno);
        model.addAttribute("lesson",lesson);
        return "admin/lessonUpdate";
    }
    @GetMapping("/getRoom/{rno}")
    public String ToRoomUpdate(@PathVariable("rno") String rno, Model model){
        List<Building> list = buildingMapper.selectByExample(null);
        model.addAttribute("buildingList",list);
        Room room = roomMapper.selectByPrimaryKey(rno);
        model.addAttribute("room",room);
        return "admin/roomUpdate";
    }

    /*****************************************2.获取内容*********************************************/

    /**************************2.1根据条件获取对象列表**********************************/

    @ResponseBody
    @RequestMapping("/getStudent")
    public Result<Object> getStudent(Integer page,
                                 Integer limit,
                                 String sno,
                                 String sname,
                                 String dno,
                                 String sclass){
        StudentExample example = new StudentExample();
        if(StringUtil.checkString(sno)){
            example.createCriteria().andSnoEqualTo(sno);
        }else {
            if (StringUtil.checkString(sname)) {
                StudentExample.Criteria c1 = example.createCriteria().andSnameLike("%" + sname + "%");
                if(StringUtil.checkString(dno)&&StringUtil.checkString(sclass))
                    c1.andDnoEqualTo(dno).andSclassEqualTo(sclass);
                else if(StringUtil.checkString(dno))
                    c1.andDnoEqualTo(dno);
                else if(StringUtil.checkString(sclass))
                    c1.andSclassEqualTo(sclass);
            }else{
                if(StringUtil.checkString(dno)&&StringUtil.checkString(sclass))
                    example.createCriteria().andDnoEqualTo(dno).andSclassEqualTo(sclass);
                else if(StringUtil.checkString(dno))
                    example.createCriteria().andDnoEqualTo(dno);
                else if(StringUtil.checkString(sclass))
                    example.createCriteria().andSclassEqualTo(sclass);

            }
        }
        PageHelper.startPage(page, limit);
        example.setOrderByClause("sno");
        List<Student> students = studentMapper.selectByExample(example);
        Long count= (long) studentMapper.selectByExample(example).size();
        List<StudentMore> data=new LinkedList<>();
        for(Student student:students){
            String dname="";
            if(StringUtil.checkString(student.getDno()))
               dname = departmentMapper.selectByPrimaryKey(student.getDno()).getDname();

            data.add(new StudentMore(student,dname));
        }
        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getAdmin")
    public Result<Object> getAdmin(Integer page,
                             Integer limit,
                             String ano,
                             String aname
                             ){
        AdminExample example = new AdminExample();
        if(StringUtil.checkString(ano)&&StringUtil.checkString(aname))
            example.createCriteria().andAnoEqualTo(ano).andAnameLike("%" + aname + "%");
        else if(StringUtil.checkString(ano))
            example.createCriteria().andAnoEqualTo(ano);
        else if(StringUtil.checkString(aname))
            example.createCriteria().andAnameLike("%" + aname + "%");


        PageHelper.startPage(page, limit);
        List<Admin> data = adminMapper.selectByExample(example);
        Long count= (long) adminMapper.selectByExample(example).size();
        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getTeacher")
    public Result<Object> getTeacher(Integer page,
                                     Integer limit,
                                     String tno,
                                     String tname,
                                     String dno
    ){
        TeacherExample example = new TeacherExample();
        if(StringUtil.checkString(tno)){
            example.createCriteria().andTnoEqualTo(tno);
        }else {
            if(StringUtil.checkString(dno)&&StringUtil.checkString(tname))
                example.createCriteria().andDnoEqualTo(dno).andTnameLike("%" + tname + "%");
            else if(StringUtil.checkString(dno))
                example.createCriteria().andDnoEqualTo(dno);
            else if(StringUtil.checkString(tname))
                example.createCriteria().andTnameLike("%" + tname + "%");

        }

        PageHelper.startPage(page, limit);
        List<Teacher> teachers = teacherMapper.selectByExample(example);
        Long count= (long) teacherMapper.selectByExample(example).size();
        List<TeacherMore> data=new LinkedList<>();
        for(Teacher teacher:teachers){
            String dname="";
            if(StringUtil.checkString(teacher.getDno())) {
               dname = departmentMapper.selectByPrimaryKey(teacher.getDno()).getDname();
            }
            data.add(new TeacherMore(teacher,dname));
        }

        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getCourse")
    public Result<Object> getCourse(Integer page,
                                   Integer limit,
                                   String cno,
                                   String cname
    ){
        CourseExample example = new CourseExample();
        if(StringUtil.checkString(cno)&&StringUtil.checkString(cname))
            example.createCriteria().andCnoEqualTo(cno).andCnameLike("%" + cname + "%");
        else if(StringUtil.checkString(cno))
            example.createCriteria().andCnoEqualTo(cno);
        else if(StringUtil.checkString(cname))
            example.createCriteria().andCnameLike("%" + cname + "%");


        PageHelper.startPage(page, limit);
        List<Course> data = courseMapper.selectByExample(example);
        Long count= (long) courseMapper.selectByExample(example).size();
        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getBuilding")
    public Result<Object> getBuilding(Integer page,
                                   Integer limit,
                                   String bno,
                                   String bname
    ){
        BuildingExample example = new BuildingExample();
        if(StringUtil.checkString(bno)&&StringUtil.checkString(bname))
            example.createCriteria().andBnoEqualTo(bno).andBnameLike("%" + bname + "%");
        else if(StringUtil.checkString(bno))
            example.createCriteria().andBnoEqualTo(bno);
        else if(StringUtil.checkString(bname))
            example.createCriteria().andBnameLike("%" + bname + "%");


        PageHelper.startPage(page, limit);
        List<Building> data = buildingMapper.selectByExample(example);
        Long count= (long) buildingMapper.selectByExample(example).size();
        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getRoom")
    public Result<Object> getRoom(Integer page,
                                   Integer limit,
                                   String rno,
                                   String rname,
                                  String bno
    ){
        RoomExample example = new RoomExample();
        if(StringUtil.checkString(rno)){
            example.createCriteria().andRnoEqualTo(rno);
        }else {
            if(StringUtil.checkString(bno)&&StringUtil.checkString(rname))
                example.createCriteria().andBnoEqualTo(bno).andRnameLike("%" + rname + "%");
            else if(StringUtil.checkString(bno))
                example.createCriteria().andBnoEqualTo(bno);
            else if(StringUtil.checkString(rname))
                example.createCriteria().andRnameLike("%" + rname + "%");
        }


        PageHelper.startPage(page, limit);
        List<Room> rooms = roomMapper.selectByExample(example);
        Long count= (long) roomMapper.selectByExample(example).size();
        List<RoomMore> data=new ArrayList<>();
        for(Room room:rooms){
            String bname="";
            if(StringUtil.checkString(room.getBno()))
                bname=buildingMapper.selectByPrimaryKey(room.getBno()).getBname();
            data.add(new RoomMore(room,bname));
        }
        return Result.success(count,data);
    }


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
                                  String csnumber
    ){
        LessonExample example = new LessonExample();
        if(StringUtil.checkString(lno)){
            example.createCriteria().andLnoEqualTo(lno);
        }else {
            if (StringUtil.checkString(cno)) {
                LessonExample.Criteria criteria = example.createCriteria().andCnoEqualTo(cno);
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
                    LessonExample.Criteria criteria = example.createCriteria().andRnoEqualTo(rno);
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
                        LessonExample.Criteria criteria = example.createCriteria().andTnoEqualTo(tno);
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
                            LessonExample.Criteria criteria = example.createCriteria().andCsweekEqualTo(Integer.valueOf(csweek));
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
                                LessonExample.Criteria criteria = example.createCriteria().andCdayEqualTo(Integer.valueOf(cday));
                                if (StringUtil.checkString(csnumber))
                                    criteria.andCsnumberEqualTo(Integer.valueOf(csnumber));

                            }else{
                                if (StringUtil.checkString(csnumber))
                                    example.createCriteria().andCsnumberEqualTo(Integer.valueOf(csnumber));

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
            LessonMore more = new LessonMore(lesson, cname,  tname,rname);
            data.add(more);
        }

        return Result.success(count,data);
    }

    @ResponseBody
    @RequestMapping("/getGrade")
    public Result<Object> getGrade(Integer page,
                                  Integer limit,
                                  String sno,
                                  String lno
    ){

            GradeExample example = new GradeExample();
            if(StringUtil.checkString(sno)&&StringUtil.checkString(lno))
                example.createCriteria().andSnoEqualTo(sno).andLnoEqualTo(lno);
           else if(StringUtil.checkString(sno))
                example.createCriteria().andSnoEqualTo(sno);
            else if(StringUtil.checkString(lno))
                example.createCriteria().andLnoEqualTo(lno);
           PageHelper.startPage(page, limit);
            List<Grade> data = gradeMapper.selectByExample(example);
           Long count= (long)gradeMapper.selectByExample(example).size();
           return Result.success(count,data);

    }


    @ResponseBody
    @RequestMapping("/getDepartment")
    public Result<Object> getDepartment(Integer page,
                                    Integer limit,
                                    String dno,
                                    String dname
    ){
        DepartmentExample example = new DepartmentExample();
        if(StringUtil.checkString(dno))
            example.createCriteria().andDnoEqualTo(dno);
        else if(StringUtil.checkString(dname))
            example.createCriteria().andDnameLike("%" + dname + "%");


        PageHelper.startPage(page, limit);
        List<Department> data = departmentMapper.selectByExample(example);
        Long count= (long) departmentMapper.selectByExample(example).size();
        return Result.success(count,data);
    }

    /**************************2.2增加对象结果**********************************/
    @ResponseBody
    @PostMapping("/addStudent")
    public Result<Object> addStudent(Student student){
        Student key = studentMapper.selectByPrimaryKey(student.getSno());
        if(key==null){
            if(StringUtil.checkString(student.getSpass()))
                student.setSpass("123456");
        studentMapper.insertSelective(student);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,学号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addTeacher")
    public Result<Object> addTeacher(Teacher teacher){
        Teacher key = teacherMapper.selectByPrimaryKey(teacher.getTno());
        if(key==null){
            if(StringUtil.checkString(teacher.getTpass()))
                teacher.setTpass("123456");
            teacherMapper.insertSelective(teacher);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addAdmin")
    public Result<Object> addAdmin(Admin admin){
        Admin key = adminMapper.selectByPrimaryKey(admin.getAno());
        if(key==null){
            if(StringUtil.checkString(admin.getApass()))
                admin.setApass("123456");
            adminMapper.insertSelective(admin);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addBuilding")
    public Result<Object> addBuilding(Building building){
        Building key = buildingMapper.selectByPrimaryKey(building.getBno());
        if(key==null){
            buildingMapper.insertSelective(building);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addLesson")
    public Result<Object> addLesson(Lesson lesson){
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
        Lesson key = lessonMapper.selectByPrimaryKey(lesson.getLno());
        if(key==null){
            lessonMapper.insertSelective(lesson);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addCourse")
    public Result<Object> addCourse(Course course){
        Course key = courseMapper.selectByPrimaryKey(course.getCno());
        if(key==null){
            courseMapper.insertSelective(course);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addRoom")
    public Result<Object> addRoom(Room room){
        Room key = roomMapper.selectByPrimaryKey(room.getRno());
        if(key==null){
            roomMapper.insertSelective(room);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }
    @ResponseBody
    @PostMapping("/addGrade")
    public Result<Object> addAdmin(Grade grade){
        if(studentMapper.selectByPrimaryKey(grade.getSno())==null)
            return Result.fail("没有对应的学生");
        if(lessonMapper.selectByPrimaryKey(grade.getLno())==null)
            return Result.fail("没有对应的授课");
        GradeKey key = new GradeKey(grade.getSno(), grade.getLno());
        if(gradeMapper.selectByPrimaryKey(key)!=null)
            return Result.fail("该学生已经选择了该课程");
        gradeMapper.insertSelective(grade);
        return Result.success("对象新增成功");
    }
    @ResponseBody
    @PostMapping("/addDepartment")
    public Result<Object> addDepartment(Department department){
        Department key = departmentMapper.selectByPrimaryKey(department.getDno());
        if(key==null){
            departmentMapper.insertSelective(department);
            return Result.success("对象新增成功");
        }else{
            return Result.fail("对象新增失败,编号要求唯一");
        }
    }

    /**************************2.3获取删除结果**********************************/
    @ResponseBody
    @PostMapping("/deleteStudents/{snos}")
    public Result<Object> deleteStudent(@PathVariable("snos") String snos){
        String[] split = snos.split(",");
        for(String s:split){
            //级联删除grade里面的数据
            GradeExample example = new GradeExample();
            example.createCriteria().andSnoEqualTo(s);
            gradeMapper.deleteByExample(example);
            //删除student数据
            studentMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @PostMapping("/deleteTeachers/{tnos}")
    public Result<Object> deleteTeacher(@PathVariable("tnos") String tnos){
        String[] split = tnos.split(",");
        for(String s:split){

            LessonExample example = new LessonExample();
            example.createCriteria().andTnoEqualTo(s);
            List<Lesson> lessons = lessonMapper.selectByExample(example);
            if(lessons.size()!=0)
                return Result.fail("删除失败，能删除的老师必须没有授课了");
            //删除teacher数据
            teacherMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @RequestMapping("/deleteDepartments/{dnos}")
    public Result<Object> deleteDepartment(@PathVariable("dnos") String dnos){
        String[] split = dnos.split(",");
        for(String s:split){
            StudentExample example = new StudentExample();
            example.createCriteria().andDnoEqualTo(s);
            List<Student> students = studentMapper.selectByExample(example);
            if(students.size()!=0)
                return Result.fail("删除失败，能够删除的学院必须已经没有学生了");
            else
            departmentMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @PostMapping("/deleteCourses/{cnos}")
    public Result<Object> deleteCourse(@PathVariable("cnos") String cnos){
        String[] split = cnos.split(",");
        for(String s:split){
            LessonExample example = new LessonExample();
            example.createCriteria().andCnoEqualTo(s);
            List<Lesson> lessons = lessonMapper.selectByExample(example);
            if(lessons.size()!=0)
                return Result.fail("删除失败，能够删除的课程必须没有老师讲授");
            else
           courseMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @PostMapping("/deleteGrades/{data}")
    public Result<Object> deleteGrade(@PathVariable("data") String data){
        String[] split = data.split(",");
        for(int i=0;i<split.length;i=i+2){
            GradeKey key = new GradeKey(split[i], split[i + 1]);
            gradeMapper.deleteByPrimaryKey(key);
        }
        return Result.success("对象删除成功");

    }
    @ResponseBody
    @RequestMapping("/deleteBuildings/{bnos}")
    public Result<Object> deleteBuilding(@PathVariable("bnos") String bnos){
        String[] split = bnos.split(",");
        for(String s:split){
            //级联删除room里面的数据
            RoomExample example = new RoomExample();
            example.createCriteria().andBnoEqualTo(s);
            List<Room> rooms = roomMapper.selectByExample(example);
            for(Room room:rooms){
                LessonExample example1 = new LessonExample();
                example.createCriteria().andRnoEqualTo(room.getRno());
                List<Lesson> lessons = lessonMapper.selectByExample(example1);
                if(lessons.size()!=0)
                    return Result.fail("删除失败，能够删除的教学楼必须包含的教室没有授课安排");
            }
            roomMapper.deleteByExample(example);
            //删除building数据
            buildingMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @PostMapping("/deleteAdmins/{anos}")
    public Result<Object> deleteAdmin(@PathVariable("anos") String anos){
        String[] split = anos.split(",");
        for(String s:split){
           adminMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }
    @ResponseBody
    @PostMapping("/deleteRooms/{rnos}")
    public Result<Object> deleteRoom(@PathVariable("rnos") String rnos){
        String[] split = rnos.split(",");
        for(String s:split){
            LessonExample example = new LessonExample();
            example.createCriteria().andRnoEqualTo(s);
            List<Lesson> lessons = lessonMapper.selectByExample(example);
            if(lessons.size()!=0)
                return Result.fail("删除失败，能够删除的教室必须没有授课安排");
            else
            roomMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }    @ResponseBody
    @PostMapping("/deleteLessons/{lnos}")
    public Result<Object> deleteLesson(@PathVariable("lnos") String lnos){
        String[] split = lnos.split(",");
        for(String s:split){
            GradeExample example = new GradeExample();
            example.createCriteria().andLnoEqualTo(s);
            List<Grade> grades = gradeMapper.selectByExample(example);
            if(grades.size()!=0)
                return Result.fail("删除失败，能够删除的授课信息必须没有学生选择");
            else
            lessonMapper.deleteByPrimaryKey(s);
        }
        return Result.success("对象删除成功");
    }


    /**************************2.4获取更新结果**********************************/
    @ResponseBody
    @PostMapping("/updateStudent")
    public Result<Object> updateStudent(Student student){
        if(student==null)
        return Result.fail("传入数据为空");
       studentMapper.updateByPrimaryKey(student);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateTeacher")
    public Result<Object> updateTeacher(Teacher teacher){
        if(teacher==null)
            return Result.fail("传入数据为空");
        teacherMapper.updateByPrimaryKey(teacher);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateAdmin")
    public Result<Object> updateAdmin(Admin admin,HttpSession session){
        if(admin==null)
            return Result.fail("传入数据为空");
        adminMapper.updateByPrimaryKey(admin);

        Admin admin1 =(Admin) session.getAttribute("admin");
        if(admin1.getAno().equals(admin.getAno())){
            session.setAttribute("admin",admin);
        }
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateRoom")
    public Result<Object> updateRoom(Room room){
        if(room==null)
            return Result.fail("传入数据为空");
        roomMapper.updateByPrimaryKey(room);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateBuilding")
    public Result<Object> updateBuilding(Building building){
        if(building==null)
            return Result.fail("传入数据为空");
        buildingMapper.updateByPrimaryKey(building);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateCourse")
    public Result<Object> updateCourse(Course course ){
        if(course==null)
            return Result.fail("传入数据为空");
        courseMapper.updateByPrimaryKey(course);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateDepartment")
    public Result<Object> updateDepartment(Department department){
        if(department==null)
            return Result.fail("传入数据为空");
        departmentMapper.updateByPrimaryKey(department);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateGrade")
    public Result<Object> updateGrade(Grade grade){
        if(grade==null)
            return Result.fail("传入数据为空");
        gradeMapper.updateByPrimaryKey(grade);
        return Result.success("用户修改成功");
    }
    @ResponseBody
    @PostMapping("/updateLesson")
    public Result<Object> updateLesson(Lesson lesson){
        if(lesson==null)
            return Result.fail("传入数据为空");
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


@RequestMapping("/updatePass")
@ResponseBody
public Result<Object> updatePass(String pass,String new_pass,String again_pass,HttpSession session){
        if(!again_pass.equals(new_pass))
            return Result.fail("新密码两次填写不一致");
    Admin admin = (Admin) session.getAttribute("admin");
    if(pass.equals(admin.getApass())) {
        admin.setApass(new_pass);
        adminMapper.updateByPrimaryKey(admin);
        session.setAttribute("admin",admin);
        return Result.success();
    }
    else
        return Result.fail("旧的密码填写不正确");

}

}
