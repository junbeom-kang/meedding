package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateRoomDto {
    @NotBlank
    private String title;
}
