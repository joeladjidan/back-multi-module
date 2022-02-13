package com.joeladjidan.gestiondestock.model.auth;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ExtendedUser extends User {
  /**
	 * 
	 */
 private static final long serialVersionUID = 1L;
 
  @Getter
  @Setter
  private Integer idEntreprise;

  public ExtendedUser(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public ExtendedUser(String username, String password, Integer idEntreprise,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.idEntreprise = idEntreprise;
  }
}
