package in.bushansirgur.restapi.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    @NotBlank(message = "Expense name is required")
    @Size(min = 3, message = "Expense name should be at least 3 characters")
    private String name;

    private String email;

    private String password;
}
