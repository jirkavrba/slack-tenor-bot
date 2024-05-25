package dev.vrba.slack.tenor.bot.service.slack;

import dev.vrba.slack.tenor.bot.service.slack.dto.*;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Singleton
@AllArgsConstructor
public class SlackModalService {
    @NonNull
    private final SlackApiClient client;

    @NonNull
    public Mono<SlackViewResponse> openLoadingView(
        @NonNull final String trigger,
        @NonNull final String response
    ) {
        final var request = new SlackViewOpenRequest(
            trigger,
            new SlackView(
                null,
                "modal",
                "tenor-gif-loading",
                new SlackTextBlock("Loading Tenor GIFs...", false),
                new ArrayList<>(),
                response,
                null
            )
        );

        return client.openView(request);
    }

    @NonNull
    public Mono<Void> updateSelectionView(
        @NonNull final String viewId,
        @NonNull final String imageUrl,
        @NonNull final String responseUrl
    ) {
        final var request = new SlackViewUpdateRequest(
            viewId,
            new SlackView(
                null,
                "modal",
                "tenor-gif-selection",
                new SlackTextBlock("Tenor GIF search", false),
                List.of(
                    new SlackActionsBlock(
                        List.of(
                            new SlackButtonElement(
                                new SlackTextBlock("Previous", false),
                                "previous",
                                "previous"
                            ),
                            new SlackButtonElement(
                                new SlackTextBlock("Next", false),
                                "next",
                                "next"
                            )
                        )
                    ),
                    new SlackImageBlock(
                        imageUrl,
                        "GIF"
                    )
                ),
                responseUrl,
                new SlackTextBlock("Send this GIF to Slack", false)

            )
        );

        return client.updateView(request).then();
    }
}
