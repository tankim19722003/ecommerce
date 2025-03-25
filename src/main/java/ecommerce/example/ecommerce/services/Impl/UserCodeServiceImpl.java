package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.CodePurposeRepo;
import ecommerce.example.ecommerce.Repo.ShopRepo;
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

    @Autowired
    private ShopRepo shopRepo;

    @Override
    public int sendCode(String email) throws MessagingException {
        String subject = "Shop confirmation";

        int code = generateCode();
        String html = generateHTMLMailContent(code);


        emailService.sendEmail(email, subject, html );

        return code;
    }

    @Override
    @Transactional
    public void confirmCode(UserCodeDTO userCodeDTO) {
        UserCode userCode = userCodeRepo
                .findUserCode(1L, userCodeDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Code does not found"));

        if (userCode.getActive()) {
            throw new RuntimeException("Code is used");
        }

        if (userCode.getDateEnd().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid code");
        }

        if(userCode.getCode() != userCodeDTO.getCode()) {
            throw new RuntimeException("Invalid code");
        }

        if (!userCode.getEmail().equals(userCodeDTO.getEmail())) {
            throw  new RuntimeException("Invalid code");
        }


        userCode.setActive(true);

        userCodeRepo.save(userCode);
    }


    @Override
    @Transactional
    public void handleCode(Long userId, String email, Long codePurposeId) throws MessagingException {

//        Boolean isShopEmailExisting = shopRepo.existsByEmail(email);
//        if (isShopEmailExisting) {
//            throw new RuntimeException("Email is existing!!");
//        }

        User user = userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User does not found"));


        CodePurpose codePurpose = codePurposeRepo.findById(codePurposeId).orElseThrow(
                () -> new RuntimeException("Code purpose does not found")
        );

        // delete all code
        int code = sendCode(email);

        UserCode userCode = UserCode.builder()
                .code(code)
                .user(user)
                .codePurpose(codePurpose)
                .email(email)
                .build();

        userCodeRepo.save(userCode);

    }

    @Override
    public void handleCode(String email, Long codePurposeId) throws MessagingException {

        // check email existing
//        Boolean isEmailExisting = userCodeRepo.existsByEmail(email);
//        if (isEmailExisting) {
//            throw new RuntimeException("Email is existing");
//        }

        CodePurpose codePurpose = codePurposeRepo.findById(codePurposeId).orElseThrow(
                () -> new RuntimeException("Code purpose does not found")
        );


        // delete all code
        int code = sendCode(email);

        UserCode userCode = UserCode.builder()
                .code(code)
                .codePurpose(codePurpose)
                .email(email)
                .build();

        userCodeRepo.save(userCode);
    }

    @Override
    public void confirmCode(
            UserCodeDTO userCodeDTO,
            Long userId
    ) {

        UserCode userCode = userCodeRepo
                .findUserCode(1L, userCodeDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Code does not found"));

        if (userCode.getActive()) {
            throw new RuntimeException("Code is used");
        }

        if (userCode.getDateEnd().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid code");
        }

        if(userCode.getCode() != userCodeDTO.getCode()) {
            throw new RuntimeException("Invalid code");
        }

        if (!userCode.getEmail().equals(userCodeDTO.getEmail())) {
            throw  new RuntimeException("Invalid code");
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

    private String generateHTMLMailContent(int code) {
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
                "                            <p>Xin chào, Snapbuyer</p>\n" +
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


