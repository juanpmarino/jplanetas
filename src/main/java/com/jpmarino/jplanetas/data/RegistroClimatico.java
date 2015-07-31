package com.jpmarino.jplanetas.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
* Registro climático que se almacena en la base.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
@Entity
public class RegistroClimatico {
	@Id public Long id;
	@Index public Integer dia;
	public String clima;
	
	/**
	 * Constructor de un registro climático vacío.
	 *
	 */
	public RegistroClimatico() {
		
	}
	
	/**
	 * Constructor de un registro climático.
	 *
	 * @param día de este registro
	 * @param clima pronosticado en el día correspondiente
	 */
	public RegistroClimatico(Integer dia, String clima) {
		this.dia = dia;
		this.clima = clima;
	}
	
}
