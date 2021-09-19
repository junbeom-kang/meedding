package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import video.meedding.Meedding.domain.Member;

@Data
@AllArgsConstructor
public class ResponseMemberDto {
    private Long id;
    private String login_id;
    private String name;
    public static ResponseMemberDto convertUserToDto(Member member) {
        return new ResponseMemberDto(member.getId(),member.getLoginid(), member.getName());
    }
}
