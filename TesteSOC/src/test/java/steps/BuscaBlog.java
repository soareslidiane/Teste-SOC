package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BuscaBlog {

    private WebDriver driver;

    @Dado("o acesso guiado pela conexao")
    public void oAcessoGuiadoPelaConexao() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(90000, TimeUnit.MILLISECONDS);
    }

    @Quando("submeto uma pesquisa")
    public void submetoUmaPesquisa() {
        driver.get("https://www.soc.com.br/blog-de-sst/");
        driver.findElement(By.cssSelector(".elementor-search-form__container:nth-child(1) > .elementor-search-form__input")).click();
        driver.findElement(By.cssSelector(".elementor-search-form__container:nth-child(1) > .elementor-search-form__input")).sendKeys("SOC");
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".elementor-search-form__submit > .fas"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector(".elementor-search-form__submit > .fas")).click();
    }

    @Entao("deve ser redirecionado para a tela")
    public void deveSerRedirecionadoParaATela() {
        driver.findElement(By.xpath("//a[contains(text(),'Software de SST: entenda a importância para o eSOCial')]")).click();

    }
}
