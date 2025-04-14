package ecommerce.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeedBackUpdating {

    @JsonProperty("feedback_id")
    private Long feedbackId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("rating")
    private int rating;
}
