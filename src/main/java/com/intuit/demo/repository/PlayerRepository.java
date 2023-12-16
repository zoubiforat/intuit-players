package com.intuit.demo.repository;

import com.intuit.demo.enitity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
