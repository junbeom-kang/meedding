package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "닉네임을 정해주세요")
    private String nickname;


}
