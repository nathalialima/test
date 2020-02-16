package br.com.concrete.teste.model;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class Phone {

    private String number;
    private String ddd;
}
