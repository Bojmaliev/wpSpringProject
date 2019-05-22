package mk.trkalo.dnp.dnpshop.dto;

import mk.trkalo.dnp.dnpshop.dto.address.AddressDto;
import mk.trkalo.dnp.dnpshop.dto.address.StreetDto;

import java.util.Set;
public class NewOrderClientDto {
    public String name;
    public Set<String>  phoneNumbers;
    public AddressDto address;
    public StreetDto street;
    public String object;
}
