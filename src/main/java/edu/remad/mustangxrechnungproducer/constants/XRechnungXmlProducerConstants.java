package edu.remad.mustangxrechnungproducer.constants;

/**
 * Constants for the producer
 * 
 * @author edu.remad
 * @since 2026
 */
public final class XRechnungXmlProducerConstants {

	/**
	 * private Constructor
	 */
	private XRechnungXmlProducerConstants() {
		// do not instantiate
	}

	/** DocumentName constant for recipient */
	public static final String DOCUMENT_NAME = "Rechnung";

	/** German profile is XRechnung */
	public static final String DEFAULT_PROFILE = "XRechnung";

	/** For Germany needed legal tax note on an invoice */
	public static final String LEGAL_TAX_NOTE = "Hinweis: Als Kleinunternehmer im Sinne von § 19 Abs. 1 UStG wird Umsatzsteuer nicht berechnet. (Das Rechnungsdatum entspricht dem Leistungsdatum)";

}
