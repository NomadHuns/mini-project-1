package shop.mtcoding.jobara.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.model.Board;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.CareerParse;
import shop.mtcoding.jobara.tech.dto.TechReq.BoardTechReqDto;
import shop.mtcoding.jobara.tech.model.Tech;
import shop.mtcoding.jobara.tech.model.TechRepository;
import shop.mtcoding.jobara.user.model.User;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TechRepository techRepository;

    @Transactional(readOnly = true)
    public List<MyBoardListRespDto> getMyBoard(int coPrincipalId, int companyId) {
        // 권한 체크
        if (coPrincipalId != companyId) {
            throw new CustomException("공고 리스트 열람 권한이 없습니다.");
        }

        List<MyBoardListRespDto> myBoardListPS;
        try {
            myBoardListPS = boardRepository.findAllByIdWithCompany(coPrincipalId);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return myBoardListPS;
    }

    @Transactional
    public int insertBoard(BoardInsertReqDto boardInsertReqDto, int companyId) {

        // career : String -> int parsing
        int career = CareerParse.careerToInt(boardInsertReqDto.getCareer());
        Board board = new Board(companyId, boardInsertReqDto.getTitle(), boardInsertReqDto.getContent(),
                career);

        try {
            boardRepository.insert(board);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return board.getId();
    }

    @Transactional
    public void updateBoard(BoardUpdateReqDto boardUpdateReqDto, int coPrincipalId) {

        Board boardPS;

        try {
            boardPS = boardRepository.findById(boardUpdateReqDto.getId());
        } catch (Exception e) {
            throw new CustomException("없는 게시물을 수정할 수 없습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (boardPS.getCompanyId() != coPrincipalId) {
            throw new CustomException("수정 권한이 없습니다", HttpStatus.BAD_REQUEST);
        }

        // career : String -> int parsing
        int career = CareerParse.careerToInt(boardUpdateReqDto.getCareerString());
        Board board = new Board(boardUpdateReqDto.getTitle(), boardUpdateReqDto.getContent(),
                career, boardUpdateReqDto.getId());

        try {
            boardRepository.updateById(board);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional(readOnly = true)
    public List<BoardMainRespDto> getListToMain() {

        List<BoardMainRespDto> boardListPS;

        try {
            boardListPS = boardRepository.findAllWithCompanyToMain();
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return boardListPS;
    }

    @Transactional(readOnly = true)
    public BoardUpdateRespDto getDetailForUpdate(int id, int coPrincipalId) {
        BoardUpdateRespDto boardDetailPS;

        try {
            boardDetailPS = boardRepository.findByIdForUpdate(id);
        } catch (Exception e) {
            throw new CustomException("없는 게시물을 수정할 수 없습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (boardDetailPS.getCompanyId() != coPrincipalId) {
            throw new CustomException("수정 권한이 없습니다", HttpStatus.BAD_REQUEST);
        }

        String career = CareerParse.careerToString(boardDetailPS.getCareer());
        boardDetailPS.setCareerString(career);

        return boardDetailPS;
    }

    @Transactional(readOnly = true)
    public Tech getTechDetailForUpdate(int id) {

        Tech techDetailPS = techRepository.findByboardId(id);

        return techDetailPS;
    }

    @Transactional(readOnly = true)
    public BoardDetailRespDto getDetail(int id) {
        BoardDetailRespDto boardDetailPS;

        try {
            boardDetailPS = boardRepository.findByIdWithCompany(id);
        } catch (Exception e) {
            throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String career = CareerParse.careerToString(boardDetailPS.getCareer());
        boardDetailPS.setCareerString(career);

        return boardDetailPS;
    }

    @Transactional(readOnly = true)
    public List<BoardListRespDto> getList(String keyword, int userId) {
        List<BoardListRespDto> boardListPS;
        List<BoardListRespDto> boardPreferedListPS;
        Tech tech = techRepository.findByuserId(userId);

        if (keyword == null || tech == null) {
            try {
                boardListPS = boardRepository.findAllWithCompany();
                return boardListPS;
            } catch (Exception e) {
                throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            try {
                boardPreferedListPS = boardRepository.findAllForPreferedTech(tech);
                return boardPreferedListPS;
            } catch (Exception e) {
                throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }

    public void getMyBoard(Integer id) {
    }

    public void updateTech(BoardTechReqDto boardTechReqDto, int boardId) {
        Tech techPS = techRepository.findByboardId(boardId);

        techPS.updateBoard(boardTechReqDto);
        techRepository.updateById(techPS);
    }

    public void insertTech(BoardTechReqDto boardTechReqDto, int boardId) {
        Tech tech = new Tech(boardId, boardTechReqDto);

        techRepository.insert(tech);
    }

}
