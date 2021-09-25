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
import java.util.NoSuchElementException;

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

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result AccessDeniedException() {
        return responseService.getFailResult(-1006, "해당 작업에 권한이 없습니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result AuthenticationEntryPointException() {
        return responseService.getFailResult(-1007, "인증정보가 유효하지 않습니다.");
    }

    @ExceptionHandler(CantMakeFriendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result cantMakeFriendException() {
        return responseService.getFailResult(-2000, "친구를 추가할 수 없습니다.");
    }

    @ExceptionHandler(DuplicateFriendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result duplicateFriendException() {
        return responseService.getFailResult(-2001, "이미 존재하는 친구입니다.");
    }
    @ExceptionHandler(NoFriendRelationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result noFriendRelationException() {
        return responseService.getFailResult(-2002, "친구 사이가 아닙니다.");
    }

    @ExceptionHandler(NoMessageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoMessageException() {
        return responseService.getFailResult(-2003, "없는 메시지입니다.");
    }



    @ExceptionHandler(NoMatchingRoomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result noMatchingRoomException() {
        return responseService.getFailResult(-3000, "해당하는 회의방이 없습니다");
    }

    @ExceptionHandler(RoomPasswordDiffException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result RoomPasswordDiffException() {
        return responseService.getFailResult(-3001, "방 비밀번호가 틀렸습니다.");
    }

    @ExceptionHandler(NoRoomCreatorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoRoomCreatorException() {
        return responseService.getFailResult(-3002, "방을 만든사람이 아닙니다");
    }

    @ExceptionHandler(ExistedRoomNameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result ExistedRoomNameException() {
        return responseService.getFailResult(-3003, "이미 존재하는 방제목입니다");
    }

    @ExceptionHandler(NoSuchParticipatorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchParticipatorException() {
        return responseService.getFailResult(-3004, "없는 참가자 입니다");
    }

    @ExceptionHandler(NoRoomSessionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoRoomSessionException() {
        return responseService.getFailResult(-3005, "없는 세션입니다");
    }

    @ExceptionHandler(NoSuchRoomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result NoSuchRoomException() {
        return responseService.getFailResult(-3006, "없는 회의방입니다");
    }


    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result RoomPasswordWrongException() {
        return responseService.getFailResult(-3007, "방 비밀번호가 틀렸습니다.");
    }


}
