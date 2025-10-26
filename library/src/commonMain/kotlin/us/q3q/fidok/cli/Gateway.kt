package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.subcommands

class Gateway : CliktCommand() {
    init {
        subcommands(HID())
    }

    override fun help(context: Context) = "Proxy an Authenticator via a different attachment method"

    override fun aliases(): Map<String, List<String>> {
        return mapOf(
            "usb" to listOf("hid"),
        )
    }

    override fun run() {
    }
}
