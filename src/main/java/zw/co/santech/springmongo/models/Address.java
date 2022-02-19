package zw.co.santech.springmongo.models;

import lombok.Data;

@Data
public class Address {
    private String country;
    private Long postCode;
    private String city;
}
