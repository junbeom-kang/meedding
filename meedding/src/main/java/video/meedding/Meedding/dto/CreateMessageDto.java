package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMessageDto {
    private Long sentMemberId;
    private Long receivedMemberId;
    private String title;
    private String contents;
}
