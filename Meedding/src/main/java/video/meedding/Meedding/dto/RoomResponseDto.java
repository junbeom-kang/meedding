package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomResponseDto {
    private Long id;
    private String title;
    private String session;
    private int peopleNum;
}
