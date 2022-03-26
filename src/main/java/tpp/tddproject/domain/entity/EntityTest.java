package tpp.tddproject.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class EntityTest {

    @Id
    @GeneratedValue
    private Long id;
}
