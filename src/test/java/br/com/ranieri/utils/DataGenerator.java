package br.com.ranieri.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private Faker faker = new Faker(new Locale("pt-BR"));

    public String gerarNome(){
        return faker.name().firstName();
    }

    public String gerarEmail(){
        return faker.internet().emailAddress();
    }

    public String gerarSenha(){
        return faker.number().digits(6);
    }
    //para gerar um id aleatorio de 16 caracteres eu faço
    public String gerarIdAleatorio(){
        return faker.random().hex(16);
    }
}
