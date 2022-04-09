package #{PackageName}.config

import #{PackageName}.autowired
import #{PackageName}.errors.UnAuthException
import #{PackageName}.errors.payload.PayLoadError
import #{PackageName}.models.entities.account.Account
import #{PackageName}.models.repos.AccountsRepository
import #{PackageName}.response.ResponseGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter : OncePerRequestFilter() {

    private val jwtUtil: JwtUtil by autowired()
    private val service: AccountsRepository by autowired()

    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        val authorizationHeader = httpServletRequest.getHeader("Authorization")
        var token: String? = null
        var userName: String? = null
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7)
            userName = jwtUtil.extractUsername(token)
        }
        if (userName != null && SecurityContextHolder.getContext().authentication == null) {
            val account: Optional<Account> = service.findByUserName(userName)
            if (account.isPresent) {
                if (account.get().status.equals("DE_ACTIVATED")) {
                    generateErrorResponse(httpServletResponse, httpServletRequest)
                    return
                }

                val userDetails: UserDetails = account.get()
                if (jwtUtil.validateToken(token, userDetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            } else {
                throw ServletException("Access Not Allowed")
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }

    private fun generateErrorResponse(response: HttpServletResponse, request: HttpServletRequest) {
        val objectMapper = ObjectMapper()
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        response?.outputStream?.println(objectMapper.writeValueAsString(request?.let {
            ResponseGenerator.getErrorResponse(
                UnAuthException("Account Not Verified By Phone Number", PayLoadError(
                    "No Any Request Accessable Until You Verify The Account",
                    "Use Otp Verification Request To Verify Your Phone Number"
                ),
            ), request)
        }))
    }
}