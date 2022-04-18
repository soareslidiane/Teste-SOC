package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Credenciado {

    private WebDriver driver;

    @Dado("o acesso guiado para a conexao")
    public void oAcessoGuiadoParaAConexao() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(90000, TimeUnit.MILLISECONDS);
    }

    @E("acesso a rede credenciada")
    public void acessoARedeCredenciada() {
        driver.get("https://www.soc.com.br/blog-de-sst/");
        driver.findElement(By.xpath("//a[contains(text(),'Funcionalidades')]")).click();
        driver.findElement(By.linkText("Rede SOCNET")).click();
        driver.findElement(By.cssSelector(".elementor-animation-grow .elementor-button-text")).click();
    }

    @Quando("submeto um cep e um filtro")
    public void submetoUmCepEUmFiltro() {
        driver.findElement(By.id("ipt-busca-credenciado-2")).sendKeys("11533520");
        driver.findElement(By.cssSelector("#div-filtro-conveniencias .elemento-filtro:nth-child(2) .cbx-estilizado")).click();
        driver.findElement(By.id("botao-buscar")).click();
        {
            WebElement element = driver.findElement(By.id("botao-buscar"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector("div:nth-child(1) > .estrutura-resultados-mapa .truncate")).click();
    }

    @Entao("deve ser redirecionado para a tela com o credenciado")
    public void deveSerRedirecionadoParaATelaComOCredenciado() {
        driver.findElement(By.cssSelector(".btn-alinhado")).click();
        driver.findElement(By.cssSelector(".nome-empresa:nth-child(1) > .truncate")).click();
        driver.close();
    }
}
