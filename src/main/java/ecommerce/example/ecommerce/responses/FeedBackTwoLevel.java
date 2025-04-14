package ecommerce.example.ecommerce.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedBackTwoLevel extends FeedBackResponse {

    @JsonProperty("feedback_level_two")
    private FeedBackResponse feedBackResponse;
}
