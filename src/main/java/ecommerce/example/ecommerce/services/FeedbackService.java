package ecommerce.example.ecommerce.services;

import ecommerce.example.ecommerce.dtos.FeedBackDTO;
import ecommerce.example.ecommerce.dtos.FeedBackUpdating;
import ecommerce.example.ecommerce.responses.FeedBackResponse;

import java.util.List;

public interface FeedbackService {

    FeedBackResponse createFeedBack(FeedBackDTO feedBackDTO);
    FeedBackResponse updateFeedBack(FeedBackUpdating feedBackUpdating);
    List<FeedBackResponse> getAllFeedBackByProductId(Long productId);
}
