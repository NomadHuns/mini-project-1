package shop.mtcoding.jobara.employee.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.employee.dto.EmployeeResp.EmployeeAndResumeRespDto;

@Mapper
public interface EmployeeRepository {

    public List<Employee> findAll();

    public List<EmployeeAndResumeRespDto> findAllWithResume();

    public EmployeeAndResumeRespDto findEmployeeByIdWithResume(int id);

    public Employee findByUserId(int userId);

    public int insert(int userId);

    public int updateByUserId(Employee employee);

    public int deleteByUserId(int userId);
}
