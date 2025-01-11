package in.bushansirgur.restapi.io;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExpenseRequest {

    @NotNull
    private String name;

    private String note;

    private String category;

    private Date date;

    private BigDecimal amount;
}
