package shop.mtcoding.jobara.board.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertSkillReqDto;

@Mapper
public interface BoardTechRepository {
    public List<BoardTech> findAll();

    public BoardTech findById(int id);

    public ArrayList<Integer> findByIdWithSkillForDetail(int boardId);

    public int insert(BoardTech boardTech);

    public int insertSkill(BoardInsertSkillReqDto boardInsertSkillReqDto);

    public int updateById(BoardTech boardTech);

    public int deleteById(int id);
}
