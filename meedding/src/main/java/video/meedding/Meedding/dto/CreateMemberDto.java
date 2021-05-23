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
public class CreateMemberDto {
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식으로 장석해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min=4, message="비밀번호는 최소 4자리입니다.")
    private String password;

    @NotBlank(message = "확인 비밀번호를 입력해주세요")
    @Size(min=4, message="비밀번호는 최소 4자리입니다.")
    private String checkPassword;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "닉네임을 정해주세요")
    private String nickname;


}
