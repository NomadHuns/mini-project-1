package shop.mtcoding.jobara.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.model.Company;
import shop.mtcoding.jobara.tech.dto.TechReq.BoardTechReqDto;
import shop.mtcoding.jobara.tech.model.Tech;
import shop.mtcoding.jobara.tech.model.TechRepository;
import shop.mtcoding.jobara.user.model.User;

@Controller
public class BoardController {

      @Autowired
      private BoardService boardService;

      @Autowired
      private TechRepository techRepository;

      @Autowired
      private HttpSession session;

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
      public String list(Model model, String keyword) {
            User user = (User) session.getAttribute("usPrincipal");

            List<BoardListRespDto> boardListPS = boardService.getList(keyword, user.getId());
            model.addAttribute("boardList", boardListPS);

            return "board/list";
      }

      @GetMapping("/board/saveForm")
      public String saveForm() {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증체크
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            return "board/saveForm";
      }

      @GetMapping("/board/updateForm/{id}")
      public String updateForm(Model model, @PathVariable int id) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증체크
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            BoardUpdateRespDto boardDetailPS = boardService.getDetailForUpdate(id, coPrincipal.getId());
            Tech techDetailPS = boardService.getTechDetailForUpdate(id);
            model.addAttribute("boardDetail", boardDetailPS);
            model.addAttribute("techDetail", techDetailPS);

            return "board/updateForm";
      }

      @PostMapping("/board/update/{id}")
      public String update(@PathVariable int id, BoardUpdateReqDto boardUpdateReqDto, BoardTechReqDto boardTechReqDto) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");
            // 인증
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            // 유효성
            Verify.validateStiring(boardUpdateReqDto.getTitle(), "제목을 입력하세요");
            Verify.validateStiring(boardUpdateReqDto.getContent(), "내용을 입력하세요");
            Verify.validateStiring(boardUpdateReqDto.getCareerString(), "경력을 입력하세요");

            boardService.updateBoard(boardUpdateReqDto, coPrincipal.getId());

            boardService.updateTech(boardTechReqDto, id);

            return "redirect:/board/" + id;
      }

      @PostMapping("/board/save")
      public String save(BoardInsertReqDto boardInsertReqDto, BoardTechReqDto boardTechReqDto) {
            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            // 유효성
            Verify.validateStiring(boardInsertReqDto.getTitle(), "제목을 입력하세요");
            Verify.validateStiring(boardInsertReqDto.getContent(), "내용을 입력하세요");
            Verify.validateStiring(boardInsertReqDto.getCareer(), "경력을 입력하세요");

            if (boardInsertReqDto.getCareer().equals("경력선택")) {
                  throw new CustomException("경력을 선택하세요");
            }

            int createdBoardId = boardService.insertBoard(boardInsertReqDto, coPrincipal.getId());
            boardService.insertTech(boardTechReqDto, createdBoardId);

            return "redirect:/board/boardList/" + coPrincipal.getId();
      }

      @GetMapping("/board/boardList/{id}")
      public String myBoardList(@PathVariable int id, Model model) {

            Company coPrincipal = (Company) session.getAttribute("coPrincipal");

            // 인증
            Verify.validateObject(coPrincipal, "로그인이 필요한 페이지입니다", HttpStatus.BAD_REQUEST, "/company/loginForm");

            List<MyBoardListRespDto> myBoardListPS = boardService.getMyBoard(coPrincipal.getId(), id);
            model.addAttribute("myBoardList", myBoardListPS);

            return "board/myBoardList";
      }

}
