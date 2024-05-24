package dev.vrba.slack.extension

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified Context> Context.deriveLogger(): Logger = LoggerFactory.getLogger(Context::class.qualifiedName)
