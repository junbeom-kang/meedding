package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @NotBlank

    private String title;
    @NotBlank
    private String password;
}

