import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}
