package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendResponseDto {
    private Long id;
    private String email;
    private String name;
}
