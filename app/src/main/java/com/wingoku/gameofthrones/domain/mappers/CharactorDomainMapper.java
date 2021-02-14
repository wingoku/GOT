package com.wingoku.gameofthrones.domain.mappers;

import com.wingoku.gameofthrones.data.network.models.CharactorDTO;
import com.wingoku.gameofthrones.domain.models.Charactor;

import java.util.ArrayList;
import java.util.List;

public class CharactorDomainMapper implements DomainMapper<Charactor, CharactorDTO> {

    @Override
    public Charactor fromDTO(CharactorDTO charactorDTO) {
        return new Charactor(
                charactorDTO.getId(),
                charactorDTO.getName(),
                charactorDTO.getGender(),
                charactorDTO.getCulture(),
                charactorDTO.getBorn(),
                charactorDTO.getDied(),
                charactorDTO.getTitles(),
                charactorDTO.getAliases(),
                charactorDTO.getFather(),
                charactorDTO.getMother(),
                charactorDTO.getSpouse(),
                charactorDTO.getAllegiances(),
                charactorDTO.getPlayedBy()
        );
    }

    @Override
    public CharactorDTO toDTO(Charactor charactor) {
        return new CharactorDTO(
                charactor.getId(),
                charactor.getName(),
                charactor.getGender(),
                charactor.getCulture(),
                charactor.getBorn(),
                charactor.getDied(),
                charactor.getTitles(),
                charactor.getAliases(),
                charactor.getFather(),
                charactor.getMother(),
                charactor.getSpouse(),
                charactor.getAllegiances(),
                charactor.getPlayedBy()
        );
    }

    @Override
    public List<Charactor> fromDTO(List<CharactorDTO> characterDTOS) {
        List<Charactor> list = new ArrayList<>();

        for(CharactorDTO dto : characterDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<CharactorDTO> toDTO(List<Charactor> charactors) {
        List<CharactorDTO> list = new ArrayList<>();

        for(Charactor dto : charactors) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
