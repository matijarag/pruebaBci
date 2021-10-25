package es.springboot.bci.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import es.springboot.bci.model.ErrorMsg;
import es.springboot.bci.model.Phones;
import es.springboot.bci.model.Usuario;
import es.springboot.bci.model.UsuarioJson;
import es.springboot.bci.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	/*Set REGEX de la password junto con su largo minimo y maximo*/
	private String passwordMinLen = "8";
	private String passwordMaxLen = "20";
	private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>_.]).{"+passwordMinLen+","+passwordMaxLen+"}$";
	
	/*Metodo get que busca todos los usuarios, devuelve una lista de objeto Usuario*/
	@GetMapping("/users")
	public ResponseEntity<List<Usuario>> getAllUsers(){
		
		try {
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			
			usuarioRepository.findAll().forEach(usuarios::add);
			
			if(usuarios.isEmpty()) {
				/*En el caso de no encontrar usuarios devuelve una respuesta vacia con Codigo 204 No Content*/
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				/*En el caso de encontrar usuarios devuelve la lista con codigo 200 Ok*/
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}catch(Exception e){
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	/*Metodo para agregar usuario*/
	@PostMapping("/usersAdd")
	public ResponseEntity<Object> createUsuario(@RequestBody UsuarioJson usuario) {
		try {
			/*Definicion y preparacion de variables*/
			ErrorMsg msgError = new ErrorMsg();
			Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			Date fecha = new Date(System.currentTimeMillis());
			
			String name = usuario.getName();
			String email = usuario.getEmail();
			String password = usuario.getPassword();
			List<Phones> phones = usuario.getPhones();
			String numero = "+"+phones.get(0).getContrycode()+phones.get(0).getCitycode()+phones.get(0).getNumber();
			
			/*Preparacion y creacion del JSON web token*/
			String subject = name+email;
			String jws = Jwts.builder().setSubject(subject).signWith(key).compact();
			
			/*Validacion de formato email*/
			if(checkEmailFormat(email)==false) {
				msgError.setMensaje("El correo no es válido");
				return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
			}
			/*Validacion de Existencia de email*/
			else if(existsEmail(email)==true) {
				
				msgError.setMensaje("El correo ya se ha registrado");
				return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
				
			}
			/*Validacion de formato password*/
			else if (checkPasswordFormat(password)==false) {
				
				msgError.setMensaje("Formato de contraseña no válido, debe tener entre "+passwordMinLen +" y "+passwordMaxLen+" caracteres, componerse de minimo 1 numero, 1 letra minúscula, 1 letra mayúscula y un caracter especial");
				return new ResponseEntity<>(msgError, HttpStatus.NOT_ACCEPTABLE);
				
			} else {
				/*Creacion del usuario en la base de datos*/
				Usuario _usuario = usuarioRepository
						.save(new Usuario(name, email, password, numero,fecha,fecha,fecha,jws,true));
				
				UUID uuid = UUID.randomUUID();
				
				/*Creacion del objeto a enviar en la Response utilizando datos del Usuario recien creado*/
				Map<String, Object> map = new HashMap<String, Object>();
	            map.put("id", uuid.toString());
	            map.put("created", _usuario.getCreated());
	            map.put("modified", _usuario.getModified());
	            map.put("last_login", _usuario.getLast_login());
	            map.put("token", _usuario.getToken());
	            map.put("isactive", _usuario.isIs_active());
	            
				return new ResponseEntity<Object>(map, HttpStatus.CREATED);
			}
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*Metodo para validar existencia de email*/
	public boolean existsEmail(String email) {
		
		List<Usuario> user = new ArrayList<Usuario>();
		
		usuarioRepository.findByEmail(email).forEach(user::add);
		
		
		if(user.isEmpty()) {
			return false;
		}else {
			return true;
		}
	
	}
	/*Metodo para validar formato email*/
	public boolean checkEmailFormat(String email) {
		
		String regex = "^(.+)@(.+)$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(email);
		
		if(matcher.matches()==true) {
			return true;
		}else {
			return false;
		}
	}
	/*Metodo para validar formato password*/
	public boolean checkPasswordFormat(String password) {
		
		Pattern pattern = Pattern.compile(passwordPattern);
		
		Matcher matcher = pattern.matcher(password);
		
		if (matcher.matches()==true) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
