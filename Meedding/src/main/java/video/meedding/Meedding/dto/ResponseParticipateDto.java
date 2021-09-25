package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.RoomParticipate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseParticipateDto {
    private Long member_id;
    private String member_LoginId;
    private String member_nickName;

    public static ResponseParticipateDto convertToDto(RoomParticipate roomParticipate) {
        ResponseParticipateDto responseParticipateDto=new ResponseParticipateDto();
        responseParticipateDto.member_id=roomParticipate.getMember().getId();
        responseParticipateDto.member_LoginId=roomParticipate.getMember().getLoginid();
        responseParticipateDto.member_nickName=roomParticipate.getMember().getNickname();
        return responseParticipateDto;
    }
}
