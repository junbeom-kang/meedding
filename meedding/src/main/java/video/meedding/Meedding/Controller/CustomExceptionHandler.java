package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.exception.*;
import video.meedding.Meedding.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return responseService.getFailResult(-1000, "오류가 발생하였습니다.");
    }

    @ExceptionHandler(ExistedNickNameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result existedNickNameException() {
        return responseService.getFailResult(-1001, "이미 존재하는 닉네임입니다.");
    }

    @ExceptionHandler(ExistedEmailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result existedEmailException() {
        return responseService.getFailResult(-1002, "이미 존재하는 아이디입니다.");
    }

    @ExceptionHandler(PasswordDiffException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result passwordDiffException() {
        return responseService.getFailResult(-1003, "확인 비밀번호가 다릅니다.");
    }

    @ExceptionHandler(SamePasswordException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result samePasswordException() {
        return responseService.getFailResult(-1004, "이전 비밀번호와 동일합니다.");
    }

    @ExceptionHandler(NoMemberException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result noMemberException() {
        return responseService.getFailResult(-1005, "없는 회원입니다.");
    }
    @ExceptionHandler(NoMatchingRoomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result noMatchingRoomException() {
        return responseService.getFailResult(-1006, "해당하는 회의방이 없습니다");
    }

    @ExceptionHandler(CantMakeFriendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result cantMakeFriendException() {
        return responseService.getFailResult(-1007, "친구를 추가할 수 없습니다.");
    }

    @ExceptionHandler(DuplicateFriendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result duplicateFriendException() {
        return responseService.getFailResult(-1008, "이미 존재하는 친구입니다.");
    }
    @ExceptionHandler(NoFriendRelationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result noFriendRelationException() {
        return responseService.getFailResult(-1009, "친구 사이가 아닙니다.");
    }


    @ExceptionHandler(NoMessageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultException() {
        return responseService.getFailResult(-1010, "없는 메시지입니다.");
    }


}