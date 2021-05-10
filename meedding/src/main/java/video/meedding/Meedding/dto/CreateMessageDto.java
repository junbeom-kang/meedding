package video.meedding.Meedding.dto;

import lombok.Data;

@Data
public class CreateMessageDto {
    private Long sentMemberId;
    private Long receivedMemberId;
    private String title;
    private String contents;
}
