package br.com.primeiraparte.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_estado")
@Entity
@JacksonXmlRootElement(localName = "Estados")
public class Estado {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonIgnore
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE,value = "Nome")
    @Column(nullable = false)
    private String nome;

}
