package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.config.auth.PrincipalDetails;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.dto.CreateFriendDto;
import video.meedding.Meedding.dto.FriendResponseDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.FriendService;
import video.meedding.Meedding.service.ResponseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FriendController {
    private final FriendService friendService;
    private final ResponseService responseService;

    @PostMapping("/friends")
    public Result makeFriend(@RequestBody CreateFriendDto createFriendDto, @AuthenticationPrincipal PrincipalDetails principal) {
        friendService.makeFriend(principal.getMember().getId(), createFriendDto.getId());
        return responseService.getSuccessResult();
    }


    @GetMapping("friends")
    public Result friendList(@AuthenticationPrincipal PrincipalDetails principal) {
        List<Friend> friends = friendService.addFriendList(principal.getMember().getId());
        List<FriendResponseDto> friend=friends.stream()
                .map(m->new FriendResponseDto(m.getTargetMember().getLoginid(),m.getTargetMember().getName()))
                .collect(Collectors.toList());
        return responseService.getListResult(friend);
    }



}
