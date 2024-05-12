package com.recipes.app.utils

import android.security.keystore.KeyProperties

object Constants {


    const val BASE_URL = "https://dummyjson.com/"
    const val END_POINT = "recipes"
    const val LIMIT = 10

    const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$ENCRYPTION_PADDING"

}
