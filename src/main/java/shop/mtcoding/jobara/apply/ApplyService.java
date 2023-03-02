package shop.mtcoding.jobara.apply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.apply.dto.ApplyReq.ApplyDecideReqDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.CompanyApplyRespDto;
import shop.mtcoding.jobara.apply.dto.ApplyResp.EmployeeApplyRespDto;
import shop.mtcoding.jobara.apply.model.Apply;
import shop.mtcoding.jobara.apply.model.ApplyRepository;
import shop.mtcoding.jobara.board.model.Board;
import shop.mtcoding.jobara.board.model.BoardRepository;
import shop.mtcoding.jobara.common.ex.CustomApiException;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.model.User;
import shop.mtcoding.jobara.user.model.UserRepository;

@Service
public class ApplyService {
    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insertApply(Integer boardId, Integer principalId) {
        Apply apply = new Apply(boardId, principalId);
        Board boardPS = boardRepository.findById(boardId);
        Verify.validateApiObject(boardPS, "존재하지 않는 게시물 입니다.");
        if (applyRepository.findByUserIdAndBoardId(apply) != null) {
            throw new CustomApiException("이미 지원한 공고입니다.");
        }
        try {
            applyRepository.insert(apply);
        } catch (Exception e) {
            throw new CustomApiException("서버 에러 : 지원 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public List<CompanyApplyRespDto> getApplyForCompany(Integer principalId) {
        return applyRepository.findByUserIdWithBoardAndUser(principalId);
    }

    @Transactional(readOnly = true)
    public List<EmployeeApplyRespDto> getApplyForEmployee(Integer principalId) {
        return applyRepository.findByUserIdWithBoardAndResume(principalId);
    }

    @Transactional
    public void approveApply(ApplyDecideReqDto applyDecideReqDto, int boradId) {
        User user = userRepository.findById(applyDecideReqDto.getUserId());
        Verify.validateApiObject(user, "존재하지 않는 유저입니다.");
        Apply apply = new Apply(boradId, applyDecideReqDto.getUserId(), applyDecideReqDto.getState());
        if (applyRepository.findByUserIdAndBoardId(apply) == null) {
            throw new CustomApiException("존재하지 않는 지원입니다.");
        }
        applyRepository.updateById(apply);
    }

}
