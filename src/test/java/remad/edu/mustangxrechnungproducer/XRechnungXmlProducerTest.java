package remad.edu.mustangxrechnungproducer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.remad.tutoring3.persistence.models.AddressEntity;
import edu.remad.tutoring3.persistence.models.InvoiceEntity;
import edu.remad.tutoring3.persistence.models.PriceEntity;
import edu.remad.tutoring3.persistence.models.ServiceContractEntity;
import edu.remad.tutoring3.persistence.models.UserEntity;
import remad.edu.mustangxrechnungproducer.utilities.XRechnungXmlProducerUtilities;

/**
 * Tests for {@link XRechnungXmlProducer}
 * 
 * @author edu.remad
 * @since 2026
 */
@ExtendWith(MockitoExtension.class)
public class XRechnungXmlProducerTest {

	@Test
	public void constructorSuccessfulCreatesInstance() {
		assertNotNull(new XRechnungXmlProducer(), "XRechnungXmlProducer has to be not null.");
	}

	@Test
	public void successProducedXML() {
		InvoiceEntity mockedInvoice = mock(InvoiceEntity.class);
		UserEntity mockedUser = mock(UserEntity.class);
		AddressEntity mockedAddress = mock(AddressEntity.class);
		List<AddressEntity> addresses = new ArrayList<>(1);
		addresses.add(mockedAddress);
		ServiceContractEntity mockedServiceContract = mock(ServiceContractEntity.class);
		PriceEntity mockedPrice = mock(PriceEntity.class);
		LocalDateTime invoiceCreationDate = LocalDateTime.of(2024, 3, 14, 10, 0);
		XRechnungXmlProducer producer = new XRechnungXmlProducer(mockedInvoice, XRechnungXmlProducerUtilities.ceateJUnitTestProperties());

		when(mockedInvoice.getUserId()).thenReturn(mockedUser);
		when(mockedUser.getAddresses()).thenReturn(addresses);
		when(mockedAddress.getAddressZipCode()).thenReturn(22359);
		when(mockedUser.getName()).thenReturn("John");
		when(mockedUser.getFamilyName()).thenReturn("Doe");
		when(mockedAddress.getAddressStreet()).thenReturn("ExampleStreet");
		when(mockedAddress.getAddressHouseNo()).thenReturn("12");
		when(mockedAddress.getAddressZipCode()).thenReturn(22359);
		when(mockedAddress.getPlace()).thenReturn("Hamburg");
		when(mockedUser.getEmail()).thenReturn("example@openweb.info");
		when(mockedInvoice.getServiceContractId()).thenReturn(mockedServiceContract);
		when(mockedServiceContract.getServiceContractName()).thenReturn("Elektrotechnik");
		when(mockedServiceContract.getServiceContractDescription()).thenReturn("Explanation ipsum");
		when(mockedInvoice.getPriceId()).thenReturn(mockedPrice);
		when(mockedPrice.getPrice()).thenReturn(new BigDecimal(13));
		when(mockedInvoice.getCreationDate()).thenReturn(invoiceCreationDate);
		when(mockedInvoice.getNo()).thenReturn(156L);
		
		byte[] actualXML = producer.produceXmlByteArray();
		assertNotNull(actualXML);
		assertTrue(actualXML.length == 6992 );
		
		String theXML = new String(actualXML, StandardCharsets.UTF_8);
		assertThat(theXML, containsString("156"));
		assertThat(theXML, containsString("DEMOBIC"));
		assertThat(theXML, containsString("0.00"));
		assertThat(theXML, containsString("DE89370400440532013000"));
		assertThat(theXML, containsString("Germany"));
		assertThat(theXML, containsString("DE"));
		assertThat(theXML, containsString("john.doe@doesnotexist.com"));
		assertThat(theXML, containsString("John Doe"));
		assertThat(theXML, containsString("63643643643646"));
		assertThat(theXML, containsString("+49743646546464"));
		assertThat(theXML, containsString("Examplestreet 16A"));
		assertThat(theXML, containsString("TAX-ID-6366363"));
		assertThat(theXML, containsString("VAT_ID_723985"));
		assertThat(theXML, containsString("6536363"));
		assertThat(theXML, containsString("Hamburg"));
		assertThat(theXML, containsString("ExampleStreet 12"));
		assertThat(theXML, containsString("22359"));
		assertThat(theXML, containsString("Elektrotechnik"));
		assertThat(theXML, containsString("Explanation ipsum"));
		assertThat(theXML, containsString("example@openweb.info"));
		assertThat(theXML, containsString("20240314"));
	}

}