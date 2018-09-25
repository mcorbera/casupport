/*
 * Desarrollado por Servicios Avanzados (Viavansi) 2014
 * Contacto: comercial@serviciosavanzados.es
 *
 * http://www.serviciosavanzados.es
 * http://www.viavansi.com
 */
package org.viafirma.groovy.caSupports.ACA;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.viafirma.nucleo.X509.TypeLegalX509;
import org.viafirma.plugin.ca.supportedByPaterns.CASupport;
import org.viafirma.plugin.ca.supportedByPaterns.PredefinedCAs;
import org.viafirma.plugin.ca.supportedByPaterns.TransformPatern;
import org.viafirma.plugin.ca.supportedByPaterns.CASupport.Attributes;

import com.viavansi.viascripts.utils.AccionScript;

public class eFirma_PF extends AccionScript {

	private static final long serialVersionUID = 1L;

	public boolean test() {
		return true;
	}

	/**
	 * Permite refrescar el conjunto de variables.
	 * 
	 * @param m
	 * @return
	 */
	public Object execute(Map m) {
		List<TransformPatern> transformPatterns = new LinkedList<TransformPatern>();		
		
		//transformPatterns.add(new TransformPatern("E",  Attributes.email.name(), null));
					
		CASupport support = new CASupport("eFirma","1.3.6.1.4.1.44234.1.1.1.2","eFirma","eFirma",
			TypeLegalX509.INDIVIDUAL_DIGITAL_CERTIFICATE, "20110712","eFirma","http://redmine.viavansi.com/issues/6787",
			(TransformPatern[])transformPatterns.toArray()
		);
		
		List<String> urlsCRL = new LinkedList<String>();
		urlsCRL.add("https://www.efirma.com.py/repositorio/efirma.crl");
		urlsCRL.add("https://www.acraiz.gov.py/arl/ac_raiz_py.crl"); 
		support.setUrlsCRL(urlsCRL);
		
		
		return support;
	}	
	/**
	 * Recupera el nombre Identificador del script
	 * 
	 * @return
	 */
	public String getNombre() {
		return "eFirma";
	}

	/**
	 * Recupera la descripción de un Script.
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return "eFirma";
	}
}
