package br.com.primeiraparte.helpers.annotations;

import br.com.primeiraparte.domain.enuns.NivelUrganecia;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoNoficador {

    NivelUrganecia value();
}
