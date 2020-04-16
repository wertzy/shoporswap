import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import util.JsonUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilTest {

    @Test
    public void userToJsonStringTest() throws JsonProcessingException {
        User testUser1, testUser2;
        String expectedString1, expectedString2;

        testUser1 = new User("test1", "pass1");
        expectedString1 = "{\r\n" +
                "  \"accountName\" : \"test1\",\r\n" +
                "  \"password\" : \"pass1\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        assertEquals(expectedString1, JsonUtil.toJsonString(testUser1));

        testUser2 = new User();
        expectedString2 = "{\r\n" +
                "  \"accountName\" : \"accountname\",\r\n" +
                "  \"password\" : \"password\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        assertEquals(expectedString2, JsonUtil.toJsonString(testUser2));

    }

    @Test
    public void userJsonFileTest() throws IOException {
        User testExportUser1, testExportUser2, testImportUser1, testImportUser2;
        String expectedString1, expectedString2, testFile1, testFile2;

        testExportUser1 = new User("test1", "pass1");
        expectedString1 = "{\r\n" +
                "  \"accountName\" : \"test1\",\r\n" +
                "  \"password\" : \"pass1\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        testFile1 = "src/test/resources/userJsonFileTest1.json";
        JsonUtil.toJsonFile(testFile1, testExportUser1);
        testImportUser1 = JsonUtil.fromJsonFile(testFile1, User.class);
        assertEquals(expectedString1, JsonUtil.toJsonString(testExportUser1));

        testExportUser2 = new User();
        expectedString2 = "{\r\n" +
                "  \"accountName\" : \"accountname\",\r\n" +
                "  \"password\" : \"password\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        testFile2 = "src/test/resources/userJsonFileTest2.json";
        JsonUtil.toJsonFile(testFile2, testExportUser2);
        testImportUser2 = JsonUtil.fromJsonFile(testFile2, User.class);
        assertEquals(expectedString2, JsonUtil.toJsonString(testExportUser2));
    }

    @Test
    public void userListFromJsonFileTest() throws IOException {
        List<User> testExportUserList1, testExportUserList2, testExportUserList3, testImportUserList1, testImportUserList2, testImportUserList3;
        String testFile1, testFile2, testFile3;

        testExportUserList1 = Arrays.asList(
                new User(),
                new User("test1", "test1")
        );
        testFile1 = "src/test/resources/userListFromJsonFileTest1.json";
        JsonUtil.toJsonFile(testFile1, testExportUserList1);
        testImportUserList1 = JsonUtil.listFromJsonFile(testFile1, User.class);
        assertEquals(2, testImportUserList1.size());

        testExportUserList2 = Arrays.asList(
                new User(),
                new User("test1", "test1"),
                new User("test2", "test2"),
                new User("test3", "test3"),
                new User("test4", "test4"),
                new User("test5", "test5")
        );
        testFile2 = "src/test/resources/userListFromJsonFileTest2.json";
        JsonUtil.toJsonFile(testFile2, testExportUserList2);
        testImportUserList2 = JsonUtil.listFromJsonFile(testFile2, User.class);
        assertEquals(6, testImportUserList2.size());

        testExportUserList3 = Arrays.asList();
        testFile3 = "src/test/resources/userListFromJsonFileTest3.json";
        JsonUtil.toJsonFile(testFile3, testExportUserList3);
        testImportUserList3 = JsonUtil.listFromJsonFile(testFile3, User.class);
        assertEquals(0, testImportUserList3.size());

    }

    @Test
    public void productToJsonStringTest() throws JsonProcessingException {
        Product testProduct1, testProduct2, testProduct3;
        String testExpectedString1, testExpectedString2, testExpectedString3;

        testProduct1 = new Product();
        System.out.println(JsonUtil.toJsonString(testProduct1));

        testProduct2 = new Product("name1", "description1", new User("test1", "pass1"));

        testProduct3 = new Product("name1", "description1", 50, new User("test1", "pass1"));
    }

    @Test
    public void productJsonFileTest(){

    }

    @Test
    public void productListFromJsonFileTest(){

    }

}
