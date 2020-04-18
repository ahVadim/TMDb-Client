package com.example.core.prefs

import android.util.Base64
import com.google.crypto.tink.Aead
import com.ironz.binaryprefs.encryption.ValueEncryption

class TinkValueEncryption(private val aead: Aead) : ValueEncryption {

    override fun encrypt(plaintext: ByteArray): ByteArray {
        val ciphertext = aead.encrypt(plaintext, byteArrayOf())
        return Base64.encode(ciphertext, Base64.DEFAULT)
    }

    override fun decrypt(cipher: ByteArray): ByteArray {
        val cipertext = Base64.decode(cipher, Base64.DEFAULT)
        return aead.decrypt(cipertext, byteArrayOf())
    }
}
