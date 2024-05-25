package dev.vrba.slack.tenor.bot.controller;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.net.URI;

@Controller
public class HomeController {

    @Get
    @NonNull
    public HttpResponse<Void> index() {
        return HttpResponse.redirect(URI.create("https://github.com/jirkavrba/slack-tenor-bot"));
    }

}
