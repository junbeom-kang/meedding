package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String writer;
    private String receiver;
    private String title;
    private String contents;

    public static MessageResponseDto covertMessageDto(Message m) {
        return new MessageResponseDto(m.getId(), m.getSentMember().getName(), m.getReceivedMember().getName(), m.getTitle(), m.getContents());
    }

}
