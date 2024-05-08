package com.example.uvs.FeedBacks;

import java.util.List;

/**
 * Defines the ActionStrategy2 interface, used in the strategy pattern for feedbacks.
 */
public interface ActionStrategy2 {
    /**
     * Retrieves the feedback text.
     *
     * @return A list of feedback for the application.
     */
    List<FeedBackForApp> getFeedbackText();
}
