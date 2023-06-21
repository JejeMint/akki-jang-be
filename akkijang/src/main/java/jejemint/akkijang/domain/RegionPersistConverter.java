package jejemint.akkijang.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RegionPersistConverter implements AttributeConverter<Region, String> {

    @Override
    public String convertToDatabaseColumn(final Region attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Region이 존재하지 않습니다.");
        }
        return attribute.getCode();
    }

    @Override
    public Region convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            throw new IllegalArgumentException("dbData가 존재하지 않습니다.");
        }
        return Region.fromCode(dbData);
    }
}
