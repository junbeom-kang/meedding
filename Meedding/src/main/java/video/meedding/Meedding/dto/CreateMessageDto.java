package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageDto {
    private Long receivedMemberId;
    private String title;
    private String contents;
}
