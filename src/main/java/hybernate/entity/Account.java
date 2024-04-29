package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@Builder
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_sqe", allocationSize = 1)
    private Long id;
    private String accountEmail;

    @OneToOne(mappedBy = "account",cascade = {CascadeType.PERSIST})
    private Customer customer;

    public Account(String accountEmail, Customer customer) {
        this.accountEmail = accountEmail;
        this.customer = customer;
    }
}
