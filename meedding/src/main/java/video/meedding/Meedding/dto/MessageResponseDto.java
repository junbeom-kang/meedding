package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String title;
    private String contents;
}
