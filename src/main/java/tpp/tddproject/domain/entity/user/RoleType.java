package tpp.tddproject.domain.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    ROLE_USER("ROLE_USER", "사용자"),
    ROLE_SELLER("ROLE_SELLER", "판매자"),
    ROLE_ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
