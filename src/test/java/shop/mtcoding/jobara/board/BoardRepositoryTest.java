package shop.mtcoding.jobara.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.board.dto.BoardReq.BoardInsertSkillReqDto;
import shop.mtcoding.jobara.board.dto.BoardResp.BoardDetailRespDto;
import shop.mtcoding.jobara.board.dto.BoardResp.MyBoardListRespDto;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.board.model.BoardTech;
import shop.mtcoding.jobara.board.model.BoardTechRepository;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardTechRepository boardTechRepository;

    @Test
    public void findByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int boardId = 1;

        // when
        BoardDetailRespDto boardDetailRespDto = boardRepository.findByIdWithCompany(boardId);
        // String responseBody = om.writeValueAsString(boardDetailRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(boardDetailRespDto.getCompanyScale()).isEqualTo("대기업");
    }

    @Test
    public void findAllByIdWithCompany_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int userId = 6;

        // when
        List<MyBoardListRespDto> myBoardListRespDto = boardRepository.findAllByIdWithCompany(userId);
        // String responseBody = om.writeValueAsString(myBoardListRespDto);
        // System.out.println("테스트 : " + responseBody);

        // then
        assertThat(myBoardListRespDto.get(1).getTitle()).isEqualTo("공고제목2");
    }

    @Test
    public void insertSkill_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        List<BoardTech> boardTechList1 = boardTechRepository.findAll();
        String res1 = om.writeValueAsString(boardTechList1);
        // System.out.println("테스트1 : " + res1);

        int boardId = 5;
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        BoardInsertSkillReqDto boardInsertSkillReqDto = new BoardInsertSkillReqDto(5, arrayList);

        // when
        boardTechRepository.insertSkill(boardInsertSkillReqDto);
        List<BoardTech> boardTechList2 = boardTechRepository.findAll();
        String res2 = om.writeValueAsString(boardTechList2);
        // System.out.println("테스트2 : " + res2);

        // then

    }

    @Test
    public void findByIdWithSkillForDetail_test() throws Exception {
        // given
        ObjectMapper om = new ObjectMapper();
        int boardId = 1;

        // when
        ArrayList<Integer> arrayList = boardTechRepository.findByIdWithSkillForDetail(boardId);
        String res = om.writeValueAsString(arrayList);
        System.out.println("테스트 : " + res);

        // then

    }
}
