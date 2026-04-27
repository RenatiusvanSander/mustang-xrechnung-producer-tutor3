package remad.edu.mustangxrechnungproducer.exceptions;

/**
 * Exception for XRechnungProducer
 * 
 * @author edu.remad
 * @since 2026
 */
public class XRechnungProducerException extends RuntimeException {

	/** created serial version UID */
	private static final long serialVersionUID = 5511683112731449900L;

	/**
	 * Constructor of {@link XRechnungProducerException}
	 * 
	 * @param message error message
	 */
	public XRechnungProducerException(String message) {
		super(message);
	}

	/**
	 * Constructor of {@link XRechnungProducerException}
	 * 
	 * @param message error message
	 * @param t       {@link Throwable}
	 */
	public XRechnungProducerException(String message, Throwable t) {
		super(message, t);
	}

}