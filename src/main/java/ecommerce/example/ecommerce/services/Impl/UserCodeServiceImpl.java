package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.CodePurposeRepo;
import ecommerce.example.ecommerce.Repo.UserCodeRepo;
import ecommerce.example.ecommerce.Repo.UserRepo;
import ecommerce.example.ecommerce.dtos.UserCodeDTO;
import ecommerce.example.ecommerce.models.CodePurpose;
import ecommerce.example.ecommerce.models.User;
import ecommerce.example.ecommerce.models.UserCode;
import ecommerce.example.ecommerce.services.UserCodeService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UserCodeServiceImpl implements UserCodeService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private UserCodeRepo userCodeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CodePurposeRepo codePurposeRepo;

    @Override
    @Transactional
    public void createAndSendCode(Long userId, String email, Long codePurposeId) throws MessagingException {
        String subject = "Shop confirmation";

        User user = userRepo.findById(userId).orElseThrow(
                () ->  new RuntimeException("User does not found")
        );

        CodePurpose codePurpose = codePurposeRepo.findById(codePurposeId).orElseThrow(
                () -> new RuntimeException("Code purpose does not found")
        );


        int code = generateCode();
        String html = generateHTMLMailContent(code, user.getAccount());

        UserCode shopCode = UserCode.builder()
                .code(code)
                .user(user)
                .codePurpose(codePurpose)
                .build();

        userCodeRepo.save(shopCode);

        emailService.sendEmail(email, subject, html );
    }

    @Override
    public void confirmCode(
            @RequestBody UserCodeDTO userCodeDTO
    ) {

        boolean isUserExisting = userRepo.existsById(userCodeDTO.getUserId());

        if (!isUserExisting) {
            throw new RuntimeException("User does not found");
        }

        UserCode userCode = userCodeRepo
                .findLatestByCodePurposeIdAndUserId(1L, userCodeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Code does not found"));

        if (userCode.getActive()) {
            throw new RuntimeException("Code is used");
        }
        if (userCode.getUser().getId() != userCodeDTO.getUserId()) {
            throw new RuntimeException("Invalid code!");
        }

        if (userCode.getDateEnd().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid code");
        }
        

        userCode.setActive(true);

        userCodeRepo.save(userCode);

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


