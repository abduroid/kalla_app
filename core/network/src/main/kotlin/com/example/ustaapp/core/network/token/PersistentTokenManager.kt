package com.example.ustaapp.core.network.token

import androidx.datastore.core.DataStore
import com.example.ustaapp.core.datastore.Tokenpair
import com.example.ustaapp.core.datastore.copy
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersistentTokenManager @Inject constructor(
    private val tokenDataStore: DataStore<Tokenpair>,
) : TokenManager {

    override suspend fun saveAccessToken(token: String) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = token
                }
            }
        } catch (ioException: IOException) {
            Timber.tag("TokenManager").e(ioException, "Failed to update access token")
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        try {
            tokenDataStore.updateData {
                it.copy {
                    refreshToken = token
                }
            }
        } catch (ioException: IOException) {
            Timber.tag("TokenManager").e(ioException, "Failed to update refresh token")
        }
    }

    override suspend fun getAccessToken(): String? =
        tokenDataStore.data.map { it.accessToken }.firstOrNull()

    override suspend fun getRefreshToken(): String? =
        tokenDataStore.data.map { it.refreshToken }.firstOrNull()

    override suspend fun clearAllTokens() {
        try {
            tokenDataStore.updateData {
                it.copy {
                    accessToken = ""
                    refreshToken = ""
                }
            }
        } catch (ioException: IOException) {
            Timber.tag("TokenManager").e(ioException, "Failed to clear tokens")
        }
    }
}
