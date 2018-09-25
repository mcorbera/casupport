/*
 * Desarrollado por VIAFIRMA, S.L. (2015)
 * Contacto: comercial@viafirma.com
 *
 * http://www.viafirma.com
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.viafirma.nucleo.X509.TypeLegalX509;
import org.viafirma.plugin.ca.supportedByPaterns.CASupport;
import org.viafirma.plugin.ca.supportedByPaterns.PredefinedCAs;
import org.viafirma.plugin.ca.supportedByPaterns.TransformPatern;
import org.viafirma.plugin.ca.supportedByPaterns.CASupport.Attributes;


import com.viavansi.viascripts.utils.AccionScript;

public class Firmaprofesional_Vinculado_SW extends AccionScript {

	private static final long serialVersionUID = 1L;

	public boolean test() {
		return true;
	}

	/**
	 * 		// sustituir el nombre de la clase con el nombre del fichero groovy que estamos editando
	 *		
	 *		Permite refrescar el conjunto de variables.
	 * 		@param Map con las siguientes claves:
	 *
	 *
	 *
	 *		-String Id: Identificador del groovy (poner un nombre descriptivo del mismo)
	 *
	 *		-String OidRoot: Aqui necesitamos saber el OID de política de certificado que queremos soportar, 
	 *		    cuando un certificado contenga un OID que contenga esta cadena, saltará este soporte y se utilizará el groovy para reconocerlo. 
	 *			Para localizar el OID correspondiente abrimos el certificado de usuario con portecle, pulsamos 'Extensions', 
     *			y buscamos la propiedad Certificate policies (2.5.29.32) y vendrá informado en el Policy identifier, 
	 *			normalmente cada numero del OID representa una propiedad y si nos quedamos hasta el numero largo, 
	 *			estamos quedandonos con el OID de la CA, es decir el groovy saltará para todos los certificados de 
	 *			esa CA (usar con precaución de no pisar otros soportes específicos de esa CA).
	 *			Para nuestro ejemplo vamos a usar el OID "1.3.6.1.4.1.16533.10.2.1"
	 *
	 *		-String CA: Nombre de la CA a soportar, por ejemplo "Sample CA".
	 *
	 *		-String typeCertificate: descripción del tipo de certificado según lo descrito en su propia política, por ejemplo, "Certificado de Persona Natural"
	 *
	 *		-TypeLegalX509 typeLegal: a nivel interno sólo reconocemos los siguientes valores:
	 *           TypeLegalX509.CORPORATE_DIGITAL_CERTIFICATE
	 *           TypeLegalX509.INDIVIDUAL_DIGITAL_CERTIFICATE
	 *			 TypeLegalX509.LEGAL_REPRESENTATIVE_DIGITAL_CERTIFICATE
	 *			 TypeLegalX509.PUBLIC_EMPLOYEE_CERTIFICATE
	 *
	 *
	 *
	 *
	 *		-String dateIncluded: fecha de creación del groovy en formato yyyyMMdd
	 *
	 *		-String title: nombre dado al certificado, por ejemplo: "Sampple CA - Certificado de Persona Natural"
	 *
	 *		-String description: recomendamos indicar la uri del ticket donde se reportó la petición del soporte a este nuevo certificado. Por ejemplo el ticket de redmine.
	 *
	 *		-TransformPatern[] transform: Array de objetos TransformPatterns. Estos objetos TransformPatterns sirven para mapear OID's del 
	 *			certificado no reconocidos por viafirma a propiedades que sí se conocen. Por ejemplo un certificado extranjero podría informar 
	 *			el email en un OID distinto al habitual, en ese caso añadiríamos a la lista 'transformPatterns' un TransformPattern para mapear el 
	 *			OID extraño a la propiedad EMAIL de certificadoGenerico de viafirma:
	 *
	 *				Ej. para transformar atributos ya gestionados por viafirma:
	 *				
	 *				transformPatterns.add(new TransformPatern("1.25.3654.32.85.1",  Attributes.email.name(), null));
	 *
	 *					Los atributos permitidos son los siguientes:
	 *					
	 *					Attributes.name.name()
	 *					Attributes.organization.name()
	 *					Attributes.cif.name()
	 *					Attributes.ownerId.name()
	 *					Attributes.organizationId.name()
	 *					Attributes.surname.name()
	 *					Attributes.numberUserId.name()
	 *					Attributes.email.name()
	 *
	 *
	 *					También se pueden añadir constantes gestionadas ya por viafirma; están disponibles las siguientes:
	 *
	 *					String CERTIFICATE_PROPERTY_NUMBER_ID_RESPONSABLE = "numberIdResponsable";
	 *					String CERTIFICATE_PROPERTY_NUMBER_ID_ORGANIZACION = "numberIdOrganizacion";
	 *				
	 *				
	 *				
	 *				Ej. para transformar otros atributos:	 
	 *				
	 *				transformPatterns.add(new TransformPatern("2.16.591.1.1.1.6",  "fechaNacimiento", null));
	 *
	 *		-X509Certificate... certs: Se esperan uno o varios X509Certificate de los crt Raíz del certificado, es decir, todos los de la cadena de confianza.
	 *					Para cargar los X509Certificate se usa el método PredefinedCAs.loadCer(url):
	 *					
	 *			
	 *				Ejemplo:
	 *					PredefinedCAs.loadCer("http://www.pki.gob.pa/test/cacerts/capc2test.crt"),
	 *					PredefinedCAs.loadCer("http://www.pki.gob.pa/test/cacerts/caraiztest.crt"));
	 *
	 *
	 * @return
	 */
	public Object execute(Map m) {
		List<TransformPatern> transformPatterns = new LinkedList<TransformPatern>();
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.1",  "GIVENNAME", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.2",  "surname1", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.3",  "surname2", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.4",  "NIF", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.7",  "CIF", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.6",  "legal_company_name", null));
		transformPatterns.add(new TransformPatern("1.3.6.1.4.1.5734.1.4",  Attributes.numberUserId.name(), null));
		
		 //incluir descripcion aqui  (sustituir OID y propiedad deseada; no usar espacios en blanco o caracteres especiales)
		
		CASupport support = new CASupport(	"Documenta",
											"1.3.6.1.4.1.48315.1.1.1.6.1.1", //OID de la política de certificado
											"Documenta", //nombre de la CA
											"Documenta", //nombre del certificado 
											TypeLegalX509.INDIVIDUAL_DIGITAL_CERTIFICATE, //indicar el tipo deseado (ver valores disponibles en la javadoc)
											"20160725", //fecha de creación del groovy
											"Documenta", //descripción del certificado
											"http://redmine.viFirmaprofesional.com/issues/23444", //uri del ticket de soporte, por ejemplo redmine 
											(TransformPatern[])transformPatterns.toArray()
											);
											
		List<String> urlsCRL = new LinkedList<String>();
		urlsCRL.add("https://www.documenta.com.py/firmadigital/descargas/crldoc.crl");
		urlsCRL.add("http://www.acraiz.gov.py/arl/ac_raiz_py.crl");
		support.setUrlsCRL(urlsCRL);

		return support;
	}

	/**
	 * Recupera el nombre Identificador del script
	 * 
	 * @return
	 */
	public String getNombre(){
		return "Documenta"; //sustituir con el nombre deseado
	}

	/**
	 * Recupera la descripción de un Script.
	 * 
	 * @return
	 */
	public String getDescripcion(){
		return "Persona física"; //sustituir con la descripción deseada
	}

}