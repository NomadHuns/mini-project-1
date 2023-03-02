package shop.mtcoding.jobara.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    HttpSession session;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        List<BoardMainRespDto> boardListPS = boardService.getListToMain();
        model.addAttribute("boardMainList", boardListPS);

        return "board/home";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        BoardDetailRespDto boardPS = boardService.getDetail(id);
        model.addAttribute("board", boardPS);
        return "board/detail";
    }

    @GetMapping("/board/list")
    public String list(Model model) {
        List<BoardListRespDto> boardListPS = boardService.getList();
        model.addAttribute("boardList", boardListPS);

        return "board/list";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(principal, "로그인이 필요한 페이지입니다.", HttpStatus.BAD_REQUEST,
                "/company/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        return "board/saveForm";
    }

    @GetMapping("/board/updateForm/{id}")
    public String updateForm(Model model, @PathVariable int id) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/company/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        BoardUpdateRespDto boardDetailPS = boardService.getDetailForUpdate(id, principal.getId());
        model.addAttribute("boardDetail", boardDetailPS);

        return "board/updateForm";
    }

    @PutMapping("/board/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/company/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        // 유효성
        Verify.validateString(boardUpdateReqDto.getTitle(), "제목을 입력하세요");
        Verify.validateString(boardUpdateReqDto.getContent(), "내용을 입력하세요");
        Verify.validateString(boardUpdateReqDto.getCareerString(), "경력을 입력하세요");

        boardService.updateBoard(boardUpdateReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글 수정완료", null), HttpStatus.OK);
    }

    @PostMapping("/board/save")
    public String save(BoardInsertReqDto boardInsertReqDto, @RequestParam ArrayList<Integer> checkLang) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/company/loginForm");
        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        // 유효성
        Verify.validateString(boardInsertReqDto.getTitle(), "제목을 입력하세요");
        Verify.validateString(boardInsertReqDto.getContent(), "내용을 입력하세요");

        if (boardInsertReqDto.getCareerString().equals("경력선택")) {
            throw new CustomException("경력을 선택하세요");
        }
        if (boardInsertReqDto.getEducationString().equals("학력선택")) {
            throw new CustomException("학력을 선택하세요");
        }
        if (boardInsertReqDto.getJobTypeString().equals("근무형태")) {
            throw new CustomException("근무형태를 선택하세요");
        }

        int boardId = boardService.insertBoard(boardInsertReqDto, principal.getId());
        boardService.insertSkill(checkLang, boardId);

        return "redirect:/board/boardList/" + principal.getId();
    }

    @GetMapping("/board/boardList/{id}")
    public String myBoardList(@PathVariable int id, Model model) {

        UserVo principal = (UserVo) session.getAttribute("principal");

        // 인증체크
        Verify.validateObject(
                principal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST,
                "/company/loginForm");

        if (!principal.getRole().equals("company")) {
            throw new CustomException("기업회원으로 로그인 해주세요.");
        }

        List<MyBoardListRespDto> myBoardListPS = boardService.getMyBoard(principal.getId(), id);
        model.addAttribute("myBoardList", myBoardListPS);

        return "board/myBoardList";
    }

}
