package shoporswap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {

    @Test
    void shopOrSwapTest() throws JsonProcessingException {
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        String testJsonString1 =
                "{" + System.lineSeparator() +
                "  \"accountCollection\" : { }," + System.lineSeparator() +
                "  \"systemMessages\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString1, JsonUtil.toJsonString(testShopOrSwap));
    }

    void AccountTest(){
        Account testClient1, testClient2;
        testClient1 = new Client();
        testClient2 = new Client("test1", "pass1");
        Account testAdmin1, testAdmin2;
    }

}
