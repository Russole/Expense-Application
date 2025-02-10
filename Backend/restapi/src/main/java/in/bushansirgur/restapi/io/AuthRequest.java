package in.bushansirgur.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自動生成 getter、setter
@AllArgsConstructor // 自動生成一個包含所有屬性的建構函數。
@NoArgsConstructor
@Builder // 啟用 Builder 模式，允許以流式 API 的方式創建對象
public class AuthRequest {
    private String email;

    private String password;
}
