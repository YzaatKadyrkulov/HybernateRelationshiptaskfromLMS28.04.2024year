package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@Builder
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen", sequenceName = "category_sqe", allocationSize = 1)
    private Long id;
    private String categoryName;

    @OneToOne()
    private License license;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
