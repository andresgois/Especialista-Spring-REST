package br.com.primeiraparte.helpers.converters;

import br.com.primeiraparte.domain.enuns.Ativado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AtivadoConverter implements AttributeConverter<Ativado, String> {

    @Override
    public String convertToDatabaseColumn(Ativado ativado) {
        if (ativado == null) {
            return null;
        }
        return ativado == Ativado.SIM ? "T" : "F";
    }

    @Override
    public Ativado convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.equalsIgnoreCase("T") ? Ativado.SIM : Ativado.NAO;
    }
}

