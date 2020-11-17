package com.example.demo.model.pojos;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails{
	
	@Id @GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private RolesUsuarios rol;
	@Enumerated(EnumType.STRING)
	private EstadosDeUsuarios estado;
	
	private LocalDate fechaCreacion;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		List<RolesUsuarios> roles = new Vector<RolesUsuarios>();
		
		roles.add(rol);
		return roles.
				stream().
				map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
	}

	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
