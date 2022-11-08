package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.SelenideElement;

public class PaginaBase {

    protected void escreverTexto(SelenideElement elemento, String texto) {

        elemento.sendKeys(texto);
    }

    protected void retirarFocoElemento(SelenideElement elemento) {

        elemento.sendKeys(Keys.TAB);
    }

    protected void tocarElemento(SelenideElement elemento) {

        elemento.click();
    }

    protected boolean retornaVisibilidadeElemento(SelenideElement elemento) {

        return elemento.isDisplayed();
    }

    public PaginaBase aceitarCookies() {

        SelenideElement botaoAceitarCookies = $(byId("onetrust-accept-btn-handler"));
        if (botaoAceitarCookies.isDisplayed()) {
            tocarElemento(botaoAceitarCookies);
        }
        return this;
    }
}
