package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import us.q3q.fidok.ctap.CTAPClient

class PinSet : CliktCommand(name = "set") {
    private val client by requireObject<CTAPClient>()

    private val newPin by option()
        .prompt("Enter new PIN", requireConfirmation = true, hideInput = true)
        .check { it.isNotEmpty() }

    override fun help(context: Context) = "Set a new PIN"

    override fun run() {
        client.setPIN(newPin)

        echo("PIN set.")
    }
}
