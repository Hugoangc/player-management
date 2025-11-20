package com.practice.player_management.services;


import com.practice.player_management.components.CodenameRepositoryFactory;
import com.practice.player_management.enums.GroupCodename;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CodenameService {
    private final CodenameRepositoryFactory codenameRepositoryFactory;

    public String generateCodename(GroupCodename groupCodename, List<String> codenameInUse ) throws Exception {
        var codenamesAvailable = listCodenamesAvailable(groupCodename, codenameInUse);
        if(codenamesAvailable.isEmpty()){
            throw new RuntimeException("Codename not available");
        }
        var sortedCodename = sortCodename(codenamesAvailable);
        return sortedCodename;
    }


    private List<String> listCodenamesAvailable(GroupCodename groupCodename, List<String> codenameInUse) throws Exception {
        var codenames = searchCodenames(groupCodename);

        var codenamesAvailable = codenames
                .stream()
                .filter(codename -> !codenameInUse.contains(codename))
                .toList();
        return codenamesAvailable;
    }

    private List<String> searchCodenames(GroupCodename groupCodename) throws Exception{
        var codenameRepository = codenameRepositoryFactory.create(groupCodename);
        return codenameRepository.searchCodenames();
    }


    private String sortCodename(List<String> codenamesAvailable) {
        return codenamesAvailable
                .get((int) (Math.random() * codenamesAvailable.size()));

    }

}
