package shop.mtcoding.jobara.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertReqDto;
import shop.mtcoding.jobara.board.dto.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardMainRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardUpdateRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyScrapBoardListRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.PagingDto;
import shop.mtcoding.jobara.common.aop.CompanyCheck;
import shop.mtcoding.jobara.common.aop.CompanyCheckApi;
import shop.mtcoding.jobara.common.dto.ResponseDto;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.DateParse;
import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.RedisServiceSet;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.love.LoveService;
import shop.mtcoding.jobara.love.dto.LoveResp.LoveDetailRespDto;
import shop.mtcoding.jobara.resume.model.Resume;
import shop.mtcoding.jobara.user.vo.UserVo;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    LoveService loveService;

    @Autowired
    HttpSession session;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisServiceSet redisServiceSet;

    @GetMapping({ "/", "/home" })
    public String home(Model model, HttpServletRequest request) {

        String username = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember")) {
                    username = cookie.getValue();
                }
            }
        }
        List<BoardMainRespDto> boardListPS = boardService.getListToMain();
        model.addAttribute("boardMainList", boardListPS);
        model.addAttribute("remember", username);
        redisServiceSet.addModel(model);
        return "board/home";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        BoardDetailRespDto boardPS = boardService.getDetail(id);
        UserVo principal = redisService.getValue("principal");
        if (principal != null) {
            List<Resume> resumeList = boardService.getResume(principal.getId());
            model.addAttribute("resumeList", resumeList);

            if (principal.getRole().equals("employee")) {
                LoveDetailRespDto lovePS = loveService.getLove(id, principal);
                model.addAttribute("love", lovePS);
            }
        }

        List<Integer> boardSkill = boardService.getSkillForDetail(id);
        model.addAttribute("boardSkill", boardSkill);
        model.addAttribute("board", boardPS);
        redisServiceSet.addModel(model);
        return "board/detail";
    }

    @GetMapping("/board/list")
    public String list(Model model, Integer page, String keyword) {
        UserVo principal = redisService.getValue("principal");
        PagingDto pagingDto = boardService.getListWithPaging(page, keyword, principal);
        model.addAttribute("pagingDto", pagingDto);
        redisServiceSet.addModel(model);
        return "board/list";
    }

    @GetMapping("/board/saveForm")
    @CompanyCheck
    public String saveForm(Model model) {
        redisServiceSet.addModel(model);
        return "board/saveForm";
    }

    @GetMapping("/board/updateForm/{id}")
    @CompanyCheck
    public String updateForm(Model model, @PathVariable int id) {
        UserVo principal = redisService.getValue("principal");

        List<Integer> boardSkill = boardService.getSkillForDetail(id);

        BoardUpdateRespDto boardDetailPS = boardService.getDetailForUpdate(id, principal.getId());
        model.addAttribute("boardDetail", boardDetailPS);
        model.addAttribute("boardSkill", boardSkill);
        redisServiceSet.addModel(model);

        return "board/updateForm";
    }

    @PutMapping("/board/update/{id}")
    @CompanyCheckApi
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {
        UserVo principal = redisService.getValue("principal");

        // ?????????
        Verify.validateApiString(boardUpdateReqDto.getDeadline(), "?????? ????????? ???????????????");

        ArrayList<Object> resDateParse = DateParse.Dday(boardUpdateReqDto.getDeadline());
        if (!(0 < (Integer) resDateParse.get(0) && (Integer) resDateParse.get(0) < 100)) {
            throw new CustomApiException("1???~100??? ?????? ??????????????? ?????? ????????????. (~" + (String) resDateParse.get(1) + ")");
        }

        if (boardUpdateReqDto.getFavor().length() > 16) {
            throw new CustomApiException("??????????????? 16??? ?????? ?????? ???????????????");
        }

        Verify.isEqualApi(boardUpdateReqDto.getCheckedValues().size(), 0, "??????????????? ??? ?????? ?????? ??????????????????.",
                HttpStatus.BAD_REQUEST);

        Verify.validateApiString(boardUpdateReqDto.getTitle(), "????????? ???????????????");
        Verify.validateApiString(boardUpdateReqDto.getContent(), "????????? ???????????????");
        Verify.validateApiString(boardUpdateReqDto.getCareerString(), "????????? ???????????????");

        boardService.updateBoard(boardUpdateReqDto, principal.getId());
        boardService.updateTech(boardUpdateReqDto.getCheckedValues(), id);

        return new ResponseEntity<>(new ResponseDto<>(1, "????????? ????????????", null), HttpStatus.OK);
    }

    @PostMapping("/board/save")
    @CompanyCheck
    public String save(BoardInsertReqDto boardInsertReqDto,
            @RequestParam(required = false, defaultValue = "") ArrayList<Integer> checkLang) {

        UserVo principal = redisService.getValue("principal");

        // ?????????
        Verify.validateString(boardInsertReqDto.getTitle(), "????????? ???????????????");
        Verify.validateString(boardInsertReqDto.getContent(), "????????? ???????????????");
        if (boardInsertReqDto.getFavor().length() > 16) {
            throw new CustomException("??????????????? 16??? ?????? ?????? ???????????????");
        }

        Verify.isStringEquals(boardInsertReqDto.getCareerString(), "????????????", "????????? ???????????????", HttpStatus.BAD_REQUEST);
        Verify.isStringEquals(boardInsertReqDto.getCareerString(), "????????????", "????????? ???????????????", HttpStatus.BAD_REQUEST);
        Verify.isStringEquals(boardInsertReqDto.getCareerString(), "????????????", "??????????????? ???????????????", HttpStatus.BAD_REQUEST);

        Verify.validateString(boardInsertReqDto.getDeadline(), "?????? ????????? ???????????????");

        ArrayList<Object> resDateParse = DateParse.Dday(boardInsertReqDto.getDeadline());
        if (!(0 < (Integer) resDateParse.get(0) && (Integer) resDateParse.get(0) < 100)) {
            throw new CustomException("1???~100??? ?????? ??????????????? ?????? ????????????. (~" + (String) resDateParse.get(1) + ")");
        }

        Verify.isEqual(checkLang.size(), 0, "??????????????? ??? ?????? ?????? ??????????????????.", HttpStatus.BAD_REQUEST);

        int boardId = boardService.insertBoard(boardInsertReqDto, principal.getId());
        boardService.insertSkill(checkLang, boardId);

        return "redirect:/board/boardList/" + principal.getId();
    }

    @GetMapping("/board/boardList/{id}")
    @CompanyCheck
    public String myBoardList(@PathVariable int id, Model model) {

        UserVo principal = redisService.getValue("principal");

        List<MyBoardListRespDto> myBoardListPS = boardService.getMyBoard(principal.getId(), id);
        model.addAttribute("myBoardList", myBoardListPS);
        redisServiceSet.addModel(model);
        return "board/myBoardList";
    }

    @GetMapping("/board/scrapList/{id}")
    public String myScrapBoardList(@PathVariable int id, Model model) {

        UserVo principal = redisService.getValue("principal");

        // ????????????
        Verify.validateObject(
                principal, "???????????? ????????? ??????????????????", HttpStatus.BAD_REQUEST,
                "/loginForm");

        Verify.checkRole(principal, "employee");

        List<MyScrapBoardListRespDto> myScrapBoardListPS = boardService.getMyScrapBoard(principal.getId(), id);
        model.addAttribute("myScrapBoardList", myScrapBoardListPS);
        redisServiceSet.addModel(model);
        return "board/myScrapBoardList";
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        UserVo principal = redisService.getValue("principal");
        Verify.validateApiObject(
                principal, "???????????? ????????? ??????????????????", HttpStatus.BAD_REQUEST);
        Verify.checkRoleApi(principal, "company");

        boardService.deleteBoard(id, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "???????????? ?????????????????????", null), HttpStatus.OK);
    }

}
