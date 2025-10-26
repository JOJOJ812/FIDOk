package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import kotlinx.coroutines.runBlocking
import us.q3q.fidok.ctap.CTAPClient

class PinChange : CliktCommand(name = "change") {
    private val client by requireObject<CTAPClient>()

    private val newPin by option()
        .prompt("Enter new PIN", requireConfirmation = true, hideInput = true)
        .check { it.isNotEmpty() }

    override fun help(context: Context) = "Change an existing PIN"

    override fun run() {
        runBlocking {
            client.changePIN(newPin)
        }

        echo("PIN changed.")
    }
}
