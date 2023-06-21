package jejemint.akkijang.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryPersistConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(final Category attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Category가 존재하지 않습니다.");
        }
        return attribute.getCode();
    }

    @Override
    public Category convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            throw new IllegalArgumentException("dbData가 존재하지 않습니다.");
        }
        return Category.fromCode(dbData);
    }
}
