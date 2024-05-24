package org.micro.documentmanager.Enumeration.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.micro.documentmanager.Enumeration.Authority;

import java.util.stream.Stream;


@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Authority, String> {

    @Override
    public String convertToDatabaseColumn(Authority authority) {
        if (authority == null) {
            return null;
        }
        return authority.value; // get the value of the enum
    }

    @Override
    public Authority convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(Authority.values())
                .filter(authority -> authority.value.equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
