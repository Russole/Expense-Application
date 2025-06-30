package in.bushansirgur.restapi.repository;

import in.bushansirgur.restapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// ProfileEntity：要操作的資料表對應的 Entity 類別。
// Long：主鍵（Primary Key）的型別。
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    // 內建的數據存取方法，Spring Data JPA 會自動解析 findByEmail，根據 email 欄位產生 SQL 查詢
    Optional<ProfileEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
