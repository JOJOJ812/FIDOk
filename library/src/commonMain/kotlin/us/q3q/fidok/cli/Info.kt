package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.requireObject
import us.q3q.fidok.ctap.FIDOkLibrary

class Info : CliktCommand() {
    private val library by requireObject<FIDOkLibrary>()

    override fun help(context: Context) = "Show information about an Authenticator"

    override fun run() {
        val client = getSuitableClient(library) ?: return

        val info = client.getInfo()

        echo("Authenticator info for $client: $info")
    }
}
