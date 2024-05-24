package dev.vrba.slack.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import java.net.URI

@Controller
class HomeController {
    @Get
    fun index(): HttpResponse<Unit> {
        return HttpResponse.redirect(URI.create("https://github.com/jirkavrba/slack-tenor-bot"))
    }
}
