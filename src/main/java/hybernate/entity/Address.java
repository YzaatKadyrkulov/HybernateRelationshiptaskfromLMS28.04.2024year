package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Builder
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    @SequenceGenerator(name = "address_gen", sequenceName = "address_sqe", allocationSize = 1)
    private Long id;
    private String employeeAddress;

    @OneToOne(mappedBy = "address",cascade = {CascadeType.PERSIST})
    private Employee employee;

    public Address(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
}
