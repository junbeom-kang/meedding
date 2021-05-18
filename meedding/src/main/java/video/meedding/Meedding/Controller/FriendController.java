package video.meedding.Meedding.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import video.meedding.Meedding.domain.Friend;
import video.meedding.Meedding.dto.FriendResponseDto;
import video.meedding.Meedding.dto.response.Result;
import video.meedding.Meedding.service.FriendService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FriendController {
    private final FriendService friendService;
/*

    @PostMapping("/friends")
    public Result makeFriend(@RequestBody Long id) {
        friendService.makeFriend(me.getId(), id);
        return new Result<Integer>(HttpStatus.OK.value());
    }


    @GetMapping("friends")
    public Result friendList() {
        List<Friend> friends = friendService.addFriendList(id);
        List<FriendResponseDto> friend=friends.stream()
                .map(m->new FriendResponseDto(m.getTargetMember().getName()))
                .collect(Collectors.toList());
        return new Result(friend);
    }

 */
}
