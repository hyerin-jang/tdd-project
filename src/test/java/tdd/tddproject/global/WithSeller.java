package tdd.tddproject.global;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "seller", roles = "SELLER")
public @interface WithSeller {
}
