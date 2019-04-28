package mk.trkalo.dnp.dnpshop.repository.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class PhoneNumbersConverter implements AttributeConverter<TreeSet<String>, String> {

    @Override
    public String convertToDatabaseColumn(TreeSet<String> attribute) {
        return String.join(",", attribute);
    }

    @Override
    public TreeSet<String> convertToEntityAttribute(String dbData) {
        return Stream.of(dbData.split(",")).collect(Collectors.toCollection(TreeSet::new));
    }
}
