package com.app.addressbook.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(description="All details about the contact")
public class ApiRequest {
    @ApiModelProperty(notes="Name must be alphanumeric and start with character")
    @NotNull(message = "Please provide valid Name")
    @NotEmpty(message = "Please provide valid Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "Validation failed: Name must be alphanumeric and start with character")
    private String name;

    @ApiModelProperty(notes="phone number must be 10 digits, 93412345678 or 11 digits 093412345678 or 12 digits 9193412345678")
    @Pattern(regexp = "^[123456789]\\d{9}$|^0[123456789]\\d{9}$|^91[123456789]\\d{9}",
            message = "Validation failed: phone number must be 10 digits, 93412345678 or 11 digits 093412345678 or 12 digits 9193412345678")
    private String phone;
}
