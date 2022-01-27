package KoelTests;

import Objects.CreatePlaylistRequest;
import Objects.CreatePlaylistResponse;
import Objects.KoelToken;
import bsh.Token;
import com.github.dockerjava.api.command.CreateConfigResponse;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class MainPage {

    private WebDriver driver;
    private String token;
    private Faker faker;
    private String playlistName;
    private int playlistId;

    @BeforeMethod
    public void start(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        token = KoelToken.get();
        String playlistName = faker.funnyName().name();
        CreatePlaylistRequest createPlaylistRequest = new CreatePlaylistRequest(playlistName);

        Response response = given()
                .baseUri("https://bbb.testpro.io/")
                .basePath("api/playlist")
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .header( "Accept", "application/json")
                .body(createPlaylistRequest)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        CreatePlaylistResponse createPlaylistResponse = jsonPath.getObject("$", CreatePlaylistResponse.class);
        playlistId = createPlaylistResponse.getId();

    }

}
