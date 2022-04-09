package #{PackageName}.config


import #{PackageName}.response.ResponseGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.naming.AuthenticationException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component("restAuthenticationEntryPoint")
class RestAuthenticationEntryPoint : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(httpServletRequest: HttpServletRequest?, response: HttpServletResponse?, authenticationException: org.springframework.security.core.AuthenticationException?) {
        val objectMapper = ObjectMapper()
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.outputStream?.println(objectMapper.writeValueAsString(httpServletRequest?.let { authenticationException?.let { it1 -> ResponseGenerator.getErrorResponse(it1, it) } }))
    }
}