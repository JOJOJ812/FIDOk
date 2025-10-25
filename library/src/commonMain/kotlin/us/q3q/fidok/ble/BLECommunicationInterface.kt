package us.q3q.fidok.ble

import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import us.q3q.fidok.ctap.AuthenticatorDevice
import us.q3q.fidok.ctap.FIDOkLibrary

interface BLECommunicationInterface {
    fun ref(): Boolean

    fun unref()

    suspend fun startDiscovery(library: FIDOkLibrary): Job?

    fun stop()

    suspend fun getDiscoveredDevices(): List<AuthenticatorDevice>

    suspend fun connect(address: String): Boolean

    @OptIn(ExperimentalUnsignedTypes::class)
    suspend fun setNotify(
        address: String,
        serviceUuid: String,
        characteristicUuid: String,
        responder: Channel<UByteArray>?,
    ): Boolean

    @OptIn(ExperimentalUnsignedTypes::class)
    suspend fun write(
        address: String,
        serviceUuid: String,
        characteristicUuid: String,
        data: UByteArray,
        expectResponse: Boolean = false,
    ): UByteArray?

    @OptIn(ExperimentalUnsignedTypes::class)
    suspend fun read(
        address: String,
        serviceUuid: String,
        characteristicUuid: String,
    ): UByteArray?
}
