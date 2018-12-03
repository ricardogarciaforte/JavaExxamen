package com.mx.examen.converter;

import java.beans.PropertyEditorSupport;

import com.mx.examen.entity.Perfil;

/**
 * This Class represents a converter with the goals of converting a String
 * received from the application pages and converting it to a system user profile.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
public class PerfilConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		Perfil perfil = Perfil.ADMIN;		
		if (text.equalsIgnoreCase("USER")) {
			perfil = Perfil.USER;
		}		
		super.setValue(perfil);
	}	

}
