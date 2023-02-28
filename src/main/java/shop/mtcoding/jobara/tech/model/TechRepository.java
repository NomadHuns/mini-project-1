package shop.mtcoding.jobara.tech.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TechRepository {

      public List<Tech> findAll();

      public Tech findById(int id);

      public Tech findByuserId(int userId);

      public Tech findByboardId(int boardId);

      public int insert(Tech tech);

      public int updateById(Tech tech);

      public int deleteById(int id);
}
