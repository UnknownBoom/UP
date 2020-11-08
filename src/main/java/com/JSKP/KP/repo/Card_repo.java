package com.JSKP.KP.repo;

import com.JSKP.KP.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Card_repo extends JpaRepository<Card,String> {
    Card findCardByNumber(String number);
}
