package app.mat.library.feature.core.data.parameter

data object CryptographyParameter {
    enum class Algorithm(
        val key: String
    ) {
        AES(
            key = "AES"
        ),
        RSA(
            key = "RSA"
        )
    }

    enum class Mode(
        val key: String
    ) {
        ECB(
            key = "ECB"
        ),
        CBC(
            key = "CBC"
        ),
        CFB(
            key = "CFB"
        ),
        NONE(
            key = "NONE"
        )
    }

    enum class Padding(
        val key: String
    ) {
        ZERO(
            key = "ZeroBytePadding"
        ),
        PKCS1(
            key = "PKCS1Padding"
        ),
        PKCS5(
            key = "PKCS5Padding"
        ),
        PKCS7(
            key = "PKCS7Padding"
        ),
        OAEP(
            key = "OAEPPadding"
        ),
        OAEPWITHSHA_256ANDMGF1PADDING(
            key = "OAEPWithSHA-256AndMGF1Padding"
        )
    }

    data object Key {
        data object ApiAES {
            const val DEFAULT = "1234567891234567"
            const val IV_SIZE = 16
            const val KEY_SIZE_IN_BYTES = 16
            const val KEY_SIZE_IN_BITS = 128
            const val CHUNK_SIZE = 1024 * 4
        }

        data object BiometricAuthenticationAES {
            const val KEY_SIZE_IN_BITS = 128
        }
    }

    data object KeyStore {
        const val NAME = "AndroidKeyStore"
        const val LIBRARY_AES_ALIAS = "LibraryAES"
        const val LIBRARY_BIOMETRIC_AUTHENTICATION_AES_ALIAS = "LibraryLocalAuthenticationAES"
        const val LIBRARY_RSA_ALIAS_PAIR_ONE = "LibraryRSAPairOne"
        const val LIBRARY_RSA_ALIAS_PAIR_TWO = "LibraryRSAPairTwo"
        const val LIBRARY_RSA_KEY_SIZE_IN_BITS = 2048
    }

    data object Hash {
        const val MD5 = "MD5"
        const val SHA_256 = "SHA-256"
        const val MGF1 = "MGF1"
    }
}