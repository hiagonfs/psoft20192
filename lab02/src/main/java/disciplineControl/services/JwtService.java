package disciplineControl.services;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import disciplineControl.entities.Usuario;
import disciplineControl.filters.TokenFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JwtService {

	private UsuarioService usuarioService;

	private final String TOKEN_KEY = "minha_senha";

	public JwtService(UsuarioService uService) {
		this.usuarioService = uService;
	}

	public String geraToken(String email) {
		return Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
	}

	public boolean usuarioExiste(String authorizationHeader) throws ServletException {

		String sujeito = getUsuarioDoToken(authorizationHeader);

		return usuarioService.getUsuarioById(sujeito).isPresent();

	}

	public boolean usuarioTemPermissao(String email, String header) throws ServletException {

		String sujeito = getUsuarioDoToken(header);

		Optional<Usuario> user = usuarioService.getUsuarioById(sujeito);

		return user.isPresent() && user.get().getEmail().equals(email);

	}

	private String getUsuarioDoToken(String authorizationHeader) throws ServletException {

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

		String sujeitoDoToken = null;

		try {
			sujeitoDoToken = Jwts.parser().setSigningKey("minha_senha").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}

		return sujeitoDoToken;

	}

}
