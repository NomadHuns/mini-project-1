package shop.mtcoding.jobara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.dto.user.UserReq.UserJoinReqDto;
import shop.mtcoding.jobara.dto.user.UserReq.UserLoginReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.User;
import shop.mtcoding.jobara.model.UserRepository;
import shop.mtcoding.jobara.util.Verify;

@Service
public class UserService {

      @Autowired
      private UserRepository userRepository;

      @Transactional(readOnly = true)
      public User login(UserLoginReqDto userLoginReqDto) {
            User userPS = userRepository.findByUsernameAndPassword(
                        new User(userLoginReqDto.getUsername(), userLoginReqDto.getPassword()));
            Verify.validateObject(userPS, "유저네임이나 암호를 확인해주세요.");
            return userPS;
      }

      @Transactional
      public int join(UserJoinReqDto userJoinReqDto) {
            int result = userRepository.insert(new User(userJoinReqDto.getUsername(),
                        userJoinReqDto.getPassword(),
                        userJoinReqDto.getEmail()));
            if (result != 1) {
                  throw new CustomException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return result;
      }
}
