package com.example.uvs.FeedBacks;

import java.util.List;

/**
 * An interface for defining action strategies.
 */
public interface ActionStrategy2 {
    /**
     * Retrieves the feedback text.
     *
     * @return A list of feedback for the application.
     */
    List<FeedBackForApp> getFeedbackText();
}
