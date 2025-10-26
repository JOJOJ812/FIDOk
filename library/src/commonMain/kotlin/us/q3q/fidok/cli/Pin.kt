package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.obj
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.core.subcommands
import us.q3q.fidok.ctap.FIDOkLibrary

class Pin : CliktCommand() {
    init {
        subcommands(PinSet(), PinChange())
    }

    override fun help(context: Context) = "Manage Authenticator PINs (user passwords)"

    private val library by requireObject<FIDOkLibrary>()

    override fun run() {
        val client = getSuitableClient(library)
        currentContext.obj = client
    }
}
