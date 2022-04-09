package #{PackageName}.config

import #{PackageName}.autowired
import #{PackageName}.models.entities.logging.RequestsLogging
import #{PackageName}.models.repos.RequestInterceptorRepository
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Instant
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
open class WebMvcConfig: WebMvcConfigurer {

    private val loggingRepository: RequestInterceptorRepository by autowired()

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(object : HandlerInterceptor {
            override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
                super.afterCompletion(request, response, handler, ex)
                val isSuccess = ex == null
                val headersMap: Map<String, List<String>> = Collections.list(request.headerNames)
                        .stream()
                        .collect(Collectors.toMap(
                                Function.identity()
                        ) { h: String? -> Collections.list(request.getHeaders(h)) })

                loggingRepository.save(RequestsLogging(
                        path = request.servletPath,
                        userAgent = request.getHeader("USER-AGENT"),
                        headers = headersMap.toString(),
                        isSuccess = isSuccess,
                        sentAt = Instant.now(),
                        params = request.parameterNames.toString()
                ))
            }
        })
    }

}