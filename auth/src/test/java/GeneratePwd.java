import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 生成BCrypt密码
 * 2024/5/21
 *
 * @author Marcel.Maxs
 */
@SpringBootTest
public class GeneratePwd {
    @Test
    public void generatePwd() {
        // 生成client_secret、用户的密码时，都是用这个方法即可
        String plainTextPwd = "system123456";
        String encodeResult = new BCryptPasswordEncoder().encode(plainTextPwd);
        System.out.println(encodeResult);
    }
}
