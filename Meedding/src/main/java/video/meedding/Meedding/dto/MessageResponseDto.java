package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.Message;

@Data
@Builder
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String writer;
    private String receiver;
    private String title;
    private String contents;

    public static MessageResponseDto covertMessageDto(Message m) {
        return MessageResponseDto.builder()
                .id(m.getId())
                .writer(m.getSentMember().getName())
                .receiver(m.getReceivedMember().getName())
                .title(m.getTitle())
                .contents(m.getContents())
                .build();
    }

}
