package hybernate.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "licences")
@Builder
@ToString
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "license_gen")
    @SequenceGenerator(name = "license_gen",sequenceName = "license_sqe",allocationSize = 1)
    private Long id;
    private String licenseNumber;
    private String issueDate;
    private int expirationDate;

    public License(String licenseNumber, String issueDate, int expirationDate) {
        this.licenseNumber = licenseNumber;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
    }
}
