package ezenweb.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@EntityListeners( AuditingEntityListener.class ) // Auditing 감시
@Getter
public class BaseTime {
    // 1. 레코드/엔티티 등록날짜
    @CreatedDate // default now()
    public LocalDateTime cdate;
    // 2. 레코드/엔티티 수정날짜
    @LastModifiedDate // 마지막 수정 날짜
    public LocalDateTime udate;
}
/*
    상속 : 여러곳에서 공통적인 멤버들

 */