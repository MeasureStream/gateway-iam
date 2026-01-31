package measuremanager.iam_module


import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalDateTime


@RestController
class HomeController(private val restTemplate: RestTemplate,
    @Value("\${OS:default}")
    private val OS: String
) {

    @GetMapping("","/")
    fun home() : Map<String, Any?>
    {
        return mapOf("name" to "home","date" to LocalDateTime.now())
    }
    @GetMapping("/secure","/secure/")
    fun secure() : Map<String, Any?>
    { val authentication = SecurityContextHolder.getContext().authentication
        return mapOf("name" to "secure","date" to LocalDateTime.now(),"principal" to authentication.principal)
    }
    @GetMapping("/anon","/anon/")
    fun anon() : Map<String, Any?>
    {
        return mapOf("name" to "anon","date" to LocalDateTime.now())
    }

    @GetMapping("/user/me/","/user/me" )
    fun me() : Map<String, Any?>
    { val authentication = SecurityContextHolder.getContext().authentication
        return mapOf("name" to "me", "date" to LocalDateTime.now(),"details" to authentication.details , "auth" to SecurityContextHolder.getContext().authentication.authorities)
    }

    @GetMapping("/login-options","/login-options/")
    fun loginOptions(): List<String>{

        val path = if(OS == "raspberrypi")  "https://www.christiandellisanti.uk" else  "http://localhost:8080"
        return listOf("${path}/oauth2/authorization/gateway", "${path}/login/", "${path}/secure")
    }

    @GetMapping("/me")
    fun me(@CookieValue(name="XSRF-TOKEN", required = false) xsrf: String? , authentication : Authentication?): Map<String,Any?>{
        val principal :  OidcUser?  = authentication?.principal as OidcUser?
        val name = principal?.preferredUsername ?: ""
        val securityContext: Any = SecurityContextHolder.getContext().authentication.principal
        //val path = if(OS == "raspberrypi")  "https://www.christiandellisanti.uk" else  "http://localhost:8080"
        val  path = when(OS){
          "raspberrypi" -> "https://www.christiandellisanti.uk"
          "dev" -> "https://dev.christiandellisanti.uk"
          else -> "http://localhost:8080"
        }
        return mapOf("name" to name,
            "loginUrl" to "${path}/oauth2/authorization/gateway",
            "logoutUrl" to "${path}/logout",
            "principal" to principal,
            "xsrfToken" to xsrf,
            "autorities" to SecurityContextHolder.getContext().authentication.authorities
        )


    }

    /*
    @GetMapping("/ruoli")
    fun ruoli() = roleService.findAll();
     */
    /*

    @PostMapping("/signup")
    //@Transactional
    fun signup(@RequestBody user: CreateUserDTO) {
        //val urlC = "http://localhost:8082/API/customers"
        //val urlP = "http://localhost:8082/API/professionals-create/"

        val urlC = "http://172.20.0.11:8080/API/customers" //LAB3 IP
        val urlP = "http://172.20.0.11:8080/API/professionals-create/"//LAB3 IP

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val response= userService.create(UserRequest(user.username, user.password, user.firstName, user.lastName, user.email))
        val userId: String = CreatedResponseUtil.getCreatedId(response);
        when(user.role) {
            "CUSTOMER" -> {
                userService.assignRole(userId, roleService.findByName("CUSTOMER"))
                val customer = CreateCustomerDTO(0, user.firstName, user.lastName,  user.email, user.phone, userId, mutableListOf(), mutableListOf())
                val request = HttpEntity(customer, headers)
                restTemplate.postForEntity(urlC, request, String::class.java)
            }
            "PROFESSIONAL" -> {
                userService.assignRole(userId, roleService.findByName("PROFESSIONAL"))
                val professional = CreateProfessionalDTO(0, user.firstName, user.lastName, user.email, user.phone, "emp_state", "location", 15, mutableSetOf(), userId, user.skills)
                val request = HttpEntity(professional, headers)
                restTemplate.postForEntity(urlP, request, String::class.java)
            }
        }
    }*/
}


class MultipartInputStreamFileResource(
    private val inputStream: InputStream,
    private val filename: String
) : InputStreamResource(inputStream) {
    override fun getFilename(): String = filename
    override fun contentLength(): Long = -1
}
