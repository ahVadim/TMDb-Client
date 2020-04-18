package com.example.core.prefs

import android.util.Base64
import com.google.crypto.tink.DeterministicAead
import com.ironz.binaryprefs.encryption.KeyEncryption

class TinkKeyEncryption(private val daead: DeterministicAead) : KeyEncryption {
    private val encoderFlags = Base64.NO_WRAP or Base64.URL_SAFE

    override fun encrypt(plaintext: String): String {
        val ciphertext = daead.encryptDeterministically(plaintext.toByteArray(), byteArrayOf())
        return Base64.encodeToString(ciphertext, encoderFlags)
    }

    override fun decrypt(cipher: String): String {
        val cipertext = Base64.decode(cipher, encoderFlags)
        return String(daead.decryptDeterministically(cipertext, byteArrayOf()))
    }
}
