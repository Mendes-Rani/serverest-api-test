package br.com.ranieri.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String email;

    @JsonProperty("password")
    private String senha;

    public LoginRequest(){

    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
