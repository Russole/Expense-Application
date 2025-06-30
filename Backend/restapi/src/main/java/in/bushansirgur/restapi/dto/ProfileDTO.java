package in.bushansirgur.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data // 生成 Getter、Setter、toString、equals、hashCode
@AllArgsConstructor // 生成所有參數的建構子
@NoArgsConstructor // 生成無參數建構子
@Builder // 提供建造者模式
public class ProfileDTO {
    private String profileId;

    private String email;

    private String name;

    private String password;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
