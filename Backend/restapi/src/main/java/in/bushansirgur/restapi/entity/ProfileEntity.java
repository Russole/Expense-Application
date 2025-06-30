package in.bushansirgur.restapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity // 標註這是一個 JPA 實體類，Hibernate 會將它映射到數據庫中的一個表
@Table(name = "tbl_profile") // 指定這個類對應的資料表名稱為 tbl_profile
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEntity {

    @Id // 標記 id 為主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 表示使用資料庫自增長（Auto Increment）策略，讓資料庫自動產生唯一 ID
    private Long id;

    @Column(unique = true) // 欄位設定為唯一
    private String profileId;

    @Column(unique = true) // 欄位設定為唯一
    private String email;

    private String name;

    private String password;

    @Column(updatable = false)
    @CreationTimestamp // 自動生成時間戳
    private Timestamp createdAt;

    @UpdateTimestamp  // 自動生成時間戳
    private Timestamp updatedAt;
}
