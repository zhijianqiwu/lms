package com.lms.mapper;

import com.lms.pojo.Admin;
import com.lms.pojo.AdminExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(String ano);

    int insert(Admin record);

    int insertSelective(Admin record);//Selective选择性，区别在于有空的属性不设置为null，普通设置为null

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(String ano);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}