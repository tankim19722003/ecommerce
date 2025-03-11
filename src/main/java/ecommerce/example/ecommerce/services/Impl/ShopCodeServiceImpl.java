package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.ShopCodeRepo;
import ecommerce.example.ecommerce.Repo.ShopRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.models.Shop;
import ecommerce.example.ecommerce.models.ShopCode;
import ecommerce.example.ecommerce.models.Status;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.services.ShopCodeService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class ShopCodeServiceImpl implements ShopCodeService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private ShopCodeRepo shopCodeRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public void createAndSendCode(Long userId) throws MessagingException {
//        String to = "tankim1972@gmail.com";
        String subject = "Shop confirmation";

        User user = userRepo.findById(userId).orElseThrow(
                () ->  new RuntimeException("User does not found")
        );

        Shop shop = shopRepo.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Shop does not found")
        );

        if (shop.getStatus().equals(Status.COMPLETED.getStatus())) {
            throw new RuntimeException("Shop is activated!");
        }

        int code = generateCode();
        String html = generateHTMLMailContent(code, shop.getShopName());

        ShopCode shopCode = ShopCode.builder()
                .code(code)
                .user(user)
                .build();

        shopCodeRepo.save(shopCode);

        emailService.sendEmail(shop.getEmail(), subject, html );
    }

    @Override
    public String getCodeByShopId(Long shopId) {
        return "";
    }

    private int generateCode() {
        long timestamp = System.currentTimeMillis() % 1000000; // Take last 6 digits
        int randomNum = new Random().nextInt(10); // Add a random digit
        int uniqueNumber = (int) ((timestamp + randomNum) % 900000) + 100000;
        return uniqueNumber;
    }

    private String generateHTMLMailContent(int code, String username) {
        String html = "<body style=\"margin: 0; padding: 0; font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center;\">\n" +
                "    <table width=\"100%\" bgcolor=\"#f4f4f4\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "                <table width=\"100%\" max-width=\"600px\" bgcolor=\"#ffffff\" style=\"margin: 20px auto; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding: 20px; background-color: #007bff; color: #ffffff; font-size: 24px; font-weight: bold; border-top-left-radius: 10px; border-top-right-radius: 10px;\">\n" +
                "                            Mã xác nhận của bạn\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding: 20px; font-size: 16px; color: #333;\">\n" +
                "                            <p>Xin chào, "+username+"</p>\n" +
                "                            <p>Mã xác nhận của bạn là:</p>\n" +
                "                            <p style=\"font-size: 28px; font-weight: bold; color: #007bff; letter-spacing: 4px; margin: 10px 0;\">"+ code +"</p>\n" +
                "                            <p>Vui lòng nhập mã này để xác minh tài khoản của bạn.</p>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding: 20px; font-size: 14px; color: #777;\">\n" +
                "                            Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding: 15px; font-size: 14px; color: #ffffff; background-color: #007bff; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;\">\n" +
                "                            © 2024 Công ty của bạn | Bảo mật & Điều khoản\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>";

        return html;
    }
}
