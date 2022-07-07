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
                "67a38e51baa42a7c5d5b:b7b295836fd6e01ec3eef4d7cb380ad0fa4045c1"
            )
            .build()

        return chain.proceed(newRequest)
    }
}
