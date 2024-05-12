package com.recipes.app.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.Serializer
import com.google.gson.Gson
import com.recipes.domain.entity.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.util.Base64

object UserSerializer : Serializer<UserModel> {


    // Start DataStore by using ProtoType with encrypt or decrypt
    private val cryptoData = CryptoData()
    override val defaultValue: UserModel
        get() = UserModel(id = String(), email = String())

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun writeTo(t: UserModel, output: OutputStream) {
        val jsonUser = Gson().toJson(t)
        val byte = jsonUser.toByteArray()
        val encryptedText = Base64.getEncoder().encodeToString(cryptoData.encrypt(byte = byte))

        withContext(Dispatchers.IO) {
            output.write(encryptedText.toByteArray())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun readFrom(input: InputStream): UserModel {
        val data = input.readBytes().decodeToString()
        val originalText = cryptoData.decrypt(Base64.getDecoder().decode(data))?.decodeToString()
        return Gson().fromJson(originalText, UserModel::class.java)
    }
    // End DataStore by using ProtoType with encrypt  or decrypt
}