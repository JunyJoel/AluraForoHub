package com.challenge.forohub.controllers;

/*import com.challenge.forohub.entities.usuario.DatosAutenticacionUsuario;
import com.challenge.forohub.infra.security.DatosTokenJWT;
import com.challenge.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class AuthenticationController {
    /*@Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager manager;

    {
         System.out.println("authentication controller is running");
}
    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        System.out.println("intentando iniciar sesion...");
        try {
            System.out.println("iniciando autenticacion...");
            var passwordEncoder = new BCryptPasswordEncoder();
            var encryptedPassword = passwordEncoder.encode("123456");
            System.out.println(encryptedPassword);

            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.nombre(), datos.contrasena());
            var authentication = manager.authenticate(authenticationToken);
            //var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

            System.out.println("autenticado: " + datos.nombre());

            return ResponseEntity.ok(new DatosTokenJWT("tokenJWT"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test")
    public void test() {
        System.out.println("test");
    }*/
}
