package ecommerce.example.ecommerce.services.Impl;

import ecommerce.example.ecommerce.Repo.*;
import ecommerce.example.ecommerce.dtos.FeedBackDTO;
import ecommerce.example.ecommerce.dtos.FeedBackUpdating;
import ecommerce.example.ecommerce.models.*;
import ecommerce.example.ecommerce.responses.FeedBackResponse;
import ecommerce.example.ecommerce.responses.FeedBackTwoLevel;
import ecommerce.example.ecommerce.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private FeedBackRepo feedbackRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    @Transactional
    public FeedBackResponse createFeedBack(FeedBackDTO feedBackDTO) {

        User user = userRepo.findById(feedBackDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not found"));

        Order order = orderRepo.findById(feedBackDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order does not found"));

        if (!order.getStatus().equals(Order.COMPLETED))
            throw new RuntimeException("Feedback fail! Order haven't completed yet");

        OrderDetail orderDetail = orderDetailRepo.findById(feedBackDTO.getOrderDetailId())
                .orElseThrow(() ->  new RuntimeException("Order detail does not found"));

        Feedback feedback = new Feedback();
        feedback.setContent(feedBackDTO.getContent());
        feedback.setRating(feedBackDTO.getRating());
        feedback.setUser(user);
        feedback.setOrderDetail(orderDetail);
        feedback.setOrderDetail(orderDetail);

        feedbackRepo.save(feedback);

        // calculate rating for the product
        Double rating = feedbackRepo.getRating(orderDetail.getProduct().getId());

        // save rating to the product
        Product product= productRepo.findById(orderDetail.getProduct().getId())
                .orElseThrow(() ->  new RuntimeException("Product does not found"));

        product.setRating(rating);

        productRepo.save(product);

        return toFeedbackResponse(feedback);


    }

    @Override
    public FeedBackResponse updateFeedBack(FeedBackUpdating feedBackUpdating) {

        Feedback feedback = feedbackRepo.findById(feedBackUpdating.getFeedbackId())
                .orElseThrow(() -> new RuntimeException("Feedback does not found!!!"));

        feedback.setContent(feedBackUpdating.getContent());

        feedback.setRating(feedBackUpdating.getRating());

        // calculate the product rating
        feedbackRepo.save(feedback);

        // save rating to the product
        Double rating = feedbackRepo.getRating(feedback.getOrderDetail().getProduct().getId());

        Product product= productRepo.findById(feedback.getOrderDetail().getProduct().getId())
                .orElseThrow(() ->  new RuntimeException("Product does not found"));

        product.setRating(rating);

        productRepo.save(product);


        return toFeedbackResponse(feedback);




    }

    private FeedBackResponse toFeedbackResponse(Feedback feedback) {

        if (feedback.getFeedback() != null) {
            // create feedback reponse
            FeedBackTwoLevel feedBackTwoLevel = new FeedBackTwoLevel();

            feedBackTwoLevel.setId(feedback.getId());
            feedBackTwoLevel.setContent(feedback.getContent());
            feedBackTwoLevel.setUserId(feedback.getUser().getId());
            feedBackTwoLevel.setUserAccount(feedback.getUser().getAccount());
            feedBackTwoLevel.setRating(feedback.getRating());
            feedBackTwoLevel.setOrderDetailId(feedback.getOrderDetail().getId());
            feedBackTwoLevel.setOrderId(feedback.getOrderDetail().getOrder().getId());

            FeedBackResponse feedBackResponse = new FeedBackResponse();
            feedBackResponse.setId(feedback.getFeedback().getId());
            feedBackResponse.setContent(feedback.getFeedback().getContent());
            feedBackResponse.setUserId(feedback.getFeedback().getUser().getId());
            feedBackResponse.setUserAccount(feedback.getFeedback().getUser().getAccount());
            feedBackResponse.setRating(feedback.getFeedback().getRating());
            feedBackTwoLevel.setOrderId(feedback.getFeedback().getOrderDetail().getOrder().getId());
            feedBackTwoLevel.setOrderDetailId(feedback.getFeedback().getOrderDetail().getId());

            feedBackTwoLevel.setFeedBackResponse(feedBackResponse);

            return feedBackTwoLevel;
        } else {

            FeedBackResponse feedBackResponse = new FeedBackResponse();
            feedBackResponse.setId(feedback.getId());
            feedBackResponse.setContent(feedback.getContent());
            feedBackResponse.setUserId(feedback.getUser().getId());
            feedBackResponse.setUserAccount(feedback.getUser().getAccount());
            feedBackResponse.setRating(feedback.getRating());
            feedBackResponse.setOrderId(feedback.getOrderDetail().getOrder().getId());
            feedBackResponse.setOrderDetailId(feedback.getOrderDetail().getId());

            return feedBackResponse;
        }

    }

    @Override
    public List<FeedBackResponse> getAllFeedBackByProductId(Long productId) {

        List<Feedback> feedbacks = feedbackRepo.findAllByProductId(productId);

        return feedbacks.stream()
                .map(feedback -> toFeedbackResponse(feedback))
                .toList();

    }
}
