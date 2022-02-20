package zw.co.santech.springmongo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String country;
    private Long postCode;
    private String city;

}
