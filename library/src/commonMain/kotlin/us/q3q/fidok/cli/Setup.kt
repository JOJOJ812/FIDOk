package us.q3q.fidok.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.requireObject
import kotlinx.coroutines.runBlocking
import us.q3q.fidok.ez.EZHmac
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
class Setup : CliktCommand() {
    private val ezhmac by requireObject<EZHmac>()

    override fun help(context: Context) = "Set up a credential for use encrypting/decrypting data"

    override fun run() {
        runBlocking {
            val ret = ezhmac.setup()
            echo(Base64.UrlSafe.encode(ret))
        }
    }
}
