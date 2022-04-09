package #{PackageName}.config

import #{PackageName}.models.entities.account.Account
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class JwtUtil {
    private val secret = "HoxSo6rNnppwAdUeUUIZbYfm2c1fEQe93vzM++Dlbyo="
    fun extractUsername(token: String?): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractId(token: String?): String? {
        return extractClaim(token,
            Function<Claims, String?> { claims: Claims -> claims.get("id") as String? })
    }

    fun extractExpiration(token: String?): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
    }

    private fun isTokenExpired(token: String?): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(account: Account?): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims.put("enabled", java.lang.String.valueOf(account?.isEnabled()))
        account?.type?.let { claims.put("type", it) }
        claims.put("id", java.lang.String.valueOf(account?.id))
        return createToken(claims, account?.getUsername() ?: "")
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(2025, 12, 12))
            .signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }
}