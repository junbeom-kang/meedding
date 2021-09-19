package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.Friend;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendResponseDto {
    private Long relation_id;
    private Long member_id;
    private String email;
    private String name;
    public static FriendResponseDto ConvertToDto(Friend friend) {
        return new FriendResponseDto(friend.getId(),
                friend.getTargetMember().getId(),
                friend.getTargetMember().getLoginid(),
                friend.getTargetMember().getName());
    }
}
