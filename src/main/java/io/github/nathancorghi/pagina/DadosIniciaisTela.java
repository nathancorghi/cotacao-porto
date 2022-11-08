package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import java.time.Duration;
import com.codeborne.selenide.Condition;
import io.github.nathancorghi.constantes.Campo;

public class DadosIniciaisTela extends PaginaBase {

    public DadosIniciaisTela preencherCampo(Campo campo, String valor) {

        escreverTexto($(byXpath("//*[@data-gtm-name='"+campo+"']")), valor);
        return this;
    }

    public DadosIniciaisTela tirarFocoCampo(Campo campo) {

        retirarFocoElemento($(byXpath("//*[@data-gtm-name='"+campo+"']")));
        return this;
    }

    public boolean retornaVisibilidadeMensagemValidacao(String mensagem) {

        return retornaVisibilidadeElemento($(byXpath("//*[contains(text(), '"+mensagem+"')]")));
    }

    public DadosIniciaisTela aguardarBuscaDadosValidos(Campo campo) {

        switch (campo) {
            case CEP:
                $(byXpath(
                        "//*[@class='mat-form-field-suffix ng-tns-c157-0 ng-star-inserted']"))
                        .shouldNotBe(Condition.visible, Duration.ofSeconds(20));
                break;
            case CELULAR:
                $(byXpath(
                        "//*[@class='mat-form-field-suffix ng-tns-c157-3 ng-star-inserted']"))
                        .shouldNotBe(Condition.visible, Duration.ofSeconds(20));
                break;
            case EMAIL:
                $(byXpath(
                        "//*[@class='mat-form-field-suffix ng-tns-c157-4 ng-star-inserted']"))
                        .shouldNotBe(Condition.visible, Duration.ofSeconds(20));
                break;
            case APARELHO:
                $(byXpath(
                        "//*[@class='fa suffix-autocomplete-desk fa-search ng-star-inserted']"))
                        .shouldBe(Condition.visible, Duration.ofSeconds(20));
                break;
        }
        return this;
    }

    public DadosIniciaisTela aguardarMensagemDadoInvalido(String mensagem) throws Exception {

        try {
            $(byXpath(
                    "//*[contains(text(), '"+mensagem+"')]"))
                    .shouldBe(Condition.visible, Duration.ofSeconds(20));
        } catch (Exception e) {
            throw new Exception("Não foi possível validar a presença da mensagem 'CEP inválido'");
        }
        return this;
    }

    public DadosIniciaisTela tocarBotaoProximoPasso() {

        tocarElemento($(byXpath("//*[@data-gtm-name='proximo-passo']")));
        return this;
    }
}
