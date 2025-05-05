import okhttp3.Interceptor
import okhttp3.Response

class TimingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        println("Testing Sending request: ${request.url}")

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        val timeTakenMs = (t2 - t1) / 1_000_000.0

        println("Testing Received response from: ${response.request.url} in $timeTakenMs ms")

        return response
    }
}
