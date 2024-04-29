package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@Builder
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_sqe",allocationSize = 1)
    private Long id;
    private String first_name;
    private String last_name;
    private int age;
    @OneToOne()
    private Account account;

    public Customer(String first_name, String last_name, int age, Account account) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.account = account;
    }
}
