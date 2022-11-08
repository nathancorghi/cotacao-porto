package io.github.nathancorghi.constantes;

public enum Campo {

    CEP("cep"),
    CPF("cpf"),
    NOME("nome"),
    CELULAR("celular"),
    EMAIL("email"),
    APARELHO("digite-a-marca-e-o-modelo-do-seu-celular");

    private final String value;

    Campo(String value) {
        this.value = value;
    }

    @Override
    public String toString() { return value; }
}
