package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.Abort
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.core.obj
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.core.subcommands
import us.q3q.fidok.ctap.CTAPOption
import us.q3q.fidok.ctap.FIDOkLibrary

class Cred : CliktCommand() {
    init {
        subcommands(CredList(), Delete(), UpdateUser())
    }

    override fun aliases(): Map<String, List<String>> {
        return mapOf(
            "set-user" to listOf("update-user"),
        )
    }

    private val library by requireObject<FIDOkLibrary>()

    override fun help(context: Context) = "Manage discoverable credentials"

    override fun run() {
        val client = getSuitableClient(library)

        if (client == null) {
            echo("No devices found", err = true)
            throw Abort()
        }

        val options = client.getInfoIfUnset().options
        if (options?.get(CTAPOption.CLIENT_PIN.value) != true &&
            options?.get(CTAPOption.INTERNAL_USER_VERIFICATION.value) != true
        ) {
            throw UsageError("Credential management commands require a PIN or internal User Verification enabled")
        }

        currentContext.obj = client
    }
}
