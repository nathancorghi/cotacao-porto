package io.github.nathancorghi;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static io.github.nathancorghi.constantes.UrlBase.BASE_URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.github.nathancorghi.constantes.Aparelho;
import io.github.nathancorghi.constantes.Campo;
import io.github.nathancorghi.constantes.Celular;
import io.github.nathancorghi.constantes.Cep;
import io.github.nathancorghi.constantes.Email;
import io.github.nathancorghi.constantes.Mensagens;
import io.github.nathancorghi.constantes.Nome;
import io.github.nathancorghi.pagina.DadosIniciaisTela;

public class DadosIniciaisTest {

    DadosIniciaisTela dadosIniciais = new DadosIniciaisTela();

    @BeforeClass
    public static void abrirNavegador() {

        open(BASE_URL);
    }

    @Before
    public void aceitarCookies() {

        dadosIniciais.aceitarCookies();
    }

    @After
    public void atualizarNavegador() {

        refresh();
    }

    @AfterClass
    public static void fecharNavegador() {

        closeWindow();
    }

    @Test
    public void validarObrigatoriedadeDoCampoCep() {

        dadosIniciais.tirarFocoCampo(Campo.CEP);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarQuantidadeMinimaCaracteresCep() {

        dadosIniciais
                .preencherCampo(Campo.CEP, Cep.CEP_QUANTIDADE_MINIMA)
                .tirarFocoCampo(Campo.CEP);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_MINIMO_CARACTER_CEP));
    }

    @Test
    public void validarCepInvalido() throws Exception {

        dadosIniciais
                .preencherCampo(Campo.CEP, Cep.CEP_INVALIDO)
                .aguardarMensagemDadoInvalido(Mensagens.MENSAGEM_CEP_INVALIDO);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CEP_INVALIDO));
    }

    @Test
    public void validarCepValido() {

        dadosIniciais
                .preencherCampo(Campo.CEP, Cep.CEP_VALIDO)
                .aguardarBuscaDadosValidos(Campo.CEP);

        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CEP_INVALIDO));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_MINIMO_CARACTER_CEP));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarObrigatoriedadeDoCampoCpf() {

        dadosIniciais.tirarFocoCampo(Campo.CEP);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarObrigatoriedadeDoCampoNome() {

        dadosIniciais.tirarFocoCampo(Campo.NOME);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarQuantidadeMinimaCaracteresNome() {

        dadosIniciais
                .preencherCampo(Campo.NOME, Nome.QUANTIDADE_MINIMA_NOME)
                .tirarFocoCampo(Campo.NOME);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_MINIMO_CARACTER_NOME));
    }

    @Test
    public void validarNomeIncompleto() {

        dadosIniciais
                .preencherCampo(Campo.NOME, Nome.PRIMEIRO_NOME)
                .tirarFocoCampo(Campo.NOME);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_INFORMAR_SOBRENOME));
    }

    @Test
    public void validarNomeCompleto() {

        dadosIniciais
                .preencherCampo(Campo.NOME, Nome.PRIMEIRO_NOME + " " + Nome.SOBRENOME)
                .tirarFocoCampo(Campo.NOME);

        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_INFORMAR_SOBRENOME));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_MINIMO_CARACTER_NOME));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarObrigatoriedadeDoCampoCelular() {

        dadosIniciais.tirarFocoCampo(Campo.CELULAR);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarCelularInvalido() {

        dadosIniciais
                .preencherCampo(Campo.CELULAR, Celular.CELULAR_INCOMPLETO)
                .tirarFocoCampo(Campo.CELULAR);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CELULAR_INVALIDO));
    }

    @Test
    public void validarTelefoneInvalido() throws Exception {

        dadosIniciais
                .preencherCampo(Campo.CELULAR, Celular.CELULAR_INVALIDO)
                .tirarFocoCampo(Campo.CELULAR)
                .aguardarMensagemDadoInvalido(Mensagens.MENSAGEM_TELEFONE_INVALIDO);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_TELEFONE_INVALIDO));
    }

    @Test
    public void validarCelularValido() {

        dadosIniciais
                .preencherCampo(Campo.CELULAR, Celular.CELULAR_VALIDO)
                .tirarFocoCampo(Campo.CELULAR)
                .aguardarBuscaDadosValidos(Campo.CELULAR);

        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CELULAR_INVALIDO));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_TELEFONE_INVALIDO));
    }

    @Test
    public void validarObrigatoriedadeDoCampoEmail() {

        dadosIniciais.tirarFocoCampo(Campo.EMAIL);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarEmailInvalido() {

        dadosIniciais
                .preencherCampo(Campo.EMAIL, Email.EMAIL_INVALIDO)
                .tirarFocoCampo(Campo.EMAIL);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_EMAIL_INVALIDO));
    }

    @Test
    public void validarEmailValido() {

        dadosIniciais
                .preencherCampo(Campo.EMAIL, Email.EMAIL_VALIDO)
                .tirarFocoCampo(Campo.EMAIL)
                .aguardarBuscaDadosValidos(Campo.EMAIL);

        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_EMAIL_INVALIDO));
    }

    @Test
    public void validarObrigatoriedadeDoCampoAparelho() {

        dadosIniciais.tirarFocoCampo(Campo.APARELHO);

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarAparelhoValido() {

        dadosIniciais
                .preencherCampo(Campo.APARELHO, Aparelho.APARELHO_VALIDO)
                .tirarFocoCampo(Campo.APARELHO)
                .aguardarBuscaDadosValidos(Campo.APARELHO);

        Assert.assertFalse(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }

    @Test
    public void validarObrigatoriedadeTempoUsoAparelho() {

        dadosIniciais
                .preencherCampo(Campo.CEP, Cep.CEP_VALIDO)
                .preencherCampo(Campo.NOME, Nome.PRIMEIRO_NOME + " " + Nome.SOBRENOME)
                .preencherCampo(Campo.CELULAR, Celular.CELULAR_VALIDO)
                .preencherCampo(Campo.EMAIL, Email.EMAIL_VALIDO)
                .preencherCampo(Campo.APARELHO, Aparelho.APARELHO_VALIDO)
                .tocarBotaoProximoPasso();

        Assert.assertTrue(dadosIniciais.retornaVisibilidadeMensagemValidacao(Mensagens.MENSAGEM_CAMPO_OBRIGATORIO));
    }
}
