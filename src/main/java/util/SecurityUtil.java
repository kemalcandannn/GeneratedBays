package util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tr.bays.dto.teskilat.KullaniciDto;
import tr.bays.service.teskilat.KullaniciService;


public class SecurityUtil {
	
	public static KullaniciDto getOnlineUser(KullaniciService kullaniciService) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			return kullaniciService.kullaniciAdiIleGetir(username);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
