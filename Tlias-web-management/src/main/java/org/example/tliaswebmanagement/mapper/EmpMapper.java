package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.EmpQueryParam;
import org.example.tliaswebmanagement.pojo.LoginInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//-----------------------------------原始分页查询-------------------------------

//    @Select("select e.*, d.name deptName from emp e left join dept d on dept_id = d.id order by e.update_time desc limit #{page},#{pageSize}")
//    List<Emp> find(Integer page, Integer pageSize);
//
//    @Select("select count(*) from emp e left join dept d on dept_id = d.id")
//    long findCount();


    //-----------------------------------PageHelper---------------------------------
    // @Select("select e.*, d.name deptName from emp e left join dept d on dept_id = d.id order by e.update_time desc")
    //条件查询员工信息
    List<Emp> find(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);

    void delete(List<Integer> ids);

    List<Emp> findByIds(List<Integer> ids);

    void update(Emp emp);

    @Select("select e.*,d.name deptName from emp e left join dept d on dept_id=d.id where e.id=#{id}")
    Emp findById(Integer id);

    @MapKey("pos")
    List<Map<String, Object>> getEmpJobData();

    @MapKey("gender")
    List<Map<String, Object>> getEmpGenderData();

    @Select("select * from emp")
    List<Emp> findAllEmp();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    LoginInfo loginByUsernameAndPassword(Emp emp);

}
