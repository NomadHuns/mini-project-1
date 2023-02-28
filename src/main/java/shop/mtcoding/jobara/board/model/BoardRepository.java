package shop.mtcoding.jobara.board.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.tech.model.Tech;

@Mapper
public interface BoardRepository {

    public List<Board> findAll();

    public List<BoardMainRespDto> findAllWithCompanyToMain();

    public List<BoardListRespDto> findAllWithCompany();

    public List<BoardListRespDto> findAllForPreferedTech(Tech tech);

    public List<MyBoardListRespDto> findAllByIdWithCompany(int companyId);

    public BoardDetailRespDto findByIdWithCompany(int boardId);

    public Board findById(int id);

    public BoardUpdateRespDto findByIdForUpdate(int id);

    public int insert(Board board);

    public int updateById(Board board);

    public int deleteById(int id);
}
