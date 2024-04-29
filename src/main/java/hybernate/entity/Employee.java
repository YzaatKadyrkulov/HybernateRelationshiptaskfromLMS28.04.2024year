package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@Builder
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_gen)")
    @SequenceGenerator(name = "employee_gen",sequenceName = "employee_sqe",allocationSize = 1)
    private Long id;
    private String first_name;
    private String last_name;
    private int age;
    @OneToOne
    private Address address;

    public Employee(String first_name, String last_name, int age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }
}
