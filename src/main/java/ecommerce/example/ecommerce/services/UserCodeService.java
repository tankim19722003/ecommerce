package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserCodeDTO;
import jakarta.mail.MessagingException;

public interface UserCodeService {

    void createAndSendCode(Long userId, String email, Long codePurposeId) throws MessagingException;

    void confirmCode(UserCodeDTO userCodeDTO);


}
