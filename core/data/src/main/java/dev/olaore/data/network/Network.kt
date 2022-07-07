package dev.olaore.data.network

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class GitHubInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val newRequest = req.newBuilder()
            .header(
                "Authorization",
                "token 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"
            )
            .build()

        return chain.proceed(newRequest)
    }
}
