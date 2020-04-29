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
                "  \"messages\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        assertEquals(expectedString1, JsonUtil.toJsonString(testUser1));

        testUser2 = new User();
        expectedString2 = "{\r\n" +
                "  \"accountName\" : \"accountname\",\r\n" +
                "  \"password\" : \"password\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"messages\" : [ ],\r\n" +
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
                "  \"messages\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        testFile1 = "src/test/resources/userJsonFileTest1.json";
        JsonUtil.toJsonFile(testFile1, testExportUser1);
        testImportUser1 = JsonUtil.fromJsonFile(testFile1, User.class);
        assertEquals(expectedString1, JsonUtil.toJsonString(testImportUser1));

        testExportUser2 = new User();
        expectedString2 = "{\r\n" +
                "  \"accountName\" : \"accountname\",\r\n" +
                "  \"password\" : \"password\",\r\n" +
                "  \"transactionHistory\" : [ ],\r\n" +
                "  \"messages\" : [ ],\r\n" +
                "  \"rating\" : 0.0\r\n" +
                "}";
        testFile2 = "src/test/resources/userJsonFileTest2.json";
        JsonUtil.toJsonFile(testFile2, testExportUser2);
        testImportUser2 = JsonUtil.fromJsonFile(testFile2, User.class);
        assertEquals(expectedString2, JsonUtil.toJsonString(testImportUser2));
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
        testExpectedString1 = "{\r\n" +
                "  \"name\" : \"DEFAULT NAME\",\r\n" +
                "  \"description\" : \"DEFAULT DESCRIPTION\",\r\n" +
                "  \"price\" : 0.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : null,\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        assertEquals(testExpectedString1, JsonUtil.toJsonString(testProduct1));

        testProduct2 = new Product("name1", "description1", new User("test1", "pass1"));
        testExpectedString2 = "{\r\n" +
                "  \"name\" : \"name1\",\r\n" +
                "  \"description\" : \"description1\",\r\n" +
                "  \"price\" : 0.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : {\r\n" +
                "    \"accountName\" : \"test1\",\r\n" +
                "    \"password\" : \"pass1\",\r\n" +
                "    \"transactionHistory\" : [ ],\r\n" +
                "    \"messages\" : [ ],\r\n" +
                "    \"rating\" : 0.0\r\n" +
                "  },\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        assertEquals(testExpectedString2, JsonUtil.toJsonString(testProduct2));

        testProduct3 = new Product("name1", "description1", 50, new User("test1", "pass1"));
        testExpectedString3 = "{\r\n" +
                "  \"name\" : \"name1\",\r\n" +
                "  \"description\" : \"description1\",\r\n" +
                "  \"price\" : 50.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : {\r\n" +
                "    \"accountName\" : \"test1\",\r\n" +
                "    \"password\" : \"pass1\",\r\n" +
                "    \"transactionHistory\" : [ ],\r\n" +
                "    \"messages\" : [ ],\r\n" +
                "    \"rating\" : 0.0\r\n" +
                "  },\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        assertEquals(testExpectedString3, JsonUtil.toJsonString(testProduct3));
    }

    @Test
    public void productJsonFileTest() throws IOException{
        Product testProduct1, testProduct2, testProduct3;
        String testExpectedString1, testExpectedString2, testExpectedString3, testFile1, testFile2, testFile3;

        testProduct1 = new Product();
        testExpectedString1 = "{\r\n" +
                "  \"name\" : \"DEFAULT NAME\",\r\n" +
                "  \"description\" : \"DEFAULT DESCRIPTION\",\r\n" +
                "  \"price\" : 0.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : null,\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        testFile1 = "src/test/resources/productJsonFileTest1.json";
        JsonUtil.toJsonFile(testFile1, testProduct1);
        assertEquals(testExpectedString1, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFile1, Product.class)));

        testProduct2 = new Product("name1", "description1", new User("test1", "pass1"));
        testExpectedString2 = "{\r\n" +
                "  \"name\" : \"name1\",\r\n" +
                "  \"description\" : \"description1\",\r\n" +
                "  \"price\" : 0.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : {\r\n" +
                "    \"accountName\" : \"test1\",\r\n" +
                "    \"password\" : \"pass1\",\r\n" +
                "    \"transactionHistory\" : [ ],\r\n" +
                "    \"messages\" : [ ],\r\n" +
                "    \"rating\" : 0.0\r\n" +
                "  },\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        testFile2 = "src/test/resources/productJsonFileTest2.json";
        JsonUtil.toJsonFile(testFile2, testProduct2);
        assertEquals(testExpectedString2, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFile2, Product.class)));

        testProduct3 = new Product("name1", "description1", 50, new User("test1", "pass1"));
        testExpectedString3 = "{\r\n" +
                "  \"name\" : \"name1\",\r\n" +
                "  \"description\" : \"description1\",\r\n" +
                "  \"price\" : 50.0,\r\n" +
                "  \"tags\" : [ ],\r\n" +
                "  \"merchant\" : {\r\n" +
                "    \"accountName\" : \"test1\",\r\n" +
                "    \"password\" : \"pass1\",\r\n" +
                "    \"transactionHistory\" : [ ],\r\n" +
                "    \"messages\" : [ ],\r\n" +
                "    \"rating\" : 0.0\r\n" +
                "  },\r\n" +
                "  \"consumers\" : [ ]\r\n" +
                "}";
        testFile3 = "src/test/resources/productJsonFileTest3.json";
        JsonUtil.toJsonFile(testFile3, testProduct3);
        assertEquals(testExpectedString3, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFile3, Product.class)));
    }

    @Test
    public void productListFromJsonFileTest() throws IOException {
        List<Product> testExportProductList1, testExportProductList2, testExportProductList3, testExportProductList4, testExportProductList5, testExportProductList6, testExportProductList7, testExportProductList8;
        String testFile1, testFile2, testFile3, testFile4, testFile5, testFile6, testFile7, testFile8;
        List<Product> testImportProductList1, testImportProductList2, testImportProductList3, testImportProductList4, testImportProductList5, testImportProductList6, testImportProductList7, testImportProductList8;

        testExportProductList1 = Arrays.asList();
        testFile1 = "src/test/resources/productListFromJsonFileTest1.json";
        JsonUtil.toJsonFile(testFile1, testExportProductList1);
        testImportProductList1 = JsonUtil.listFromJsonFile(testFile1, Product.class);
        assertEquals(0, testImportProductList1.size());

        testExportProductList2 = Arrays.asList(
                new Product(),
                new Product()
        );
        testFile2 = "src/test/resources/productListFromJsonFileTest2.json";
        JsonUtil.toJsonFile(testFile2, testExportProductList2);
        testImportProductList2 = JsonUtil.listFromJsonFile(testFile2, Product.class);
        assertEquals(2, testImportProductList2.size());
        assertEquals("DEFAULT NAME", testImportProductList2.get(0).getName());
        assertEquals("DEFAULT DESCRIPTION", testImportProductList2.get(1).getDescription());
        assertEquals(0.0, testImportProductList2.get(0).getPrice());
        assertNull(testImportProductList2.get(1).getMerchant());
        assertEquals(0, testImportProductList2.get(0).getTags().size());
        assertEquals(0, testImportProductList2.get(1).getConsumers().size());

        testExportProductList3 = Arrays.asList(
                new Product(),
                new Product(),
                new Product(),
                new Product(),
                new Product(),
                new Product()
        );
        testFile3 = "src/test/resources/productListFromJsonFileTest3.json";
        JsonUtil.toJsonFile(testFile3, testExportProductList3);
        testImportProductList3 = JsonUtil.listFromJsonFile(testFile3, Product.class);
        assertEquals(6, testImportProductList3.size());
        assertEquals("DEFAULT NAME", testExportProductList3.get(0).getName());
        assertEquals("DEFAULT DESCRIPTION", testExportProductList3.get(1).getDescription());
        assertEquals(0.0, testExportProductList3.get(2).getPrice());
        assertNull(testExportProductList3.get(3).getMerchant());
        assertEquals(0, testExportProductList3.get(4).getTags().size());
        assertEquals(0, testExportProductList3.get(5).getConsumers().size());

        testExportProductList4 = Arrays.asList(
                new Product("name 1", "description 1", new User("test1", "pass1")),
                new Product("name 2", "description 2", new User("test2", "pass2"))
        );
        testFile4 = "src/test/resources/productListFromJsonFileTest4.json";
        JsonUtil.toJsonFile(testFile4, testExportProductList4);
        testImportProductList4 = JsonUtil.listFromJsonFile(testFile4, Product.class);
        assertEquals(2, testImportProductList4.size());
        assertEquals("name 1", testImportProductList4.get(0).getName());
        assertEquals("description 2", testImportProductList4.get(1).getDescription());
        assertEquals(0.0, testImportProductList4.get(0).getPrice());
        assertEquals("test2", testImportProductList4.get(1).getMerchant().getAccountName());
        assertEquals(0, testImportProductList4.get(0).getTags().size());
        assertEquals(0, testImportProductList4.get(1).getConsumers().size());

        testExportProductList5 = Arrays.asList(
                new Product("name 1", "description 1", new User("test1", "pass1")),
                new Product("name 2", "description 2", new User("test2", "pass2")),
                new Product("name 3", "description 3", new User("test3", "pass3")),
                new Product("name 4", "description 4", new User("test4", "pass4")),
                new Product("name 5", "description 5", new User("test5", "pass5")),
                new Product("name 6", "description 6", new User("test6", "pass6"))
        );
        testFile5 = "src/test/resources/productListFromJsonFileTest5.json";
        JsonUtil.toJsonFile(testFile5, testExportProductList5);
        testImportProductList5 = JsonUtil.listFromJsonFile(testFile5, Product.class);
        assertEquals(6, testImportProductList5.size());
        assertEquals("name 1", testImportProductList5.get(0).getName());
        assertEquals("description 2", testImportProductList5.get(1).getDescription());
        assertEquals(0.0, testImportProductList5.get(2).getPrice());
        assertEquals("test4", testImportProductList5.get(3).getMerchant().getAccountName());
        assertEquals(0, testImportProductList5.get(4).getTags().size());
        assertEquals(0, testImportProductList5.get(5).getConsumers().size());

        testExportProductList6 = Arrays.asList(
                new Product("name 1", "description 1", 50.0, new User("test1", "pass1")),
                new Product("name 2", "description 2", 50.0, new User("test2", "pass2"))
        );
        testFile6 = "src/test/resources/productListFromJsonFileTest6.json";
        JsonUtil.toJsonFile(testFile6, testExportProductList6);
        testImportProductList6 = JsonUtil.listFromJsonFile(testFile6, Product.class);
        assertEquals(2, testImportProductList6.size());
        assertEquals(2, testImportProductList6.size());
        assertEquals("name 1", testImportProductList6.get(0).getName());
        assertEquals("description 2", testImportProductList6.get(1).getDescription());
        assertEquals(50.0, testImportProductList6.get(0).getPrice());
        assertEquals("test2", testImportProductList6.get(1).getMerchant().getAccountName());
        assertEquals(0, testImportProductList6.get(0).getTags().size());
        assertEquals(0, testImportProductList6.get(1).getConsumers().size());

        testExportProductList7 = Arrays.asList(
                new Product("name 1", "description 1", 50.0, new User("test1", "pass1")),
                new Product("name 2", "description 2", 50.0, new User("test2", "pass2")),
                new Product("name 3", "description 3", 50.0, new User("test3", "pass3")),
                new Product("name 4", "description 4", 50.0, new User("test4", "pass4")),
                new Product("name 5", "description 5", 50.0, new User("test5", "pass5")),
                new Product("name 6", "description 6", 50.0, new User("test6", "pass6"))
        );
        testFile7 = "src/test/resources/productListFromJsonFileTest7.json";
        JsonUtil.toJsonFile(testFile7, testExportProductList7);
        testImportProductList7 = JsonUtil.listFromJsonFile(testFile7, Product.class);
        assertEquals(6, testImportProductList7.size());
        assertEquals("name 1", testImportProductList7.get(0).getName());
        assertEquals("description 2", testImportProductList7.get(1).getDescription());
        assertEquals(50.0, testImportProductList7.get(2).getPrice());
        assertEquals("test4", testImportProductList7.get(3).getMerchant().getAccountName());
        assertEquals(0, testImportProductList7.get(4).getTags().size());
        assertEquals(0, testImportProductList7.get(5).getConsumers().size());

        testExportProductList8 = Arrays.asList(
                new Product("name 1", "description 1", new User("test1", "pass1")),
                new Product("name 2", "description 2", new User("test2", "pass2")),
                new Product("name 3", "description 3", new User("test3", "pass3")),
                new Product("name 4", "description 4", new User("test4", "pass4")),
                new Product("name 5", "description 5", 50.0, new User("test5", "pass5")),
                new Product("name 6", "description 6", 50.0, new User("test6", "pass6"))
        );
        testFile8 = "src/test/resources/productListFromJsonFileTest8.json";
        JsonUtil.toJsonFile(testFile8, testExportProductList8);
        testImportProductList8 = JsonUtil.listFromJsonFile(testFile8, Product.class);
        assertEquals(6, testImportProductList8.size());
        assertEquals("name 1", testImportProductList8.get(0).getName());
        assertEquals("description 2", testImportProductList8.get(1).getDescription());
        assertEquals(0.0, testImportProductList8.get(2).getPrice());
        assertEquals("test4", testImportProductList8.get(3).getMerchant().getAccountName());
        assertEquals(0, testImportProductList8.get(4).getTags().size());
        assertEquals(0, testImportProductList8.get(5).getConsumers().size());
    }

}
