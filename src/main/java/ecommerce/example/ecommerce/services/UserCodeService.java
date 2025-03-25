package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.UserCodeDTO;
import jakarta.mail.MessagingException;

public interface UserCodeService {

    void handleCode(Long userId, String email, Long codePurposeId) throws MessagingException;

    void handleCode(String email, Long codePurposeId) throws MessagingException;

    int sendCode(String email) throws MessagingException;

    void confirmCode(UserCodeDTO userCodeDTO, Long userId);

    void confirmCode(UserCodeDTO userCodeDTO);
}
