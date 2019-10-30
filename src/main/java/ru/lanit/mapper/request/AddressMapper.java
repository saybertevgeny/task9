package ru.lanit.mapper.request;

import ru.lanit.entity.Address;
import ru.lanit.exception.MappingException;
import javax.servlet.http.HttpServletRequest;

public class AddressMapper {

    private enum RequiredParams{street,house,flat}

    public static Address map(HttpServletRequest request) throws MappingException {
        Address address = new Address();
        for(AddressMapper.RequiredParams p: AddressMapper.RequiredParams.values()){
            String paramValue = request.getParameter(p.name());
            if(paramValue == null || paramValue.isEmpty()){
                throw new MappingException("Все поля обязательны");
            }
        }
        address.setStreet(request.getParameter("street"))
                .setHouse(request.getParameter("house"))
                .setFlat(request.getParameter("flat"));
        return address;
    }
}
