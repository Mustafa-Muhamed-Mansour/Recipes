package com.recipes.app.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoData {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(Constants.ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(Constants.BLOCK_MODE)
                    .setEncryptionPaddings(Constants.ENCRYPTION_PADDING)
                    .build()
            )
        }.generateKey()
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry("secret", null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private val cipher = Cipher.getInstance(Constants.TRANSFORMATION)

    // Start the purpose from the encrypt
    fun encrypt(byte: ByteArray): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, getKey())
        val iv = cipher.iv
        val encrypted = cipher.doFinal(byte)
        return iv + encrypted
    }
    // End the purpose from the encrypt


    // Start the purpose from the decrypt
    fun decrypt(byte: ByteArray): ByteArray {
        val iv = byte.copyOfRange(0, cipher.blockSize)
        val data = byte.copyOfRange(cipher.blockSize, byte.size)
        cipher.init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        return cipher.doFinal(data)
    }
    // End the purpose from the decrypt
}