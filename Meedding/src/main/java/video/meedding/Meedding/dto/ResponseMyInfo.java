package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.Member;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMyInfo {
    private String email;
    private String image;
    private String name;
    private String nickName;
    private LocalDate date;
    public static ResponseMyInfo convertToMyInfo(Member member) {
        ResponseMyInfo responseMyInfo=new ResponseMyInfo();
        responseMyInfo.email= member.getLoginid();
        responseMyInfo.image= member.getImage();
        responseMyInfo.name=member.getName();
        responseMyInfo.nickName=member.getNickname();
        responseMyInfo.date=member.getSignUpDate();
        return responseMyInfo;
    }
}
