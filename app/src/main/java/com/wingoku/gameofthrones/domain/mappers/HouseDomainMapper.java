package com.wingoku.gameofthrones.domain.mappers;

import com.wingoku.gameofthrones.data.network.models.HouseDTO;
import com.wingoku.gameofthrones.domain.models.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDomainMapper implements DomainMapper<House, HouseDTO> {

    @Override
    public House fromDTO(HouseDTO houseDTO) {
        return new House(
                houseDTO.getId(),
                houseDTO.getName(),
                houseDTO.getRegion(),
                houseDTO.getTitle()
        );
    }

    @Override
    public HouseDTO toDTO(House house) {
        return new HouseDTO(
                house.getId(),
                house.getName(),
                house.getRegion(),
                house.getTitle()
        );
    }

    @Override
    public List<House> fromDTO(List<HouseDTO> houseDTOS) {
        List<House> list = new ArrayList<>();

        for(HouseDTO dto : houseDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<HouseDTO> toDTO(List<House> houses) {
        List<HouseDTO> list = new ArrayList<>();

        for(House dto : houses) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
