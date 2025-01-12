package in.bushansirgur.restapi.io;

import jakarta.validation.constraints.Email;
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

    @NotBlank(message = "Email is required")
    @Email(message = "Provide valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;
}
