import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.User;
import com.example.ValidationProcessor;
import com.exceptions.ValidationException;

public class ValidationTest {
    // sad test cases for user validation
    @ParameterizedTest
    @ValueSource(strings = { "  ,  ,-5", "Chinmay,   ,-10", "Chinmay,chinmay@gmail.com,-20" })
    public void testValidationSad(String str) {
        String[] fields = str.split(",");
        User testUser = new User(fields[0], fields[1], Integer.parseInt(fields[2]));
        try {
            ValidationProcessor.validate(testUser);
            fail("Exception Expected");
        } catch (Exception exception) {
            assertEquals(ValidationException.class, exception.getClass());
        }
    }

    // happy test cases for user validation
    @ParameterizedTest
    @ValueSource(strings = { "Chinmay,chinmay@gmail.com,21", "Dummy,dummy@gmail.com,35",
            "Hello,hello.world@gmail.com,85" })
    public void testValidationHappy(String str) {
        String[] fields = str.split(",");
        User testUser = new User(fields[0], fields[1], Integer.parseInt(fields[2]));
        try {
            ValidationProcessor.validate(testUser);
            assertTrue(true);
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }
}
