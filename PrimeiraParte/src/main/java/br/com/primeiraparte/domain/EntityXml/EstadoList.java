package br.com.primeiraparte.domain.EntityXml;

import br.com.primeiraparte.domain.entity.Estado;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "Estados")
@Data
public class EstadoList {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("estado")
    @NonNull
    private List<Estado> estados;

    /*public EstadoList(List<Estado> estados) {
        this.estados = estados;
    }

    public EstadoList() {}


    public List<Estado> getEstados() { return estados; }
    public void setEstados(List<Estado> estados) { this.estados = estados; }*/
}

