package dev.vrba.slack.tenor.bot.service.slack;

import dev.vrba.slack.tenor.bot.service.slack.dto.*;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Singleton
@AllArgsConstructor
public class SlackService {
    @NonNull
    private final SlackApiClient slackClient;

    @NonNull
    private final HttpClient genericClient;

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

        return slackClient.openView(request);
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

        return slackClient.updateView(request).then();
    }

    @NonNull
    public Mono<?> handleViewSubmission(@NonNull SlackViewSubmissionInteraction interaction) {
        final var hook = interaction.getView().getMetadata();
        final var image = (SlackImageBlock) interaction.getView().getBlocks()
            .stream()
            .filter(it -> it instanceof SlackImageBlock)
            .findFirst()
            .orElseThrow();

        final var user = interaction.getUser().getId();
        final var payload = new SlackWebhookRequest(
            "in_channel",
            String.format("<@%s> is sending a GIF from Tenor\n%s", user, image.getUrl())
        );

        return Mono.from(genericClient.exchange(HttpRequest.POST(hook, payload), Void.class));
    }

    @NonNull
    public Mono<Void> handleBlockAction(@NonNull SlackBlockActionsInteraction interaction) {
        System.out.println(interaction);
        return Mono.empty();
    }
}
